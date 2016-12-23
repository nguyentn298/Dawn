package com.dante.learn.core;

public class TestBoxingAndUnboxing {
	public static void main(String[] args) {
		Integer a = 5;
		Integer b = 5;
		Integer c = new Integer(5);
		Byte d = 5;
		System.out.println(a.equals(b));
		System.out.println(a.equals(c));
		System.out.println(a.equals(d));
	}
}
