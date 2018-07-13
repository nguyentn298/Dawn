package com.dante.learn.core.oop;

public class TestJavap {
	public static void main(String[] args) {
		String a = "IAmNotString";
		StringBuilder b = new StringBuilder(a);
		int[] c = {1, 4, 8};
		for (int i : c) {
			b.insert(i, " ");
		}
		System.out.println(b.toString());
	}
}
