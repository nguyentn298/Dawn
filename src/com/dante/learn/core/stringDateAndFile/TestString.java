package com.dante.learn.core.stringDateAndFile;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.util.StringUtils;

public class TestString {
	public static void main(String[] args) {
		/*
		 * Use StringTokenizer to merge String by delimiter
		 */
		System.out.println("================================ TestString.tokenString() =======================================");
		TestString.tokenString();
		System.out.println();
		/*
		 * add param to url
		 * url = localhost:8080/Idea
		 * add :"?"
		 * add param : key + "=" + value
		 */
		System.out.println("================================ addParamsToUrl() =======================================");
		addParamsToUrl();
		System.out.println();
		/*
		 * Use substring() to insert string to string
		 */
		System.out.println("================================ insertParamsToUrl() =======================================");
		insertParamsToUrl();
		System.out.println();
		/*
		 * Use subString(0, 1) to get first letter
		 * Use toUpperCase() to uppercase first letter
		 * User subString(1) to merge first letter with remain letter
		 */
		System.out.println("================================ uppercaseFirstChar() =======================================");
		uppercaseFirstChar();
		System.out.println();
		/*
		 * Use subString() to remove a specific String
		 * Use replace()   to remove all specific Strings
		 */
		System.out.println("================================ removeString()=======================================");
		removeString();
		System.out.println();
		/*
		 * Limit for split: str.split(delimeter, 3);
		 */
		System.out.println("================================ splitLimit() =======================================");
		splitLimit();
		System.out.println();
		/*
		 * Compare a specific region of String A to a specific region of String B
		 * Ex: xxxA1xxx and xA2x
		 * ==> A1.indexOf() = 3, A2.indexOf() = 1
		 *   boolean match = A1.regionMatches(3, A2, 1, 5) = true;
		 *   5 is lenght of string2
		 */
		System.out.println("================================ identicalScope() =======================================");
		identicalScope();
		System.out.println();
		/*
		 * Use currentTimeMillis() to count start and end time
		 */
		System.out.println("================================ countTime() =======================================");
		countTime();
		System.out.println();
		/*
		 * Use String.intern() when many value with same value to share same memory (increase perfomance)
		 */
		System.out.println("================================ internString() =======================================");
		internString();
		System.out.println();
		/*
		 * a= 0
		 * a++ ==> a = 1 But b = a++ ==> b = 0
		 * ++a ==> a = 1 But b = ++a ==> b = 1
		 */
		System.out.println("================================ comparePlusCharater() =======================================");
		comparePlusCharater();
		System.out.println();
		
		/*
		 * Convert list to string by delimiter
		 * Ex: List = [this, is, a, cat]
		 * StringUtils.collectionToDelimitedString(list, " ", "(", ")"); ==> String test = (this) (is) (a) (cat)
		 * " " ==> delimited by each string above
		 * "(" and ")" are prefix and suffix of each string above
		 */
		System.out.println("================================ covertListToStringByCharacter() =======================================");
		covertListToStringByCharacter();
		System.out.println();
		
		/*
		 *  Compare String.split, StringUtils.split, StringUtils.splitPreserveAllTokens
		 *  String.split : get empty string on the first loop
		 *  StringUtils.split: get all empty String
		 *  StringUtils.splitPreserveAllTokens doesn't get any empty String
		 */
		System.out.println("================================ splitAdvance() =======================================");
		splitAdvance();
		System.out.println();
		
		System.out.println("================================ replaceString =======================================");
		replaceString();
		System.out.println();
		
		String a = "";
		String b = "";
		String c = "";
		
		if(a == null && (b == null || c == null)){
			
		}
	}
	
	public static void replaceString() {
		String test = "1,233";
		System.out.println(test.replace(",", ""));
	}
	
