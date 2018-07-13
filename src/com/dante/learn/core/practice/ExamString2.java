package com.dante.learn.core.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

public class ExamString2 {

	public static void main(String[] arrs) throws IOException {
		
		// Request: ABC123 XYZ456 HJK001 ABC123 ABC123 HJK001 ==> XYZ456-1 ABC123-3 HJK001-2
		String text = "ABC123 XYZ456 HJK001 ABC123 ABC123 HJK001";
		counterString(text);
		
		// Request: id=29|name=nguyen|hero=tristana ==> Get value of name
		String text2 = "id=29|name=nguyen|hero=tristana";
		String key = "name";
		splitAndSubstring(text2, key);
		splitAndSplit(text2, key);
		
		// Request: Convert String to number, e.g: A = 1, B = 2, D = 4.
		String text3 = "A CA DD AB";
		convertStringToInteger(text3);
		
		// Request: Get String from specific file and organize it by rule that 1 line and 10 word
		String file = "D:/Nguyen/test/input.txt";
		organizeString(file);
	}
	
	public static String organizeString(String path) {
		// Begin Read File =============================
		InputStream is = null;
		StringBuffer stringBuffer = new StringBuffer("");
		try {
			 is = new FileInputStream(new File(path));
			 
			 int text = -1;
			 while((text = is.read()) != -1) {
				 char c = (char) text;
				 stringBuffer.append(c);
			 }
			 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// End reading file =============================
//		System.out.println(stringBuffer.toString());
		
		StringBuffer textFinal = new StringBuffer("");
		String myText = stringBuffer.toString();
		String[] myArrays = myText.trim().split("\\s");
		for(int i = 1; i< myArrays.length; i++) {
			textFinal.append(myArrays[i]).append(" ");
			if(i%10 == 0) {
				textFinal.append("\n");
			}
		}
		
		System.out.println(textFinal.toString());
		return stringBuffer.toString();
	}
	public static String convertStringToInteger(String text) {
		Map<String, Integer> map = new HashMap<String, Integer>() {
			{
				put("A", 1);
				put("B", 2);
				put("C", 3);
				put("D", 4);
			}
		};
		
		StringBuffer StringBuffer = new StringBuffer("");
		String[] words = text.trim().split("\\s");
		for (String word : words) {
			String[] literals = word.split("");
			for (String literal : literals) {
				if(literal.isEmpty()) {
					continue;
				}
				Integer value = map.get(literal);
				StringBuffer.append(value);
			}
			StringBuffer.append(" ");
		}
		System.out.println(StringBuffer.toString());
		return StringBuffer.toString();
	}
	
	public static String splitAndSubstring(String text, String fieldName) {
		System.out.println(System.currentTimeMillis());
		String[] myArrays = text.trim().split("\\|");
		for (String string : myArrays) {
			String name = string.substring(0, string.indexOf("="));
			if(name.equalsIgnoreCase(fieldName)) {
				System.out.println(System.currentTimeMillis());
				System.out.println(string.substring(string.indexOf("=") + 1).trim());
				return string.substring(string.indexOf("=") + 1).trim();
			}
		}
		System.out.println(System.currentTimeMillis());
		return null;
	}
	
	public static String splitAndSplit(String text, String fieldName) {
		System.out.println(System.currentTimeMillis());
		String[] myArrays = StringUtils.split(text, "|");
		for(int i = myArrays.length - 1; -1 < i && i < myArrays.length; i--) {
			String[] myHero = StringUtils.split(myArrays[i], "=");;
			if(myHero[0].equalsIgnoreCase(fieldName)) {
				System.out.println(System.currentTimeMillis());
				System.out.println(myHero[1]);
				return myHero[1];
			}
		}
		System.out.println(System.currentTimeMillis());
		return null;
		
	}
	
	public static String counterString(String text) {
		Map<String, Integer> myMap = new HashedMap<String, Integer>();
		String[] myArrays = text.trim().split("\\s");
		for (String string : myArrays) {
			if(string == null) {
				continue;
			}
			Integer value = myMap.get(string);
			if(value == null) {
				myMap.put(string, 1);
			} else {
				myMap.put(string, ++value);
			}
		}
		
		for (Map.Entry<String, Integer> entry : myMap.entrySet()) {
			String name = entry.getKey();
			int value = entry.getValue();
			System.out.println(name + "-" + value);
		}
			
		return null;
	}
	
}
