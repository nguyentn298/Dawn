package com.dante.learn.core;


public class TestReturn {
	public static void main(String[] args) {
		String a = "this is my String";
		String b = "is my";
		printString(a, b);
		
	}
	
	// If the method returns void then return; just exits out 
	// of the method at that statement, not running the following statements.
	
	//  it returns nothing
	public static void printString(String a, String b) {
		
		if(!a.contains(b)) {
			System.out.println("false");
			return;
		} else {
			System.out.println("true");
		}
		
		System.out.println("if false, i'm invisible because 'return;;'");
	}
}
