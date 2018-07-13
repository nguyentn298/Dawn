package com.dante.learn.core.thread;

public class TestMultiThread {
	public static void main(String[] args) {
		joinTest();
	}
	
	public static void joinTest() {
		Thread t1 = new FirstThread();
		Thread t2 = new SecondThread();
		
		t1.start();
		try {
			// Use join() to execute current Thread until finish
			System.out.println("Start Joinning of Thread 1");
			t1.join();
			System.out.println("Finish Joinning of Thread 1");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Next thread will be started after previous thread finish.
		t2.start();
	}
}

class FirstThread extends Thread{
	
	public FirstThread() {
		super("FirstThread ");
		System.out.println("my thread created " + this);
	}
	
	public void run() {
		
		String result = "";
		for(int i = 0; i < 11; i++) {
			try {
				System.out.println("Thread 1: " + i);
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(result);
		
	}
}

class SecondThread extends Thread{
	
	public SecondThread() {
		super("SecondThread ");
		System.out.println("my thread created " + this);
	}
	public void run() {
		String result = "";
		for(int i = 0; i < 11; i++) {
			try {
				System.out.println("Thread 2: " + i);
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(result);
	}
}