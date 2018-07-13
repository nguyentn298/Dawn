package com.dante.learn.core.collectionAndMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class TestSort {
	public static void main(String[] args) {

		Set<String> unOrderedSet = TestSort.mySet();

	}
	
	// List is an ordered colletion
	// ===================================
	public static List<String> myList() {
		List<String> list = new ArrayList<String>() {
			{
				add("bbb");
				add("aaa");
				add("ddd");
				add("ccc");
			}
		};
		
		// custom sort list
		// ===================================
//		Collections.sort(list, new Comparator<String>() {
//
//			@Override
//			public int compare(String o1, String o2) {
//				return o1.compareTo(o2);
//			}
//			
//		});
		// ===================================
		for (String string : list) {
			System.out.println(string);
		}
		return list;
	}
	
	 /**
		Map is an unordered colletion
		Use TreeMap() instead of HashMap() to order
	 */
	// ===================================
	public static Map<String, Integer> myMap() {
		Map<String, Integer> unOrderMap = new HashMap<String, Integer>() {
			{
				put("dd", (int) (Math.random() * 100));
				put("bb", (int) (Math.random() * 100));
				put("cc", (int) (Math.random() * 100));
				put("aa", (int) (Math.random() * 100));
				put("ff", (int) (Math.random() * 100));
				put("gg", (int) (Math.random() * 100));
				put("hh", (int) (Math.random() * 100));
			}
		};
		
		// Custom sort map
		// ===================================
//		Map<String, Integer> orderMap = new TreeMap<String, Integer>(new Comparator<String>() {
//
//			@Override
//			public int compare(String o1, String o2) {
//				// TODO Auto-generated method stub
//				return o2.compareTo(o1);
//			}
//		});
//		
//		orderMap.putAll(unOrderMap);
		// ===================================

		for (String myKey : unOrderMap.keySet()) {
			System.out.println("My key: " + myKey + " - " + "My Value " + unOrderMap.get(myKey));
		}
		return unOrderMap;
	}
	
	// Set is an unordered colletion
	// Use TreeSet() instead of HashMap() to order
	// ===================================
	public static Set<String> mySet() {
		Set<String> unOrderedSet = new TreeSet<String>() {
			{
				add("bbb");
				add("aaa");
				add("ddd");
				add("ccc");
			}
		};
		
		// Custom sort set
		// ===================================
//		Set<String> orderedSet = new TreeSet<String>(new Comparator<String>() {
//
//			@Override
//			public int compare(String o1, String o2) {
//				return o2.compareTo(o1);
//			}
//		});
//		orderedSet.addAll(unOrderedSet);
		// ===================================
		for (String string : unOrderedSet) {
			System.out.println(string);
		}
		
		return unOrderedSet;
	}
	
}
