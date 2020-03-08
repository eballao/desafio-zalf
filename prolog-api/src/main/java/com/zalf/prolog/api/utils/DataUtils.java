package com.zalf.prolog.api.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DataUtils {
	
	public static String getLocalDateFormatString(final LocalDate date, final String pattern) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		try {
			
			return formatter.format(date);
		}
		catch (final Exception e) {
			return null;
		}
	}
	
	public static String getLocalDateTimeFormatString(final LocalDateTime date, final String pattern) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		try {
			
			return formatter.format(date);
		}
		catch (final Exception e) {
			return null;
		}
	}
	
	public static String getLocalTimeFormatString(final LocalTime time, final String pattern) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		try {
			
			return formatter.format(time);
		}
		catch (final Exception e) {
			return null;
		}
	}
	
	public static String getDurationFormatString(Duration duration) {
	    long seconds = duration.getSeconds();
	    long absSeconds = Math.abs(seconds);
	    String positive = String.format(
	        "%d:%02d:%02d",
	        absSeconds / 3600,
	        (absSeconds % 3600) / 60,
	        absSeconds % 60);
	    return seconds < 0 ? "-" + positive : positive;
	}
	
	public static String getIntegerMinutosFormatString(Integer minutos) {
		long seconds = minutos * 60;
	    long absSeconds = Math.abs(seconds);
	    String positive = String.format(
	        "%d:%02d:%02d",
	        absSeconds / 3600,
	        (absSeconds % 3600) / 60,
	        absSeconds % 60);
	    return minutos < 0 ? "-" + positive : positive;
	}
	

}
