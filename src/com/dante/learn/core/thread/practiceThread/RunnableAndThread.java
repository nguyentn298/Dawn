package com.dante.learn.core.thread.practiceThread;

public class RunnableAndThread {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("My Runnable: ");
		
		// Share work
		MyRunnable r = new MyRunnable();
		Thread r1 = new Thread(r);
		Thread r2 = new Thread(r);
		Thread r3 = new Thread(r);
		
		r1.start();
		Thread.sleep(1000);
		
		r2.start();
		Thread.sleep(1000);
		
		r3.start();
		Thread.sleep(1000);
		
		System.out.println("My Thread: ");
		
		// No share
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		
		t1.start();
		Thread.sleep(1000);
		
		t2.start();
		Thread.sleep(1000);
		
		t3.start();
		Thread.sleep(1000);
	}
}

class MyThread extends Thread {
	int count;
	public void run() {
		count++;
		System.out.println(Thread.currentThread() + "" + count);
	}
}

class MyRunnable implements Runnable{
	int count;
	@Override
	public void run() {
		count++;
		System.out.println(Thread.currentThread() + "" + count);
	}
	
}
