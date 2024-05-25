package com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.service;

import com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.model.Activity;
import com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.model.ActivityFile;
import com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.model.ActivityStatisticsResponse;
import com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.model.DailyActivityComparison;
import com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.model.MonthlyActivityStatistic;
import com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.repository.ActivityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ActivityService {
	Logger logger = Logger.getLogger("ActivityService.class");
	private static final String[] VALID_NAMES = { "doubleTap", "singleTap", "crash", "anr" };
	private final ActivityRepository activityRepository;
	private final ObjectMapper objectMapper;

	@Value("${filePath}")
	private String textFilePath;

	@Autowired
	public ActivityService(ActivityRepository activityRepository, ObjectMapper objectMapper) {
		this.activityRepository = activityRepository;
		this.objectMapper = objectMapper;
	}

	public int processActivityFiles() throws IOException, NullPointerException {
		logger.info("instantiating processActivityFiles method");
		File uploadDirectory = new File("D:\\devrev\\");
		File[] uploadFiles = uploadDirectory.listFiles();
		if (uploadFiles.length == 0) {
			return 0;
		}
		logger.info("list of upload files are " + Arrays.toString(uploadFiles));
		for (File file : uploadFiles) {
			ActivityFile activityFile = objectMapper.readValue(file, ActivityFile.class);
			List<Activity> validActivities = activityFile.getActivities().stream().filter(this::isValidActivity)
					.collect(Collectors.toList());
			activityRepository.saveAll(validActivities);
		}
		return 1;
	}

	private boolean isValidActivity(Activity activity) {
		for (String validName : VALID_NAMES) {
			if (validName.equals(activity.getName())) {
				return true;
			}
		}
		return false;
	}

	public ActivityStatisticsResponse getActivityStatistics() {
		logger.info("getActivityStatistics() method intiated ");

		// Today start and end
		LocalDate endDate = LocalDate.now();
		LocalDate todayStart = endDate.atStartOfDay().toLocalDate();
		LocalDateTime todayStartDateTime = todayStart.atStartOfDay();
		Timestamp todayStartTimestamp = Timestamp.valueOf(todayStartDateTime);

		LocalDate todayEnd = todayStart.plusDays(1);
		LocalDateTime todayMinusMonthTime = todayEnd.atStartOfDay();
		Timestamp todayMinusMonthTimestamp = Timestamp.valueOf(todayMinusMonthTime);
		LocalDateTime todayEndDateTime = todayEnd.atStartOfDay();
		Timestamp todayEndTimestamp = Timestamp.valueOf(todayEndDateTime);

		// Yesterday start and end
		LocalDate yesterdayStart = todayStart.minusDays(1);
		LocalDateTime yesterdayStartDateTime = yesterdayStart.atStartOfDay();
		Timestamp yesterdayStartTimestamp = Timestamp.valueOf(yesterdayStartDateTime);

		LocalDate yesterdayEnd = todayStart;
		LocalDateTime yesterdayEndDateTime = yesterdayEnd.atStartOfDay();
		Timestamp yesterdayEndTimestamp = Timestamp.valueOf(yesterdayEndDateTime);
		
		List<MonthlyActivityStatistic> monthlyStats = activityRepository
				.findActivityStatisticsForMonth(todayStartTimestamp, todayMinusMonthTimestamp);
		List<DailyActivityComparison> dailyStats = activityRepository.findActivityStatisticsYesterdayVsToday(
				yesterdayStartTimestamp, yesterdayEndTimestamp, todayStartTimestamp, todayEndTimestamp);

		return new ActivityStatisticsResponse(monthlyStats, dailyStats);
	}
}
