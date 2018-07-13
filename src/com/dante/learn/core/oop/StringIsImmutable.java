package com.dante.learn.core.oop;

public class StringIsImmutable {
	public static void main(String[] args) {
		String a = "1";
		a += 2;
		System.out.println(a);
		
		int b = 1;
		b += 2;
		int c = b + 2;
		System.out.println(b);
		System.out.println(c);
	}
}
