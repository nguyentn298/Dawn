package com.dante.learn.core.stringDateAndFile;

import java.util.ArrayList;
import java.util.List;

public class TestStringBuilderAndBuffer {
	public static void main(String[] args) {
		char a = 'a';
		String b = a + "bcdef";
		System.out.println(b.charAt(5));

	
	}
	
	public void insertValue() {
		String input = "I Am Not String";
		char[] arr = input.toCharArray();

		StringBuilder stringBuilder = new StringBuilder("");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == ' ') {
				list.add(i);
				continue;
			}
			stringBuilder.append(arr[i]);
		}
		StringBuilder target = stringBuilder.reverse();
		for (Integer integer : list) {
			target.insert(integer, " ");
		}
		System.out.println(target.toString());
	}
}
