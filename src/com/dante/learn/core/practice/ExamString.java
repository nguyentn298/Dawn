package com.dante.learn.core.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExamString {
	public static void main(String[] args) {
//		exam1();
		exam1ForOptimize();
//		exam2();
//		exam3();
	}
	
	public static void exam1() {
		// AABSSSDABB ==> 2AB3SA2B
		String test = "AABSSSDABB";
		String testABC = test.replace("S", "").replace("D", "");
		System.out.println(testABC);
		
		String[] strs = test.split("");
		for (String string : strs) {
			System.out.println(string);
		}
		List<String> target = new ArrayList<String>();
		for (String string : strs) {
			if(!string.equals("D") && !string.isEmpty()) {
				target.add(string);
			}
		}
		System.out.println("My list 1: " + target);
		int count = 1;
		StringBuffer strBuffer = new StringBuffer();
		for(int i = target.size() - 1; i < target.size() && i >= 0; i--) {
			// AABSSSABB
			
			if(i == 0 && target.get(i).equals(target.get(i+1))){
				strBuffer.append(count + target.get(i));
				continue;
			}
			if(target.get(i).equals(target.get(i-1))){
				count++;
			} else{	
				strBuffer.append(count + target.get(i) + ",");
				count = 1;
			}
		}
		
		String[] str = strBuffer.toString().split(",");
		for (String string : str) {
			System.out.println("last" + string);
		}
	
		String result = "";
		for(int i = str.length-1; 0 <= i && i < str.length + 1; i--) {
			result += str[i];
		}
		System.out.println("My result : " + result.replace("1", ""));
		
	}
	
	public static void exam2() {
		// "      I      am    a Programmer      " ->"I am a programmer".
		String test = "      I      am    a Programmer      ";
		System.out.println("my string: " + test.trim());
		String[] strs = test.trim().split(" ");
		StringBuffer strBuffer = new StringBuffer("");
		for (int i = 0; i <  strs.length; i++) {
			System.out.println(strs[i]);
			if(!strs[i].isEmpty()) {
				strBuffer.append(strs[i]);
			}
			
			if(!strs[i].isEmpty() && i != strs.length - 1) {
				strBuffer.append(" ");
			}
			
		}
		System.out.println(strBuffer.toString());
		
	}
	
	public static void exam1ForOptimize() {
		// AABSSSDABB ==> 2AB3SA2B
		String test = "AABSSSDADDDDBB";
		String test1 = "";
		if(test.contains("")) {
			test1 = test.replace("D", "").trim();
		}
		
		//AABSSSABB
		System.out.println(test1);
		
		String[] strs = test1.trim().split("");
		List<String> list = new ArrayList<String>();
		for (String string : strs) {
			if(!string.isEmpty()) {
				list.add(string);
			}
		}
		System.out.println("list1: " + list);
		List<String> list2 = new ArrayList<String>();
		int count = 1;
		
		// A A B S S S A B B
		for(int i = list.size() - 1; 0 <= i && i < list.size(); i--) {
			
			if(i == 0 && list.get(i).equals(list.get(i+1))) {
				list2.add(count + list.get(i));
				continue;
			}
			if(list.get(i).equals(list.get(i-1))) {
				count++;
			} else {
				list2.add(count + list.get(i));
				count = 1;
			}
		}
		// [2B, 1A, 3S, 1B, 2A]
		System.out.println(list2);
		String result = "";
		for (int i = list2.size() - 1; 0 <= i && i < list2.size(); i--) {
			result += list2.get(i);
		}
		System.out.println(result);
	}
	
	public static void exam3() {
		// nguyen
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < i; j++) {
				System.out.println("*");
			}
			System.out.println("\n");
		}
		
	}
}