	public static void comparePlusCharater() {
		int a = 0;
		System.out.println("a: " + a);
		int b = a++;
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		
		int c = 0;
		System.out.println("c: " + c);
		int d = ++c;
		System.out.println("c: " + c);
		System.out.println("d: " + d);
		
	}
	public static void internString() {
//		Basically doing String.intern() on a series of strings will ensure that all strings having same contents share same memory. 
//		So if you have list of names where 'john' appears 1000 times, by interning you ensure only one 'john' is actually allocated memory.
//
//		This can be useful to reduce memory requirements of your program. 
//		But be aware that the cache is maintained by JVM in permanent memory pool 
//		which is usually limited in size compared to heap so you should not use intern if you don't have too many duplicate values.
		String variables[] = new String[50000];
		
		long startTime1 = System.currentTimeMillis();
		for (int i = 0; i < 50000; i++) {
			variables[i] = new String("hello");
		}
		
		long endTime1 = System.currentTimeMillis();
		System.out.println("No Intern: " + (endTime1 - startTime1) + " milli seconds");
		
		long startTime0 = System.currentTimeMillis();
		for (int i = 0; i < 50000; i++) {
			variables[i] = new String("hello");
			variables[i] = variables[i].intern();
		}
		
		long endTime0 = System.currentTimeMillis();
		System.out.println("With Intern: " + (endTime0 - startTime0) + " milli seconds");

	}
	
	public static void countTime() {
		long startTime = System.currentTimeMillis();
		String test = "";
		for(int i = 0; i < 1000000; i++) {
			test = "test";
		}
		System.out.println("test: " + test);
		long endTime = System.currentTimeMillis();
		System.out.println((endTime - startTime) + " milli seconds");
	}
	
	public static void identicalScope() {
		String test1 = "Hello world";
		String test2 = "Hi world";
		int numbertTest1 = test1.indexOf("w");
		int numbertTest2 = test2.indexOf("w");
		
		// 5 is lengh of compared string
		System.out.println("test1: " + test1);
		System.out.println("test2: " + test2);
		System.out.println("numbertTest1: " + numbertTest1);
		System.out.println("numbertTest2: " + numbertTest2);
		boolean match = test1.regionMatches(numbertTest1, test2, numbertTest2, 5);
		System.out.println(match);
	}
	
	public static void splitLimit() {
		String str = "jan-feb-march";
		String[] temp;
		String delimeter = "-";
		temp = str.split(delimeter);
		for (int i = 0; i < temp.length; i++) {
//			System.out.println(temp[i]);
//			System.out.println("");
			
		}
		
		str = "jan.feb.march.april.may";
		delimeter = "\\.";
		temp = str.split(delimeter, 3);
		for (int i = 0; i < temp.length; i++) {
			System.out.println("temp[i] " + temp[i]);
		}
	}
	public static void removeString() {
		String test = "My computer has broken down";
		int indexOfHas = test.indexOf("h");
		String resutl = test.substring(0, indexOfHas) + test.substring(indexOfHas+1);
		System.out.println(resutl);
	}
	
	public static void uppercaseFirstChar() {
		String[] array = new String[]{"test", "nguyen van jayce", "tran van lucian"};
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < array.length; i++) {
			String[] subArray = array[i].split(" ");
			StringBuilder strBuilder = new StringBuilder();
			for(int j = 0; j < subArray.length; j++) {
				String subStr = subArray[j].substring(0, 1).toUpperCase() + subArray[j].substring(1);
				if(j > 0) {
					strBuilder.append(" ");
				}
				strBuilder.append(subStr);
				System.out.println("j" + j);
			}
			list.add(strBuilder.toString());
		}
		String[] result = list.toArray(new String[list.size()]);
		System.out.println(array);
		
		System.out.println("=============================");
		System.out.println("Result of List: ");
		System.out.println(list);
		
