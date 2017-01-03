package com.dante.learn.core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
	public static void main(String[] args) {
		String str = "08/29/1991";
		System.out.println("String: " + str);
		

		try {
			Date initDate = new SimpleDateFormat("MM/dd/yyyy").parse(str);
			System.out.println("initDate: " + initDate);
			
			String convertDate = new SimpleDateFormat("yyyy-MM-dd").format(initDate);
			System.out.println("Convert Date: " + convertDate);
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
}
