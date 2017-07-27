package com.dante.learn.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class TestCollection {
	public static void main(String[] args) {
//		testQueue();
//		convertMapToList();
//		getIndexOfList();
//		testQueue();
//		compareMap();
//		joinList();
//		convertListToArray();
		
//		Map<String, String> mapA = new HashMap<String, String>() {
		Map<String, String> mapA = new TreeMap<String, String>() {
			{
				put("xyz", "Jarvan");
				put("cde", "Garen");
				put("abc", "Xinzhao");
				put("hehe", "AAAA");
			}
		};
		
		System.out.println(mapA);
		for (Map.Entry<String, String> element : mapA.entrySet()) {
			System.out.println(element.getKey() + " - " + element.getValue());
		}
		
		// check enum contans map
//		for (OutputFileOption option : OutputFileOption.values()) {
//			Boolean isExist = options.get(option);
//			if(isExist == null) {
//				options.put(option, false);
//			}
//		}
	}
	
	public static void joinList() {
		List<String> list1 = new ArrayList<String>() {
			{
				add("1");
				add("2");
				add("3");
			}
		};
		
		Set<String> list2 = new HashSet<String>() {
			{
				add("6");
				add("4");
				add("9");
			}
		};
//		List<String> newList = new ArrayList<String>(list1);
		list1.addAll(list2);
		
		for (String string : list1) {
			System.out.println(string);
		}
	}
	
	public static void compareMap() {
		Map<String, String> mapA = new HashMap<String, String>() {
			{
				put("2", "Jarvan");
				put("3", "Garen");
				put("1", "Xinzhao");
				put("4", "AAAA");
			}
		};
		
		Map<String, String> mapB = new HashMap<String, String>() {
			{
				put("2", "Jarvan");
				put("3", "Garen");
				put("1", "Xinzhao");
				put("5", "BBB");
			}
		};
		Set<String> keysInA = new HashSet<String>(mapA.keySet());
		Set<String> keysInB = new HashSet<String>(mapB.keySet());
		Set<String> inANotB = new HashSet<String>(keysInA);
		inANotB.removeAll(keysInB);
		
		System.out.println("inANotB: " + inANotB);
		System.out.println(mapA);
		
		for (String string : inANotB) {
			mapA.remove(string);
		}
		System.out.println(mapA);
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
		System.out.println("My List: " + list);
	}
	
	public static void convertListToArray() {
		List<String> list = new ArrayList<String>() {
			{
				add("test3");
				add("test9");
				add("test5");
			}
		};
		String[] strs = list.toArray(new String[list.size()]);
		System.out.println(strs);
		StringBuffer loanBatchIds = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			if(i == list.size() - 1) {
				loanBatchIds.append(list.get(i));
				break;
			}
			loanBatchIds.append(list.get(i)).append(",");
		}
		System.out.println("Buffer: " + loanBatchIds);
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
