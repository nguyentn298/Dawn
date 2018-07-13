package com.dante.learn.core.thread2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCallableAndFuture {
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(10); 		// Get ExecutorService from Executors utility class, thread pool size is 10
		
		List<Future<String>> futures = new ArrayList<Future<String>>(); // create a list to hold the Future object associated with Callable
		
		Callable<String> callable = new MyCallable(); // Create MyCallable instance
		for (int i = 0; i < 50; i++) {
			
			Future<String> future = executor.submit(callable); // submit Callable tasks to be executed by thread pool
			
			futures.add(future);
		}
		for (Future<String> fut : futures) {
			try {
				// print the return value of Future, notice the output delay in console
				// because Future.get() waits for task to get completed
				System.out.println(new Date() + "::" + fut.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		// shut down the executor service now
		executor.shutdown();
	}
}

class MyCallable implements Callable<String> {

	Integer number = 0;
	@Override
	public String call() throws Exception {
//		System.out.println("This is my callable");
		synchronized (number) {
			number++;
		}
		
//		Thread.sleep(2000);
		return Thread.currentThread().getName() + " - " + number;
	}

}
