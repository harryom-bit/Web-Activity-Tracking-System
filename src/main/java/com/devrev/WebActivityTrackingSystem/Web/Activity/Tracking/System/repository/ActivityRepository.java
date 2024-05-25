package com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.model.Activity;
import com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.model.DailyActivityComparison;
import com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.model.MonthlyActivityStatistic;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
	@Query("SELECT new com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.model.MonthlyActivityStatistic(a.name, COUNT(a)) " +
	           "FROM Activity a " +
	           "WHERE a.time BETWEEN :startDate AND :endDate " +
	           "GROUP BY a.name " +
	           "ORDER BY COUNT(a) DESC")
	    List<MonthlyActivityStatistic> findActivityStatisticsForMonth(@Param("startDate") Timestamp startDate,
	                                                                  @Param("endDate") Timestamp endDate);

	    @Query("SELECT new com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.model.DailyActivityComparison(a.name, " +
	           "SUM(CASE WHEN a.time BETWEEN :yesterdayStart AND :yesterdayEnd THEN 1 ELSE 0 END), " +
	           "SUM(CASE WHEN a.time BETWEEN :todayStart AND :todayEnd THEN 1 ELSE 0 END), " +
	           "CASE WHEN SUM(CASE WHEN a.time BETWEEN :todayStart AND :todayEnd THEN 1 ELSE 0 END) > " +
	           "SUM(CASE WHEN a.time BETWEEN :yesterdayStart AND :yesterdayEnd THEN 1 ELSE 0 END) THEN 'positive' " +
	           "WHEN SUM(CASE WHEN a.time BETWEEN :todayStart AND :todayEnd THEN 1 ELSE 0 END) < " +
	           "SUM(CASE WHEN a.time BETWEEN :yesterdayStart AND :yesterdayEnd THEN 1 ELSE 0 END) THEN 'negative' " +
	           "ELSE 'unaltered' END) " +
	           "FROM Activity a " +
	           "GROUP BY a.name")
	    List<DailyActivityComparison> findActivityStatisticsYesterdayVsToday(@Param("yesterdayStart") Timestamp yesterdayStart,
	                                                                         @Param("yesterdayEnd") Timestamp yesterdayEnd,
	                                                                         @Param("todayStart") Timestamp todayStart,
	                                                                         @Param("todayEnd") Timestamp todayEnd);

}
