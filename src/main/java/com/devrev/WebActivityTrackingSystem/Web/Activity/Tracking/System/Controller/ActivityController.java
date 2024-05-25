package com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.model.ActivityStatisticsResponse;
import com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.service.ActivityService;

@RestController
public class ActivityController {
	private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    //This end point is for first problem, here if no file is found in that location 
    @GetMapping("/process-activities")
    public String processActivities() {
        try {
            if(activityService.processActivityFiles()==0) {
            	return "NO FILE FOUND";
            }
            return "Activities processed successfully";
        } catch (IOException e) {
            return "Error processing activities: " + e.getMessage();
        }catch(NullPointerException e) {
        	return "Error Processing activities: because there are no files present";
        }
    }
    //and this one is for the second problem, where i have implemented the GET REST end-point from the database
    @GetMapping("/activity-statistics")
    public ActivityStatisticsResponse getActivityStatistics() {
        return activityService.getActivityStatistics();
    }
}
