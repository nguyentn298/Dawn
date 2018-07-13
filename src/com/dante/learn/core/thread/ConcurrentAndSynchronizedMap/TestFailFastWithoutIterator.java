package com.dante.learn.core.thread.ConcurrentAndSynchronizedMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestFailFastWithoutIterator {
	
	public final static int THREAD_POOL_SIZE = 5;
	
	public static void main(String[] args) {
		
		/**
		 * Test with IERATOR
		 */
		TestFailFast test = new TestFailFast();
		Map<Integer, String> map = new HashMap<Integer, String>(){
			{
				put(2, "test2");
				put(3, "test3");
				put(1, "test1");
			}
		};
		
//		test.testHashMap(map);
//		test.testSynchronizedMap(map);
		test.testCocurrentMap(map);
		
		ExecutorService services = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		
		services.execute(new Runnable() {
			
			@Override
			public void run() {
				
			}
		});
		
		
	}

	public void testHashMap(Map map) {
		/**
		 * Test with HashMap (asynchronied map)
		 */
		System.out.println("START t1 and t2!!");

		Runnable r1 = new GetThreadMore(map);
		Thread t1 = new Thread(r1);

		Runnable r2 = new SetThreadMore(map);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();

		System.out.println("FiNISH t1 and t2!!");
		System.out
				.println("=============================================================");
	}

	public void testSynchronizedMap(Map map) {
		/**
		 * Test with synchronizedMap (synchronied map): SynchronizedMap will
		 * throw an ConcurrentModificationException while one thread is updating
		 * the map and another thread is traversing the ITERATOR obtained from
		 * the map
		 */
		System.out.println("START t3 and t4!!");

		Map<Integer, String> synchronizedMap = Collections.synchronizedMap(map);

		Runnable r3 = new GetThreadMore(synchronizedMap);
		Thread t3 = new Thread(r3);

		Runnable r4 = new SetThreadMore(synchronizedMap);
		Thread t4 = new Thread(r4);
		t3.start();
		t4.start();

		System.out.println("FiNISH t3 and t4!!");
		System.out
				.println("=============================================================");
	}

	public void testCocurrentMap(Map map) {
		/**
		 * Test with concurrentHashMap (synchronied map): concurrentHashMap will
		 * NOT throw an ConcurrentModificationException while one thread is
		 * updating the map and another thread is traversing the ITERATOR
		 * obtained from the map
		 */
		System.out.println("START t5 and t6!!");

		Map<Integer, String> concurrentHashMap = new ConcurrentHashMap<Integer, String>(
				map);
		Runnable r5 = new GetThreadMore(concurrentHashMap);
		Thread t5 = new Thread(r5);

		Runnable r6 = new SetThreadMore(concurrentHashMap);
		Thread t6 = new Thread(r6);
		t5.start();
		t6.start();

		System.out.println("FiNISH t5 and t6!!");
		System.out
				.println("=============================================================");
	}
}

class GetThreadMore implements Runnable {

	Map map;

	public GetThreadMore(Map map) {
		this.map = map;
	}

	@Override
	public void run() {
		// try{
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry element = (Map.Entry) iterator.next();
			System.out.println("Get: " + element);

			// Just get value to test multi thread
			String value = (String) element.getValue();

		}
		// } catch(ConcurrentModificationException c) {
		// // throw new
		// RuntimeException(String.format("It will cause by asynchronize map:[%s]",
		// c));
		// System.out.println(String.format("It will cause by HashMap(asynchronize map):[%s]",
		// c));
		// }
	}

}

class SetThreadMore implements Runnable {

	Map map;

	public SetThreadMore(Map map) {
		this.map = map;
	}

	@Override
	public void run() {
		for (int i = 4; i < 10; i++) {
			System.out.println("Set: " + "test" + i);
			map.put(i, "test" + i);
		}
	}

}
