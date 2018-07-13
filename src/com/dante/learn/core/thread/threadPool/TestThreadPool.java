package com.dante.learn.core.thread.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadPool {
	public static void main(String[] args) {
		// creating a  pool of 5 threads
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 1; i < 11; i++) {
			Runnable worker = new WorkerThread("" + i);
			
			// calling execute method of ExecutorService
			executor.execute(worker);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}

		System.out.println("Finished all threads");
	}
}
