package com.fun.club.utils;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private DateUtils() {
		// Private constructor to hide the implicit public one.
	}

	private final static String DATE_STRING1 = "MMM-yyyy";
	private final static String DATE_STRING2 = "dd-MMM-yyyy";
	private final static String DATE_STRING3 = "dd-MMM-yyyy HH:mm:ss";

	public static String changeDateToStringWithoutDateAndTime(Date date) throws ParseException {
		Format df = new SimpleDateFormat(DATE_STRING1);
		return df.format(date);
	}

	public static Date convertStringToDateWithoutDateAndTime(String dateString) throws ParseException {
		DateFormat df = new SimpleDateFormat(DATE_STRING1);
		return df.parse(dateString);
	}
	
	public static String changeDateToStringWithoutTime(Date date) throws ParseException {
		Format df = new SimpleDateFormat(DATE_STRING2);
		return df.format(date);
	}

	public static Date convertStringToDateWithoutTime(String dateString) throws ParseException {
		DateFormat df = new SimpleDateFormat(DATE_STRING2);
		return df.parse(dateString);
	}
	
	public static String changeDateToString(Date date) throws ParseException {
		Format df = new SimpleDateFormat(DATE_STRING3);
		return df.format(date);
	}

	public static Date convertStringToDate(String dateString) throws ParseException {
		DateFormat df = new SimpleDateFormat(DATE_STRING3);
		return df.parse(dateString);
	}
}