package com.dante.learn.core.collectionAndMap;

import java.util.Arrays;

public class TestArrays {
	public static void main(String args[]) throws Exception {
		testMultidimensionalArray();
	}

	public static void testFill() {
		int[] a = new int[9];
		Arrays.fill(a, 6);
		for (int i : a) {
			System.out.println(i);
		}
	}
	public static void testSortAndSearch() {
		 int array[] = { 2, 5, -2, 6, -3, 8, 0, -7, -9, 4 };
		 printArray("orginal array", array);
		 
		 Arrays.sort(array);
		 printArray("Sorted array", array);
		
		 // Array's must be sort to binarySearch
		 int index = Arrays.binarySearch(array, 1);
		 
		 // it's not found, because arrays doesn't have the number
		 System.out.println("index: " + index);
		
		 int newIndex = -index - 1;
		 System.out.println("newIndex: " + newIndex);
		 
		 array = insertElement(array, 699, newIndex);
		 printArray("With 1 added", array);
	}

	public static void copyArrayByArrays() {
		int[] a = { 1, 2, 3, 4, 5, 6 };
		int[] a2 = { 1, 2, 3, 4, 5, 6 };

		System.out.println(Arrays.equals(a, a2));
		/**
		 * Copy to new lenght array with all value of old array
		 */
		int[] b = Arrays.copyOf(a, 10);
		for (int i : b) {
			System.out.println("copyOf: " + i);
		}

		/**
		 * Copy to specific range of old array
		 */
		int[] c = Arrays.copyOfRange(a, 2, 4);
		for (int i : c) {
			System.out.println("Test copyOfRange: " + i);
		}

		/**
		 * Copy to specific range of new array to range of old array
		 * 
		 */
		int[] d = new int[9];
		System.arraycopy(a, 3, d, 4, 2);
		for (int i : d) {
			System.out.println("Test System.arraycopy: " + i);
		}

		/**
		 * Custom copy
		 */

		String[] test = new String[7];
		test[0] = "test0";
		test[1] = "test1";
		test[2] = "";
		test[3] = "";
		test[4] = "test4";
		test[5] = "";
		test[6] = "test6";

		String array = "";
		for (int i = 0; i < test.length; i++) {
			if (test[i] != "") {
				if (i == test.length - 1) {
					array += test[i];
					break;
				}
				array += test[i] + ",";
			}
		}

		String[] array2 = array.split(",");
		for (String string : array2) {
			System.out.println(string);
		}
	}

	private static int[] insertElement(int[] original, int element, int index) {
		int length = original.length;
		int destination[] = new int[length + 1];

		// Copy from 0 --> index (exclusive)
		System.arraycopy(original, 0, destination, 0, index);
		
		// Assign value at index position
		destination[index] = element;
		
		// Copy from index + 1 --> original.length - index (cause end - mid)
		System.arraycopy(original, index, destination, index + 1, length - index);
		
		return destination;
	}
	
	private static void printArray(String message, int array[]) {
		System.out.println(message + ": [length: " + array.length + "]");
		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				System.out.print(", ");
			}
			System.out.print(array[i]);
		}
		System.out.println();System.out.println();

	}

	public static void testMultidimensionalArray() {
		String[][] a = {
				{"Mr. ", "Mrs. ", "Ms. "},
				{"A", "B", "C"},
		};
		System.out.println(a[0][0] + a[1][1]);
	}
}
