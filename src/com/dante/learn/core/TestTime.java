package com.dante.learn.core;

import org.junit.Test;

public class TestTime {
	public static void main(String[] args) {
		System.err.println("Pool did not terminate");
		System.out.println("Pool did not terminate");
		long start = System.currentTimeMillis();
		System.out.println(start);
	}
	
	@Test
	public void testCheckSecondInterval() {
		
		long start = System.currentTimeMillis();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println((System.currentTimeMillis() - start)/(1000));
		
	}
	
	@Test
	public void testCheckMilisecondInterval() {
		long start = System.currentTimeMillis();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
