package com.dante.learn.core;

public class TestGenericBoundedType {
	public static <T extends Comparable<T>> void maxResult(T x, T y, T z) {
		T max = x;
		
		if(y.compareTo(max) > 0) {
			max = y;
		}
		
		if(z.compareTo(max) > 0) {
			max = z;
		}
		
		System.out.println("this is max: " + max);
	}
	
	public static void main(String[] args) {
		maxResult(9, 20, 11);
		maxResult("yy", "cc", "dd");
	}
}
