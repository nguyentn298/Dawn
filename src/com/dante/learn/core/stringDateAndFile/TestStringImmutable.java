package com.dante.learn.core.stringDateAndFile;

public class TestStringImmutable {
	
	public static void main(String[] args) {
		/**
		 * Security and String pool being primary reason of making String immutable
		 * Read more: http://javarevisited.blogspot.com/2010/10/why-string-is-immutable-or-final-in-java.html#ixzz4xdcdtazW
		 */
		
		// String is immutable and cannot change its value.
		String a = "Hello";
		a.concat(" World!!!");
		System.out.println("Immutable String: " + a);
		
		// just change its reference
		a = a.concat(" World!!!");
		System.out.println("Changed reference: " + a);
		
		// primitive type is mutable
		int c = 1;
		++c;
		System.out.println(c);
	}
	
}
