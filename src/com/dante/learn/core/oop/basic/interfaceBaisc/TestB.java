package com.dante.learn.core.oop.basic.interfaceBaisc;

public class TestB implements TestA, TestC{

	String b;
	@Override
	public void run() {
		// cant print a variable
		String a;
		System.out.println(b);
	}

	@Override
	public void stop() {
		
	}

	@Override
	public void start() {
		
	}
	
	public static void main(String[] args) {
		TestB test = new TestB();
		test.run();
	}
}
