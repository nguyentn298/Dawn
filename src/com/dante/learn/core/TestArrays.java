package com.dante.learn.core;

import java.util.Arrays;

public class TestArrays {
	public static void main(String args[]) throws Exception {
		int array[] = { 2, 5, -2, 6, -3, 8, 0, -7, -9, 4 };
		Arrays.sort(array);
		printArray("Sorted array", array);
		
		// Array's must be sort to binarySearch
		int index = Arrays.binarySearch(array, 1);
		// it's not found, because arrays doesn't have the number
		System.out.println("position: " + index);
		
		int newIndex = -index - 1;
		array = insertElement(array, 699, newIndex);
		printArray("With 1 added", array);
		
//		int[] test1 = {0, 1, 3, 6, 7};
//		int[] test2 = new int[test1.length+5];
//		System.out.println("Before copy");
//		for (int i : test2) {
//			System.out.println("Before: " + i);
//		}
//		
//		System.arraycopy(test1, 0, test2, 0, 2);
//		test2[2] = 2;
//		System.out.println("after first copy");
//		for (int i : test2) {
//			System.out.println("After: " + i);
//		}
//		
//		System.arraycopy(test1, 2, test2, 3, 1);
//		System.out.println("after second copy");
//		for (int i : test2) {
//			System.out.println("After: " + i);
//		}
//		test2[4] = 4;
//		test2[5] = 5;
//		
//		System.arraycopy(test1, 3, test2, 6, 2);
//		System.out.println("after third copy");
//		for (int i : test2) {
//			System.out.println("After: " + i);
//		}
		
	}

	private static void printArray(String message, int array[]) {
		System.out.println(message + ": [length: " + array.length + "]");
		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				System.out.print(", ");
			}
			System.out.print(array[i]);
		}
		System.out.println();
	}

	private static int[] insertElement(int original[], int element, int index) {
		int length = original.length;
		int destination[] = new int[length + 1];
		System.arraycopy(original, 0, destination, 0, index);
		destination[index] = element;
		System.arraycopy(original, index, destination, index + 1, length - index);
		return destination;
	}
}
