package com.dante.learn.core.oop.basic;

public class CompareSomething {
	public static void main(String[] args) {
		compareBoxingAndUnboxing();

	}

	public static void compareString() {
		String a = new String("test");
		String b = "test";
		String c = a;
		String d = b;

		System.out.println("a == b: " + (a == b));// false --> wrong stored location, b is created and store in string
													// pool, a is new object
													// and out string pool
		System.out.println("a == c: " + (a == c));// true --> same stored location, same refererence to object created
		System.out.println("a == d: " + (a == d));// false --> wrong stored location, d refer to b value in string pool,
													// a is new object
													// and out string pool

		System.out.println("b == c: " + (b == c));// false --> wrong stored location, b in string pool, c refer to same
													// location of a
		System.out.println("b == d: " + (b == d));// true --> same stored location, same refererence to string pool

		System.out.println("c == d: " + (c == d));// false --> wrong stored location, d refer to same location with b in
													// pool, c refer to a object (outside with pool)
	}

	public static void compareBoxingAndUnboxing() {

		/**
		 * Test with value range from -128 to 127 Note: if value in the range above,
		 * jdk(java.lang) had already an array of the values.
		 */
		Integer a = new Integer(50);
		Integer b = Integer.valueOf(50); // It's like b = Integer.valueOf(50)

		Integer c = a;
		Integer d = b;

		int e = 50;
		Integer f = 50; // this value is already in IntegerCache.cache

		System.out.println("a == b: " + (a == b));// false
		System.out.println("a == c: " + (a == c));// true
		System.out.println("a == d: " + (a == d));// false

		System.out.println("b == c: " + (b == c));// false
		System.out.println("b == d: " + (b == d));// true

		System.out.println("c == d: " + (c == d));// false

		System.out.println("a == e: " + (a == e));// true
		System.out.println("b == e: " + (b == e));// true

		System.out.println("a == f: " + (a == f));// false
		System.out.println("b == f = 50: " + (b == f));// true --> IntegerCache.cache has the value

		b = 130;
		f = 130;

		System.out.println("b == f = 130: " + (b == f));// false --> IntegerCache.cache[] dont have this value (130), so
														// it will create new object follow: 
														// b = new Integer(130)
														// f = new Integer(130)

	}
	
}