		System.out.println("=============================");
		System.out.println("Result of Array: ");
		for(int k = 0; k < result.length; k++) {
			System.out.println("result " + k + " = " + result[k]);
		}
	}
	
	public static String addParamsToUrl() {
		String url = "http://testParam";
		String[] paramNames = {"user", "password"};
		String[] paramValues = {"fox", "123456"};
		StringBuilder wsUrl = new StringBuilder();
		if(paramNames.length != paramValues.length) {
			System.out.println("no same lenght!!!");
		}
		
		for(int i = 0; i < paramNames.length; i++) {
			if(i > 0) {
				wsUrl.append("&");
			}
			wsUrl.append(paramNames[i] + "=" + paramValues[i]);
			
		}
		
		if(wsUrl.length() > 0) {
			url += "?" + wsUrl;
		}
		
		System.out.println(url);
		return url;
		
	}
	
	public static String insertParamsToUrl() {
		String url = addParamsToUrl();
		String addStr = "id=69";
		int index = url.indexOf("?");
		String result = url.substring(0, index + 1) + addStr + url.substring(index);
		System.out.println(result);
		return result;
	}

	public static void tokenString() {
		String str = "test-abc-heheh";
		
		StringTokenizer st = new StringTokenizer(str, "-");
		System.out.println("token: " + st);
		String newStr = "";
		while (st.hasMoreTokens()) {
			newStr += st.nextToken();
		}

		System.out.println(newStr);
	}
	public static void formatString() {
		
		// Preceding with zeros
		String flag = String.format("%03X%d", 6, 9);
		System.out.println(flag);
		
		String.format("Characters: %c %c \n", 'a', 65);
		String.format ("Decimals: %d %ld\n", 1977, 650000L);
		String.format ("Preceding with blanks: %10d \n", 1977);
		String.format ("Preceding with zeros: %010d \n", 1977);
		String.format ("Some different radices: %d %x %o %#x %#o \n", 100, 100, 100, 100, 100);
		String.format ("floats: %4.2f %+.0e %E \n", 3.1416, 3.1416, 3.1416);
		String.format ("Width trick: %*d \n", 5, 10);
		String.format ("%s \n", "A string");
	}
	
	
	public void myString() {
		String test = "I'm Cat cat";
		int indexOf = test.indexOf("a");
		String subString = test.substring(0, indexOf);
		String subStringofBeging = test.substring(indexOf);
		System.out.println("Index Of: " + indexOf);
		System.out.println("");
		System.out.println("SubString 0 --> indexOf: " + subString);
		System.out.println("");
		System.out.println("SubString of indexOf: " + subStringofBeging);
		
		String url = "http://localhost:8080/Dawn/user?name=dante&age=19";
		String id = "id=1&";
		
		int firstUrl = url.indexOf("?");
		String fullUrl = url.substring(0, firstUrl + 1) + id + url.substring(firstUrl + 1);
		System.out.println("");
		System.out.println("full url: " + fullUrl);
	}
	
	public static void covertListToStringByCharacter() {
		List<String> list = new ArrayList<>();
		// this is a cat
		list.add("this");
		list.add("is");
		list.add("a");
		list.add("cat");
		
		// use any character
		String useSpace = StringUtils.collectionToDelimitedString(list, " ");
		System.out.println("Use space (collectionToDelimitedString): " + useSpace);
		System.out.println();
		
		// use prefix and suffix
		String useSpaceAndPrefixAndSuffix = StringUtils.collectionToDelimitedString(list, " ", "(", ")");
		System.out.println("Use Space (collectionToDelimitedString), Prefix And Suffix: " + useSpaceAndPrefixAndSuffix);
		System.out.println();
		
		// use comma
		String useComma= StringUtils.collectionToCommaDelimitedString(list);
		System.out.println("Use comma (collectionToCommaDelimitedString): " + useComma);
		System.out.println();
	}
	
	public static void splitAdvance() {
		String test1 = "-this-is-a-cat-";
		String test2 = null;
		
		// Normal split does not handle with null value like test2
		String[] test11 = test1.split("-");
		for (String string : test11) {
			System.out.println("Normal split: " + string);
		}
		
		// Get all
		System.out.println();
		String[] test22 = org.apache.commons.lang3.StringUtils.splitPreserveAllTokens(test1, "-");
		for (String string : test22) {
			System.out.println("Advance splitPreserveAllTokens: " + string);
		}
		
		// Not return empty value
		System.out.println();
		String[] test33 = org.apache.commons.lang3.StringUtils.split(test1, "-");
		for (String string : test33) {
			System.out.println("Advance split: " + string);
		}
	}
}
