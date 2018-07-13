package com.dante.learn.core.thread.ConcurrentAndSynchronizedMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.formula.functions.T;

import javassist.bytecode.Descriptor.Iterator;
 
/**
 * @author Crunchify.com
 *
 */
 
public class CrunchifyConcurrentHashMapVsSynchronizedMap {
 
	public final static int THREAD_POOL_SIZE = 5;
 
	public static Map<String, Integer> testHashMapObject = null;
	public static Map<String, Integer> testHashTableObject = null;
	public static Map<String, Integer> testSynchronizedMapObject = null;
	public static Map<String, Integer> testConcurrentHashMapObject = null;
 
	public static void main(String[] args) throws InterruptedException {
 
		// Test with HashMap Object, it will be hanging cause fail-fast
		testHashMapObject = new HashMap<String, Integer>();
		performTest(testHashMapObject);
		
		// Test with Hashtable Object
		testHashTableObject = new Hashtable<String, Integer>();
//		performTest(testHashTableObject);
 
		// Test with synchronizedMap Object
		testSynchronizedMapObject = Collections.synchronizedMap(new HashMap<String, Integer>());
//		performTest(testSynchronizedMapObject);
 
		// Test with ConcurrentHashMap Object
		testConcurrentHashMapObject = new ConcurrentHashMap<String, Integer>();
//		performTest(testConcurrentHashMapObject);
 
	}
 
	public static void performTest(final Map<String, Integer> map) throws InterruptedException {
		ExecutorService executorService = null;
		try{
			System.out.println("Test started for: " + map.getClass());
			long averageTime = 0;
	//		for (int i = 0; i < 5; i++) {
	 
				long startTime = System.nanoTime();
				
				/**
				 * NOTE: ExecutorService interface represents an asynchronous execution mechanism
				 */
				executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
				for (int j = 0; j < THREAD_POOL_SIZE; j++) {
	//				executorService.execute(new Runnable() {
	//					@SuppressWarnings("unused")
	//					@Override
	//					public void run() {
	//						for (int i = 1; i < 10000; i++) {
	////							Integer randomNumber = (int) Math.ceil(Math.random() * 100);
	//							if(i == 9999) {
	//								System.out.println("9999");
	//							}
	//							Integer randomNumber = i;
	// 
	//							// Retrieve value. We are not using it anywhere
	//							Integer crunchifyValue = map.get(String.valueOf(randomNumber));
	// 
	//							// Put value
	//							map.put(String.valueOf(randomNumber), randomNumber);
	//						}
	//					}
	//				});
					
					Future future = executorService.submit(new CallableAdder(map)); 
				
				// Make sure executor stops
//				executorService.shutdown();
	 
				// Blocks until all tasks have completed execution after a shutdown request
//				executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
				
	//			executorService.awaitTermination(5, TimeUnit.MILLISECONDS);
	 
					long entTime = System.nanoTime();
					long totalTime = (entTime - startTime) / 1000000L;
					averageTime += totalTime;
					System.out.println("2500K entried added/retrieved in " + totalTime + " ms");
		//		}
					System.out.println("For " + map.getClass() + " the average time is " + averageTime / 5 + " ms\n");
					System.out.println("future.get(): " + future.get());
			}
		
				
		}catch(Exception e) {
			System.out.println(e);
		} finally{
			executorService.shutdown();
		}
	}
}

class CallableAdder implements Callable<String> {
	Map<String, Integer> map = null;

	CallableAdder(Map map) {
		this.map = map;
	}

	public String call() {
		try {
			for (int i = 1; i < 50000; i++) {
				if (i == 49999) {
					System.out.println("49999");
				}
				Integer randomNumber = i;

				// Retrieve value. We are not using it anywhere
				Integer value = map.get(String.valueOf(randomNumber));
				if(null != value && value == 100) {
					map.put(String.valueOf(randomNumber), randomNumber+100);
				}
				// Put value
				map.put(String.valueOf(randomNumber), randomNumber);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return "Callable Result";
	}
}

