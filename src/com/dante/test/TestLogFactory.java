package com.dante.test;

import com.dante.util.LogUtil;

public class TestLogFactory extends LogUtil{
	public static void main(String[] args) {
		TestLogFactory test = new TestLogFactory();
		test.printName();
	}
	
	public void printName() {
		System.out.println(getClass());
		System.out.println( getClass().getResource("/com/dante/test/Windowbuilder.java"));
	}
}
