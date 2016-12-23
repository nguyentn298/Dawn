package com.dante.learn.core;

public class TestClass<T> {
	static void printClassName(Object obj) {
		System.out.println("The class of " + obj + " is " + obj.getClass().getName());
		System.out.println();
		System.out.println("This is obj = " + obj);
		System.out.println("This is get Class = " + obj.getClass());
		System.out.println("This is get Class Name = " + obj.getClass().getName());
		System.out.println("This is get super generic = " + obj.getClass().getGenericSuperclass());
		System.out.println();
	}
	
	public static void main(String[] args) {
		printClassName(new TestClass());
	}
}
