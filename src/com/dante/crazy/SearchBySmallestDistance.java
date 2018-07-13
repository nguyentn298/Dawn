package com.dante.crazy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

public class SearchBySmallestDistance {

	public static void main(String[] args) {

		operator("cat", "chicken");

	}

	public static void operator(String inputA, String inputB) {
		String[] str = { "cat", "dog", "bird", "fish", "cat", "duck", "chicken", "dog" };
		List<String> list = Arrays.asList(str);
		System.out.println("My List: " + list);

		List<Integer> listA = new ArrayList<Integer>();
		List<Integer> listB = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			if (!list.contains(inputA) && !list.contains(inputB)) {
				System.out.println("Your input is invalid!");
				return;
			} else {
				if (list.get(i).contains(inputA)) {
					listA.add(i);
				} else if (list.get(i).contains(inputB)) {
					listB.add(i);
				}
			}
		}

		int expectedDistance = 0;
		Map<Integer, String> map = new HashedMap<>();
		for (int i = 0; i < listA.size(); i++) {
			for (int j = 0; j < listB.size(); j++) {
				int c = listA.get(i) - listB.get(j);
				if (c < 0) {
					c = -c;
				}
				if (i == 0 && j == 0) {
					expectedDistance = c;
					map.put(c, listA.get(i) + " and " + listB.get(j));
					continue;
				}

				if (c <= expectedDistance) {
					expectedDistance = c;
					map.put(c, listA.get(i) + " and " + listB.get(j));
				}
			}
		}

		System.out.println("Smallest Distance: " + expectedDistance);
		System.out.println("Index: " + map.get(expectedDistance));
		

	}

}
