package com.dante.learn.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TestCollection {
	public static void main(String[] args) {
//		testQueue();
//		convertMapToList();
//		getIndexOfList();
		testQueue();
	}
	
	public static void getIndexOfList() {
		List<String> list = new ArrayList<String>() {
			{
				add("test3");
				add("test9");
				add("test5");
			}
		};
		
		String str1 = "test5";
		String str2 = "test10";
		System.out.println(list.indexOf(str1));
		System.out.println(list.indexOf(str2));
	}
	
	public static void convertMapToList() {
		Map<Integer, String> map = new HashMap<Integer, String>() {
			{
				put(2, "Jarvan");
				put(3, "Garen");
				put(1, "Xinzhao");
			}
		};
		
		List<String> list = new ArrayList<String>(map.values());
		for (String string : list) {
			System.out.println("String: " + string);
		}
	}
	
	public static void testQueue() {
		Queue<String> queueValues = new LinkedList<String>();
		queueValues.add("this");
		queueValues.add("is");
		queueValues.add("a");
		queueValues.add("Cat");
		System.out.println(queueValues);
		
		// Retrieves and removes the head of this queue, or returns null if this queue is empty.
		String a = queueValues.poll();
		System.out.println(queueValues);
	}
}
