package com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.model;

import java.util.List;

public class ActivityFile {
    private Long uniqueId;
    private List<Activity> activities;
	public Long getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(Long uniqueId) {
		this.uniqueId = uniqueId;
	}
	public List<Activity> getActivities() {
		return activities;
	}
	@Override
	public String toString() {
		return "ActivityFile [uniqueId=" + uniqueId + ", activities=" + activities + "]";
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

    // Getters and Setters
}