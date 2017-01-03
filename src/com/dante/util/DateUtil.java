package com.dante.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static final String DATE_FORMAT_DB = "yyyy-MM-dd hh:mm";
	
	// Java : mm = minute, MM = month
	// DatePicker : mm = month (digit), MM = month (letter)
	public static Date convertDateToDate(Date originalDate) {
		String str = new SimpleDateFormat(DATE_FORMAT_DB).format(originalDate);
		Date newDate;
		try {
			newDate = new SimpleDateFormat(DATE_FORMAT_DB).parse(str);
			return newDate;
		} catch (ParseException e) {
			System.out.println(e);
			return null;
		}
	}
}
