package org.restassured.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class BaseTest {
	static {
		try {
			// Create logs directory if not exists
			File logDir = new File("logs");
			if (!logDir.exists()) {
				logDir.mkdirs();   // creates folder "logs"
			}
			// Current date/time
			Date now = new Date();
			// Define format: yyyy-MM-dd_HH-mm-ss
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
			String formattedTime = sdf.format(now);
			// Build file name with timestamp
			String fileName = "logs/all_api_log_" + formattedTime + ".txt";
			//String fileName = "logs/all_api_log_" + System.currentTimeMillis() + ".txt";
			System.out.println(fileName);
			PrintStream logFile = new PrintStream(fileName);
			//RestAssured.filters(new RequestLoggingFilter(logFile), new ResponseLoggingFilter(logFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

