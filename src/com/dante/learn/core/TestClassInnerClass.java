package com.dante.learn.core;

public class TestClassInnerClass {

	//	this is a private field but it can be get by inner class 
	//	(mean: another class cannot get values of this field because it doesn't getter and setter)
	private int number = 20;
	/*
	 * 	This is inner class, it's able to get private field of outer class
	 */

	class InnerClass {
		public void myString() {
			System.out.println("This is inner class");
			System.out.println("It has private value of outer class: " + number);
		}
	}

	/*
	 * 	This is method of outerclass has access of inner class
	 */

	public void outerString() {
		System.out.println();
		System.out.println("This is a method of outer class");
		
		// Accessing the inner class from the method
		InnerClass in = new InnerClass();
		in.myString();
	}
	
	public void outerInterface(Greeting greeting) {
		System.out.println(greeting.getGreeting() + "everybody");
	}
	public static void main(String[] args) {
		TestClassInnerClass out = new TestClassInnerClass();
		
		// Instantiating the inner class
		TestClassInnerClass.InnerClass in = out.new InnerClass();
		in.myString();
		
		// Or get inner class by method of outer class
		out.outerString();
		
		// Interface is a parameter 
		out.outerInterface(new Greeting() {
			
			@Override
			public String getGreeting() {
				System.out.println();
				return "Hello ";
			}
		});
	}
}

	/* 
	 *	Interface or abstract
	 */

interface Greeting {
	public String getGreeting();
}
