package com.dante.learn.core.oop.Variables;

public class MyMain {
	public static void main(String[] args) {
//		TestAllVarbiale test = new TestAllVarbiale();
//		String b = TestAllVarbiale.name;
//		System.out.println("b: " +  b);
//		TestAllVarbiale.name = "nguyen222";
//		b = TestAllVarbiale.name; 
//		System.out.println("b: " +  b);
//		
//		
//		Double a = 	1.4e-2;
//		System.out.println(a);
//		
//		char c = ')';
//		System.out.println(c);
		
		String a = "test";
		String b = a;
		a = "test222";
		b = a;
		System.out.println(b);
		
		String c = new String("abc");
		String d = c;
		System.out.println(d);
		
	}
	
	public void getVariable() {
		String id = TestAllVarbiale.name;
	}
}
