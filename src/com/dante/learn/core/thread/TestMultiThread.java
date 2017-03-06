package com.dante.learn.core.thread;

public class TestMultiThread {
	public static void main(String[] args) {
		System.out.println();
		
		firstThread ft = new firstThread();
		secondThread st = new secondThread();
		
		firstThread ft2 = new firstThread();
		ft.start();
		ft2.start();
		
		
//		try {
//			ft.join();
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
		// using sleep() when run parallel another thread
//		while(ft.isAlive()) {
//			try {
//				System.out.println("Main Thread is slept");
//				Thread.sleep(1500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		String result = "";
//		for(int i = 0; i < 10; i++) {
//			try {
//				for(int k = 0; k < 10; k++) {
//					System.out.print(" ");
//				}
//				System.out.println("|");
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//			
//		}
//		
//		st.start();
//		try {
//			st.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println("Done!!!");
		
	}
}

class firstThread extends Thread{
	
	public firstThread() {
		super("my extending thread ");
		System.out.println("my thread created " + this);
	}
	
	public void run() {
		
		String result = "";
		for(int i = 0; i < 11; i++) {
			try {
				System.out.print("*");
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(result);
		
	}
}

class secondThread extends Thread{
	public void run() {
		String result = "";
		for(int i = 0; i < 11; i++) {
			try {
				System.out.print("*");
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(result);
	}
}