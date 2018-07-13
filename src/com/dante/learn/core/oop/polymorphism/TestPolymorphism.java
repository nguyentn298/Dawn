package com.dante.learn.core.oop.polymorphism;

public class TestPolymorphism {

	public static void main(String[] args) {
		MyTest();
	}

	public static void MyTest() {
		String[] managers = new String[2];
		String[] employees = managers;
		
		employees[0] = "employees1";
		employees[1] = "employees2";
		
		for (String string : managers) {
			System.out.println(string);
		}
		
		for (String string : employees) {
			System.out.println(string);
		}
	}
}
