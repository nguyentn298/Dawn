package com.dante.english;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class MyEnglish1 {

	public static void main(String[] args) {
		String myEng = "hood";
		// String myVn = engToVn().get(myEng);
		// System.out.println(myEng + ": " + myVn);
		vietnam();
	}

	public static Map<String, String> engToVn() {
		Map<String, String> dictionary = new HashMap<>();
		dictionary
				.put("prone",
						" It’s error prone and confusing – mostly when using greater-than or less-than operators.");
		dictionary
				.put("hood",
						"noun: a covering for the head and neck with an opening for the face, typically forming part of a coat or sweatshirt"
								+ " \n- verb: put a hood on or over.");
		dictionary.put("anual", "hang nam");
		dictionary.put("bleed", "do mau");
		dictionary.put("midget", "small person");
		dictionary.put("pant", "hoi hop - pants: quan dai");
		dictionary.put("salvation", "su cuu roi");
		dictionary.put("faith", "long tin, long trung thanh");
		return dictionary;

	}

	public static void vietnam() {
		// List<String> list = Arrays.asList(new String[]{"A", "B", "C"});

		List<String> list = new ArrayList<String>();
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");
		list.add("G");
		list.add("H");
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String t = iterator.next();
			if (t.equalsIgnoreCase("D")) {
				iterator.remove();
			} else {
				System.out.println("Iterator: " + t + "\n");
			}
		}

		for (int i = 0; i < list.size(); i++) {
			String t = list.get(i);
			// if(t.equalsIgnoreCase("D")) {
			// list.remove(i);
			// } else {
			System.out.println("Foreach: " + t + "\n");
			// }

		}

	}

	public void test() {
		List<Integer> numbers = new ArrayList<Integer>();

		// populates list with initial values
		for (Integer i : Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7))
			numbers.add(i);
		printList(numbers); // 0,1,2,3,4,5,6,7

		// replaces each element with twice its value
		for (int index = 0; index < numbers.size(); index++) {
			numbers.set(index, numbers.get(index) * 2);
		}
		printList(numbers); // 0,2,4,6,8,10,12,14

		// does nothing because list is not being changed
		for (Integer number : numbers) {
			number++; // number = new Integer(number+1);
		}
		printList(numbers); // 0,2,4,6,8,10,12,14

		// same as above -- just different syntax
		for (Iterator<Integer> iter = numbers.iterator(); iter.hasNext();) {
			Integer number = iter.next();
			number++;
		}
		printList(numbers); // 0,2,4,6,8,10,12,14

		// ListIterator<?> provides an "add" method to insert elements
		// between the current element and the cursor
		for (ListIterator<Integer> iter = numbers.listIterator(); iter
				.hasNext();) {
			Integer number = iter.next();
			iter.add(number + 1); // insert a number right before this
		}
		printList(numbers); // 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15

		// Iterator<?> provides a "remove" method to delete elements
		// between the current element and the cursor
		for (Iterator<Integer> iter = numbers.iterator(); iter.hasNext();) {
			Integer number = iter.next();
			if (number % 2 == 0) // if number is even
				iter.remove(); // remove it from the collection
		}
		printList(numbers); // 1,3,5,7,9,11,13,15

		// ListIterator<?> provides a "set" method to replace elements
		for (ListIterator<Integer> iter = numbers.listIterator(); iter
				.hasNext();) {
			Integer number = iter.next();
			iter.set(number / 2); // divide each element by 2
		}
		printList(numbers); // 0,1,2,3,4,5,6,7
	}

	public static void printList(List<Integer> numbers) {
		StringBuilder sb = new StringBuilder();
		for (Integer number : numbers) {
			sb.append(number);
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1); // remove trailing comma
		System.out.println(sb.toString());
	}
}
