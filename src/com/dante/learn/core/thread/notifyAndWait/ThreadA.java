package com.dante.learn.core.thread.notifyAndWait;

public class ThreadA {
	public static void main(String[] args) {
		ThreadB b = new ThreadB();
		b.start();

		synchronized (b) {
			try {
				System.out.println("Waiting for b to complete...");
				b.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("Total is: " + b.total);
		}
		
//		System.out.println("Total is: " + b.total);
	}
}

class ThreadB extends Thread {
	int total;

	@Override
	public void run() {
		synchronized (this) {
			for (int i = 0; i < 10000; i++) {
				total += i;
			}
			notify();
		}
	}
}
