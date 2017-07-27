package com.dante.learn.core;


public class TestReturn {
	public static void main(String[] args) {
//		String a = "this is my String";
//		String b = "is my";
//		printString(a, b);
		
		String[] test = new String[7];
		test[0] = "test0";
		test[1] = "test1";
		test[2] = "";
		test[3] = "";
		test[4] = "test4";
		test[5] = "";
		test[6] = "test6";
	
		String array = "";
		for (int i = 0; i < test.length; i++) {
			if(test[i] != "") {
				if(i == test.length - 1) {
					array += test[i];
					break;
				} 
				array += test[i] + "," ;
			}
		}
		
		String[] array2 = array.split(",");
		for (String string : array2) {
			System.out.println(string);
		}
		
		
		
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
