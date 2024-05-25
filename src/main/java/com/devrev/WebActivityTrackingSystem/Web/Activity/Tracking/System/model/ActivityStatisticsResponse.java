package com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.model;

import java.util.List;

public class ActivityStatisticsResponse {
	private List<MonthlyActivityStatistic> activityStatisticsForMonth;
    private List<DailyActivityComparison> activityStatisticsYesterdayVsToday;
    public ActivityStatisticsResponse(List<MonthlyActivityStatistic> activityStatisticsForMonth,List<DailyActivityComparison> activityStatisticsYesterdayVsToday) {
    	this.activityStatisticsForMonth=activityStatisticsForMonth;
    	this.activityStatisticsYesterdayVsToday=activityStatisticsYesterdayVsToday;
    }
	public List<MonthlyActivityStatistic> getActivityStatisticsForMonth() {
		return activityStatisticsForMonth;
	}
	public void setActivityStatisticsForMonth(List<MonthlyActivityStatistic> activityStatisticsForMonth) {
		this.activityStatisticsForMonth = activityStatisticsForMonth;
	}
	public List<DailyActivityComparison> getActivityStatisticsYesterdayVsToday() {
		return activityStatisticsYesterdayVsToday;
	}
	public void setActivityStatisticsYesterdayVsToday(List<DailyActivityComparison> activityStatisticsYesterdayVsToday) {
		this.activityStatisticsYesterdayVsToday = activityStatisticsYesterdayVsToday;
	}

}
