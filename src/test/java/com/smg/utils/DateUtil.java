package com.smg.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateUtil {
	private static final Logger log = LogManager.getLogger(DateUtil.class);
	
	public static String getDateToday(String pattern) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
		String dateToday = LocalDate.now().format(dateFormat);
		log.info("Date today: {}", dateToday);
		return dateToday;
	}
}
