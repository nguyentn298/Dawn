package com.dante.crazy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Calculator {

	public final static String PLUS = "+";
	public final static String MINUS = "-";
	public final static String MULTIPLY = "*";
	public final static String DIVIDE = "/";

	public static List<String> list = new ArrayList<String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add(PLUS);
			add(MINUS);
			add(MULTIPLY);
			add(DIVIDE);
		}
	};

	public static void main(String[] args) {
		
		String content = "+45";

		boolean checkCondtion = validNumber(content);
		if(!checkCondtion) {
			throw new RuntimeException("please enter valid number!!");
		}
		
		for (String string : list) {
			if (content.contains(string)) {
				operator(string, content);
			}
		}
	}

	// factory
	public static void operator(String type, String content) {
		List<String> values = getValues(type, content);
		int a = Integer.parseInt(values.get(0));
		int b = Integer.parseInt(values.get(1));

		switch (type) {
		case PLUS:
			plus(a, b);
			break;
		case MINUS:
			minus(a, b);
			break;
		case MULTIPLY:
			multiply(a, b);
			break;
		case DIVIDE:
			divide(a, b);
			break;
		default:
			System.out.println(String.format("We don't support your type[%s]", type));
			break;
		}
	}

	// util
	public static List<String> getValues(String type, String content) {
		List<String> list = new ArrayList<String>();
		String a = content.substring(0, content.indexOf(type));
		
		if(StringUtils.isBlank(a)) {
			a = "0";
		}
		
		String b = content.substring(content.indexOf(type)+1);

		list.add(a);
		list.add(b);
		return list;

	}

	public static boolean validNumber(String content) {
		String regex = "\\d*[+*-/]?\\d*";
//		String content = "1+456";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	// Service
	public static void plus(int a, int b) {
		System.out.println(a + b);
	}

	public static void minus(int a, int b) {
		System.out.println(a - b);
	}

	public static void multiply(int a, int b) {
		System.out.println(a * b);
	}

	public static void divide(int a, int b) {
		System.out.println(a / b);
	}
}
