package com.dante.learn;

public class MyChild extends MyParent{
	
	public MyChild() {
//		super();
	}
	
	@Override
	public String test() {
		System.out.println("Child");
		return null;
	}
	
	public String printTest() {
		test();
		this.test();
		return null;
	}
	public static void main(String[] args) {
		MyChild m = new MyChild();
		m.printTest();
	}
}
