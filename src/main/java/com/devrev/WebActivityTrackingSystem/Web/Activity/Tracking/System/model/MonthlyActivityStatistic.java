package com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.model;

public class MonthlyActivityStatistic {
	private String activityName;
    private long occurrences;
    public MonthlyActivityStatistic(String activityName, long occurrences) {
        this.activityName = activityName;
        this.occurrences = occurrences;
    }
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public long getOccurrences() {
		return occurrences;
	}
	public void setOccurrences(long occurrences) {
		this.occurrences = occurrences;
	}

}
