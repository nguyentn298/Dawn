package com.dante.learn.core.stringDateAndFile;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TestDate {
	public static void main(String[] args) {
		
//		Timestamp dateUpdated = new Timestamp(System.currentTimeMillis() - (60 * 60 * 1000));
//		System.out.println(dateUpdated);
		
//		test();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		System.out.println(dateFormat.toPattern());
		
	}
	
	public static void test() {
		String str = "08/29/1991";
		System.out.println("String: " + str);
		

		try {
			Date initDate = new SimpleDateFormat("MM/dd/yyyy").parse(str);
			System.out.println("Convert String to Date Object(MM/dd/yyyy): " + initDate);
			
			String convertDate = new SimpleDateFormat("yyyy-MM-dd").format(initDate);
			System.out.println("Convert Date to String Object(MM/dd/yyyy --> yyyy-MM-dd): " + convertDate);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void convertStringToDate() throws ParseException {
		String startDateString = "06/27/2007";
		System.out.println("String: " + startDateString);
		// This object can interpret strings representing dates in the format
		// MM/dd/yyyy
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

		// Convert from String to Date
		Date startDate = df.parse(startDateString);

		// Print the date, with the default formatting.
		// Here, the important thing to note is that the parts of the date
		// were correctly interpreted, such as day, month, year etc.
		System.out.println("Date, with the default formatting: " + startDate);

		// Once converted to a Date object, you can convert
		// back to a String using any desired format.
		String startDateString1 = df.format(startDate);
		System.out.println("Date in format MM/dd/yyyy: " + startDateString1);

		// Converting to String again, using an alternative format
		DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
		String startDateString2 = df2.format(startDate);
		System.out.println("Date in format dd/MM/yyyy: " + startDateString2);
	}
	
	@Test
	public void getCurrentDateOrTime() {
		
		Calendar calendar = Calendar.getInstance(); // Get current calendar
		
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int date = calendar.get(Calendar.DATE);
		int dateOfMonth = calendar.get(Calendar.DAY_OF_WEEK);

		System.out.println(hour);
		System.out.println(date);
		System.out.println(dateOfMonth);
	}
}
