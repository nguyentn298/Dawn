package com.dante.learn.core;

public class TestBinary {
	public static void main(String[] args) {
		String myNumber = convertToBinary(10);
		
		// revert
		String revert = new StringBuffer(myNumber).reverse().toString();
		
		// result
		int result = convertToNumber(revert);
		System.out.println(result);
		
	}
	
	public static String convertToBinary(int number) {
		String split = "";
		while( number > 0 ) {
			int i = number%2;
			split += i;
			number = number/2;
		}
		
		String result = new StringBuffer(split).reverse().toString(); 
		System.out.println(result);
		return result;
	}
	
	public static int convertToNumber(String str) {
		String[] binary = str.trim().split("");
		int number = 0;
		for(int i = 0; i < binary.length; i++) {
			
			if(!binary[i].isEmpty()) {
				int j = Integer.parseInt(binary[i]);
				number = number * 2 + j;
			}
		}
		return number;
	}
	
}
