package com.dante.learn.core.thread;

//Implement Runnable Interface...
class ImplementsRunnable implements Runnable {

	// reuse this object, and not create new one
	private int counter = 0;

	public void run() {
		counter++;
		System.out.println("ImplementsRunnable : Counter : " + counter);
	}
}

// Extend Thread class...
class ExtendsThread extends Thread {

	// this is not reuse, and create new one
	private int counter = 0;

	public void run() {
		counter++;
		System.out.println("ExtendsThread : Counter : " + counter);
	}
}

// Use above classes here in main to understand the differences more clearly...
public class ThreadVsRunnable {

	public static void main(String args[]) throws Exception {
//		// Multiple threads share the same object.
//		ImplementsRunnable rc = new ImplementsRunnable();
//		Thread t1 = new Thread(rc);
//		t1.start();
//		Thread.sleep(1000); // Waiting for 1 second before starting next thread
//		
//		// restart this work
//		Thread t2 = new Thread(rc);
//		t2.start();
//		Thread.sleep(1000); // Waiting for 1 second before starting next thread
//		
//		// restart this work
//		Thread t3 = new Thread(rc);
//		t3.start();
//
//		System.out.println();
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				System.out.println("Test Runnable without implement Runnable");
//				
//			}
//		}).start();
//		System.out.println();
//		
////		t4.start();
//		// Creating new instance for every thread access.
//		ExtendsThread tc1 = new ExtendsThread();
//		tc1.start();
//		Thread.sleep(1000); // Waiting for 1 second before starting next thread
//		
//		// Create new this work
//		ExtendsThread tc2 = new ExtendsThread();
//		tc2.start();
//		Thread.sleep(1000); // Waiting for 1 second before starting next thread
//		
//		// Create new this work
//		ExtendsThread tc3 = new ExtendsThread();
//		tc3.start();
		
		   //Multiple threads share the same object.
	    ImplementsRunnable rc = new ImplementsRunnable();
	    Thread t1 = new Thread(rc);
	    t1.start();
	    Thread.sleep(1000); // Waiting for 1 second before starting next thread
	    Thread t2 = new Thread(rc);
	    t2.start();
	    Thread.sleep(1000); // Waiting for 1 second before starting next thread
	    Thread t3 = new Thread(rc);
	    t3.start();

	    //Modification done here. Only one object is shered by multiple threads here also.
	    ExtendsThread extendsThread = new ExtendsThread();
	    Thread thread11 = new Thread(extendsThread);
	    thread11.start();
	    Thread.sleep(1000);
	    Thread thread12 = new Thread(extendsThread);
	    thread12.start();
	    Thread.sleep(1000);
	    Thread thread13 = new Thread(extendsThread);
	    thread13.start();
	    Thread.sleep(1000);
	}
}
