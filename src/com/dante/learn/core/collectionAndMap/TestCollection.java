package com.dante.learn.core.collectionAndMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class TestCollection {
	public static void main(String[] args) {
		// testQueue();
		// convertMapToList();
		// getIndexOfList();
		// testQueue();
		// compareMap();
		// joinList();
		// convertListToArray();

		// check enum contans map
		// for (OutputFileOption option : OutputFileOption.values()) {
		// Boolean isExist = options.get(option);
		// if(isExist == null) {
		// options.put(option, false);
		// }
		// }

	}

	@Test
	public void iteratorForList() {
		List<Integer> list = new ArrayList<Integer>() {
			{
				add(1);
				add(5);
				add(3);
			}
		};
		
		Iterator<Integer> iterator = list.iterator();
		while(iterator.hasNext()) {
			Integer list2 = iterator.next();
			System.out.println(list2);
		}
	}
	
	public static enum TestEnum {
		TEST1, TEST2, TEST3
	}

	@Test
	public void checkEnum() {
		
		// check enum contans map
		Map<TestEnum, Boolean> map = new HashMap<>();
		for (TestEnum option : TestEnum.values()) {
			Boolean isExist = map.get(option);
			if (isExist == null) {
				map.put(option, false);
			}
		}
		
		for (Iterator<Map.Entry<TestEnum, Boolean>> entries = map.entrySet().iterator(); entries.hasNext();) {
			Map.Entry<TestEnum, Boolean> element = entries.next();
			System.out.println(element.getValue());
		}
	}

	@Test
	public void mergeListOrSet() {

		/**
		 * Merge LIST
		 */
		List<String> list1 = new ArrayList<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add("1");
				add("2");
				add("3");
			}
		};

		// Set or List are ok
		Set<String> set = new HashSet<String>() {
			{
				add("6");
				add("4");
				add("9");
			}
		};

		list1.addAll(set);

		for (String string : list1) {
			System.out.println(string);
		}

		/**
		 * Merge SET
		 */
		Set<String> a = new HashSet<String>() {
			{
				add("A");
				add("B");
			}
		};

		System.out.println(a);
		Set<String> b = new HashSet<String>() {
			{
				add("A");
				add("C");
			}
		};

		System.out.println(b);
		a.addAll(b);
		System.out.println(a);
	}

	@Test
	public void getIndexOfList() {
		List<String> list = new ArrayList<String>() {
			{
				add("test3");
				add("test9");
				add("test5");
			}
		};

		String str1 = "test5";
		String str2 = "test10";
		System.out.println("position: " + list.indexOf(str1));
		System.out.println("position: " + list.indexOf(str2));
	}

	@Test
	public void convertListToArray() {
		List<String> list = new ArrayList<String>() {
			{
				add("test3");
				add("test9");
				add("test5");
			}
		};

		String[] strs = list.toArray(new String[list.size()]);

		for (String string : strs) {
			System.out.println("Array value: " + string);
		}

		StringBuffer loanBatchIds = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				loanBatchIds.append(list.get(i));
				break;
			}
			loanBatchIds.append(list.get(i)).append(",");
		}
		System.out.println("Buffer value: " + loanBatchIds);
	}

}
