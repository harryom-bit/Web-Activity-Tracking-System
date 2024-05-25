package com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.model;

public class DailyActivityComparison {
	private String activityName;
    private long yesterdayOccurrences;
    private long todayOccurrences;
    private String status;
    public DailyActivityComparison(String activityName, Long yesterdayOccurrences, Long todayOccurrences, String status) {
        this.activityName = activityName;
        this.yesterdayOccurrences = yesterdayOccurrences;
        this.todayOccurrences = todayOccurrences;
        this.status = status;
    }
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public long getYesterdayOccurrences() {
		return yesterdayOccurrences;
	}
	public void setYesterdayOccurrences(long yesterdayOccurrences) {
		this.yesterdayOccurrences = yesterdayOccurrences;
	}
	public long getTodayOccurrences() {
		return todayOccurrences;
	}
	public void setTodayOccurrences(long todayOccurrences) {
		this.todayOccurrences = todayOccurrences;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
