package com.dante.learn.core.thread;

public class CalcPI1WithJoin {
	public static void main(String[] args) {
		
		
//		To quote from the Thread.join() method javadocs:
//
//		Waits for this thread to die.
//		There is a thread that is running your example code which is probably the main thread.
//
//		1/ The main thread creates and starts the t1 and t2 threads. The two threads start running in parallel.
//		2/ The main thread calls t1.join() to wait for the t1 thread to finish.
//		3/ The t1 thread completes and the t1.join() method returns in the main thread.
//		4/ The main thread calls t2.join() to wait for the t2 thread to finish.
//		5/ The t2 thread completes (or completed before the t1 thread did) and the t2.join() method returns in the main thread.
//		It is important to understand that the t1 and t2 threads have been running in parallel but the main thread that started them needs to wait for them to finish before it can continue. 
//		That's a common pattern.
//
//		Question: t1.join() means cause t2 to stop until t1 terminates?
//		No. The main thread that is calling t1.join() will stop running and wait for the t1 thread to finish. 
//		The t2 thread is running in parallel and is not affected by t1 or the t1.join() call at all.
//
//		In terms of the try/catch, the join() throws InterruptedException meaning that the main thread that is calling join() may itself be interrupted by another thread.
//		
		MyThread3 mt = new MyThread3();
		MyThread4 mt4 = new MyThread4();
		mt.start();
		mt4.start();
		try {
			mt4.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("number: " + mt.pi);
		System.out.println("number 4: " + mt4.pi);
	}
}

class MyThread3 extends Thread {
	boolean negative = true;
	double pi; // Initializes to 0.0, by default

	public void run() {
		for (int i = 3; i < 100000; i += 2) {
			if (negative)
				pi -= (1.0 / i);
			else
				pi += (1.0 / i);
			negative = !negative;
		}
		pi += 1.0;
		pi *= 4.0;
		System.out.println("Finished calculating PI");
	}
}

class MyThread4 extends Thread {
	boolean negative = true;
	double pi; // Initializes to 0.0, by default

	public void run() {
		for (int i = 3; i < 5; i += 2) {
			if (negative)
				pi -= (1.0 / i);
			else
				pi += (1.0 / i);
			negative = !negative;
		}
		pi += 1.0;
		pi *= 4.0;
		System.out.println("Finished calculating PI");
	}
}
