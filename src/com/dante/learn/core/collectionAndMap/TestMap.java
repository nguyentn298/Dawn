package com.dante.learn.core.collectionAndMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

public class TestMap {

	/**
	 * TreeMap is ordered map
	 */
	@Test
	public void testTreeMap() {
		Map<String, String> mapA = new TreeMap<String, String>() {
			{
				put("xyz", "Jarvan");
				put("cde", "Garen");
				put("abc", "Xinzhao");
				put("hehe", "AAAA");
			}
		};

		System.out.println("Map" + mapA);
		for (Map.Entry<String, String> element : mapA.entrySet()) {
			System.out.println(element.getKey() + " - " + element.getValue());
		}
	}

	@Test
	public void iteratorOfMap() {
		Map<Integer, String> map = new HashMap<Integer, String>() {
			{
				put(2, "Jarvan");
				put(3, "Garen");
				put(1, "Xinzhao");
			}
		};

		/**
		 * Iterator with values
		 */
		for (Iterator<String> iterator = map.values().iterator(); iterator.hasNext();) {
			String str = iterator.next();
			System.out.println("str: " + str);
		}

		/**
		 * Iterator with key
		 */
		for (Iterator<Integer> iterator = map.keySet().iterator(); iterator.hasNext();) {
			Integer i = iterator.next();
			System.out.println("Integer: " + i);
		}

		/**
		 * Iterator with Map:
		 * Use "while" operator run fast better than "for" operator
		 */
		System.out.println("Start for: " + System.currentTimeMillis());
		for (Iterator<Map.Entry<Integer, String>> map1 = map.entrySet().iterator(); map1.hasNext();) {
			Map.Entry<Integer, String> map2 = map1.next();
			System.out.println("Use for - Key: " + map2.getKey() + " value: " + map2.getValue());
		}
		System.out.println("Finish for: " + System.currentTimeMillis());
		System.out.println("Start while: " + System.currentTimeMillis());
		Iterator<Map.Entry<Integer, String>> map1 = map.entrySet().iterator(); 
		while (map1.hasNext()) {
			Map.Entry<Integer, String> map2 = map1.next();
			System.out.println("Use While - Key: " + map2.getKey() + " value: " + map2.getValue());
		}
		System.out.println("Finish while: " + System.currentTimeMillis());
	}

	@Test
	public void convertMapToList() {
		Map<Integer, String> map = new HashMap<Integer, String>() {
			{
				put(2, "Jarvan");
				put(3, "Garen");
				put(1, "Xinzhao");
			}
		};
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			System.out.println("iterator.next(): " + iterator.next());
			Map.Entry element = (Map.Entry) iterator.next();
			System.out.println("Map: " + element.getKey() + "--" + element.getValue());
		}
		// list of key
		List<Integer> keyList = new ArrayList<Integer>(map.keySet());
		for (Integer number : keyList) {
			System.out.println("Key of Map: " + number);
		}

		// list of value
		List<String> list = new ArrayList<String>(map.values());
		for (String string : list) {
			System.out.println("Value of Map: " + string);
		}
		System.out.println("My List: " + list);
	}

	@Test
	public void removeValuesMapFromMap() {
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
				put("6", "CCC");
				put("5", "BBB");
			}
		};
		Set<String> keyMapA = new HashSet<String>(mapA.keySet());
		Set<String> keyMapB = new HashSet<String>(mapB.keySet());
		Set<String> inANotB = new HashSet<String>(keyMapA);
		inANotB.removeAll(keyMapB);

		System.out.println("MapA: " + mapA);
		System.out.println("MapB: " + mapB);
		System.out.println("in A Not B: " + inANotB);

	}
}
