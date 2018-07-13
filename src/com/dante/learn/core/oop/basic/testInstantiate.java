package com.dante.learn.core.oop.basic;

public class testInstantiate {
	public static void main(String[] args) {

		/**
		 * Class and instance variable have default value Local variable dont have
		 * default value
		 */
		TestDefaultInitialise test = new TestDefaultInitialise();
		System.out.println(test.getB());

		/**
		 * The object is not in the variable, the variable is just hold a reference to
		 * object
		 */
		ObjectAndVariable test1 = new ObjectAndVariable(); // Create a object and assign reference (address) of the
															// object to test1 variable

		@SuppressWarnings("unused")
		ObjectAndVariable test2 = new ObjectAndVariable(); // Create a object and assign reference (address) of the
															// object to test2 variable
		ObjectAndVariable test3 = test1; // copy reference of test1 to test3 (it will refer to same object on heap)

		@SuppressWarnings("unused")
		ObjectAndVariable test4 = null; // no object created, or no reference, homeless.

		/**
		 *  
		 */
		test1.name = "test1111";
		System.out.println("test3.name: " + test3.name); // test3.name dont have any assignment, but test3.name has same value of
										// test1.name
										// Because test 1 and test 3 have reference,
										// it's in same house, The house hav a pool, test1 have a pool, test 2 have a
										// pool
										// test1 buy a tive, test 1 can watch the tivi, test2 can watch the tivi
										// say oh yeah:))

		final ObjectAndVariable test5 = new ObjectAndVariable();
		test5.name = "test5555";
		System.out.println("test5.name: " + test5.name); // test5 has final modifier, but test5 contain reference of object,
										// Object's not in test5
										// So, test 5 has just reference (address), so test5 don't have any permission
										// to change or not of the object

		ObjectAndVariable test6 = new ObjectAndVariable();
		System.out.println("test6.name: " + test6.name);
		test6.name = "test6666";
		System.out.println("test6.name: " + test6.name);
		test6.setName("test6 setMethod");
		System.out.println("test6.name: " + test6.name);
		/**
		 * Note: The expression greeting == "Hello" tests whether greeting and "Hello"
		 * contain the same characters stored in the same memory location
		 * 
		 */
		String greeting = "Hello"; // like String greeting2 = new String("Hello"); and greeting2.intern()

		if (greeting == "Hello") {
			System.out.println(true);
		} else {
			System.out.println(false);
		}

		if (greeting.equals("Hello")) {
			System.out.println(true);
		} else {
			System.out.println(false);
		}

		String greeting2 = new String("Hello");
		if (greeting2 == "Hello") {
			System.out.println(true);
		} else {
			System.out.println(false);
		}

		if (greeting2.equals("Hello")) {
			System.out.println(true);
		} else {
			System.out.println(false);
		}

		int a = 10;
		change(a);
		System.out.println(a);

		String str = "test".concat("hehe");
		str.concat("hihi");
		System.out.println("str: " + str);

		int b = 10;
		b++;
		System.out.println("b: " + b);
	}

	public static void change(int x) {
		System.out.println("x1: " + x);
		x = 5;
		System.out.println("x2: " + x);
	}

}
