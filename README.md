# DevRev Java-Developer Assignment

## Note:
**This project only has backend implementation** 
1. **Backend part of this application is build on Spring-Boot.**

## Project Description: 

This project has one controller, Service, Repository and Model layer.

## DQL includes
 - I have used the following the queries in order to implement the problem number 2:
  - SELECT new com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.model.DailyActivityComparison(a.name, " +
	           "SUM(CASE WHEN a.time BETWEEN :yesterdayStart AND :yesterdayEnd THEN 1 ELSE 0 END), " +
	           "SUM(CASE WHEN a.time BETWEEN :todayStart AND :todayEnd THEN 1 ELSE 0 END), " +
	           "CASE WHEN SUM(CASE WHEN a.time BETWEEN :todayStart AND :todayEnd THEN 1 ELSE 0 END) > " +
	           "SUM(CASE WHEN a.time BETWEEN :yesterdayStart AND :yesterdayEnd THEN 1 ELSE 0 END) THEN 'positive' " +
	           "WHEN SUM(CASE WHEN a.time BETWEEN :todayStart AND :todayEnd THEN 1 ELSE 0 END) < " +
	           "SUM(CASE WHEN a.time BETWEEN :yesterdayStart AND :yesterdayEnd THEN 1 ELSE 0 END) THEN 'negative' " +
	           "ELSE 'unaltered' END) " +
	           "FROM Activity a " +
	           "GROUP BY a.name
  - SELECT new com.devrev.WebActivityTrackingSystem.Web.Activity.Tracking.System.model.MonthlyActivityStatistic(a.name, COUNT(a)) " +
	           "FROM Activity a " +
	           "WHERE a.time BETWEEN :startDate AND :endDate " +
	           "GROUP BY a.name " +
	           "ORDER BY COUNT(a) DESC

## DDL includes
 - CREATE DATABASE activitydb;

 - USE activitydb;

 - CREATE TABLE activity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    time TIMESTAMP,
    duration INT
    );

    
    
## Technologies used: 

- Spring Boot
- Hibernate
- Log4J
- MySql Database

## Features
- we can use the following endpoints :
- "http://localhost:8080/process-activities" -> to process all the json files.
- "http://localhost:8080/activity-statistics" -> to proces all the activity statistics.

To-Do list:
- I could have used lombok tool to reduce the boilerplate code.
- I can alos use angular as a front-end to display the data properly and provide more user friendly features for the project.

## Getting Started
- **Back-end part**
  - need to provide the filePath in the application.configuratino file to specify the path of the json files in your system.
  - also need to provide database url and password so that your application can access the database and perform the respective operaions.
  - to deply this project run the following command: mvn clean install.
  - above command will create a jar file in the target directory which can be run using java -jar command.






