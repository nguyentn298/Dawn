package com.dante.learn.core.thread.threadLocal;

public class TestThreadLocal {

	
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("=================================");
		Runnable r = new normalThread();
		for(int i = 0; i < 10; i++) {
			Thread t = new Thread(r, "Normal - " + i);
			t.sleep(1000);
			t.start();
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("=================================");
		Runnable r2 = new localThread();
		for(int i = 0; i < 10; i++) {
			Thread t = new Thread(r2, "Local - " + i);
			t.sleep(1000);
			t.start();
		}
	}

}

class normalThread implements Runnable{

	int number = 0;
//	ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
	@Override
	public void run() {
		System.out.println("Thread " + Thread.currentThread().getName() + "(In) : "+ number);
		++number;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Thread " + Thread.currentThread().getName() + "(Out) : " + number);
	}
	
}

class localThread implements Runnable{
	int number = 0;
	ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
	@Override
	public void run() {
		System.out.println("Thread " + Thread.currentThread().getName() + "(In) : "+ threadLocal.get());
		System.out.println("Thread " + Thread.currentThread().getName() + "(In) : "+ number);
		threadLocal.set(++number);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("Thread " + Thread.currentThread().getName() + "(Out) : " + threadLocal.get());
	}
}
