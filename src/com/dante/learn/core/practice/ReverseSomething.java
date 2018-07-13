package com.dante.learn.core.practice;

import java.util.ArrayList;
import java.util.List;

public class ReverseSomething {

	public static void main(String[] args) {
		
		byte a = (byte) 234234234;
		int b = 1;
		byte c = (byte) b;
		int d = c;
		Integer e = Integer.valueOf(200);
		byte f = e.byteValue();
		System.out.println(e);
		char g = '1' + '2';
		System.out.println(g);
		
		char h = '3';
		System.out.println(h);
		
		Long i = 100L;
		Long j = 100L;
		
		System.out.println((i == j) ? true : false);
		
		String test = "abc123";
		System.out.println(test.charAt(345345345));
	}

	public void reverseWithChar() {
		String original = "I Am Not String";
		char[] charArray = original.toCharArray();
		char[] result = new char[charArray.length];
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == ' ') {
				result[i] = ' ';
			}
		}

		int j = 0;
		for (int i = charArray.length - 1; 0 < i && i < charArray.length; i--) {

			if (result[j] == ' ') {
				j++;
			}

			if (charArray[i] == ' ') {
				i--;
			}
			result[j] = charArray[i];
			j++;
		}

		System.out.println(result);
	}
	public void reverseWithOriginalSpace() {
		/**
		 * str1 = "I Am Not String" ==> str2 = "g ni rts toNmAI"
		 */
		String original = "I Am Not String";
		String[] arr = original.split(" ");

		StringBuilder stringBuilder = new StringBuilder();
		String index = "";
		for (int i = 0; i < arr.length; i++) {
			index += arr[i].length() + ",";
			stringBuilder.append(arr[i]);
		}

		String myString = stringBuilder.reverse().toString();
		String[] myIndex = index.split(",");
		String target = "";
		int begin = 0;

		// gnirtStoNmAI
		for (String string : myIndex) {
			if (string.equals("") || string == null) {
				continue;
			}
			int end = Integer.parseInt(string);
			target += myString.substring(begin, begin + end) + " ";
			begin = end;
		}
		System.out.println(target);
	}

	public void reverseArray() {

		/**
		 * arr1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 } ==> arr2 = {10, 9, 8, 7, 6, 5,4
		 * ,3 ,2 ,1}
		 */

		/**
		 * Reverse by StringBuilder
		 */
		int[] arr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		StringBuilder stringBuilder = new StringBuilder();
		int[] target = new int[11];
		for (int i = 10; 0 <= i && i <= arr.length; i--) {
			stringBuilder.append(arr[i] + ",");
		}
		String[] str = stringBuilder.toString().split(",");

		for (int i = 0; i < str.length; i++) {
			target[i] = Integer.parseInt(str[i]);
		}

		for (int number : target) {
			System.out.println("Reverse by StringBuilder: " + number);
		}
		System.out.println();

		/**
		 * Reverse by List
		 */

		List<Integer> list = new ArrayList<>();
		for (int i = 10; 0 <= i && i <= arr.length; i--) {
			list.add(arr[i]);
		}

		Integer[] tager2 = list.toArray(new Integer[11]);
		int a = 0;
		a = (int) tager2[0];
		System.out.println("a: " + a);
		for (Integer integer : tager2) {
			System.out.println("Reverse by List: " + integer);
		}
		System.out.println();

		/**
		 * Reverse by String
		 */

		int[] arr2 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		String str2 = "";
		for (int i = 11; 0 <= i && i <= arr2.length; i--) {
			if (i == 0) {
				break;
			}
			str2 += arr2[i - 1] + ",";
		}

		String[] target2 = str2.split(",");
		for (String i : target2) {
			System.out.println("Reverse by String " + i);
		}
		System.out.println();

		/**
		 * Reverse Array by it self
		 */

		int[] arr4 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		for (int i = 0; i < arr4.length / 2; i++) {
			int lastIndex = arr4.length - 1;

			// i = 3; arr4[3] = 3, temp = 3
			int temp = arr4[i];

			// arr4[3] = arr4[11 - 3 - 1]
			arr4[i] = arr4[lastIndex - i];

			arr4[lastIndex - i] = temp;
		}
		for (int i : arr4) {
			System.out.println("Reverse Array by it self: " + i);
		}

	}
}