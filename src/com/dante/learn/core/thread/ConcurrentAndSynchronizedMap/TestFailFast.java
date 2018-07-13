package com.dante.learn.core.thread.ConcurrentAndSynchronizedMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class TestFailFast {
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
		
		test.testHashMap(map);
//		test.testSynchronizedMap(map);
//		test.testCocurrentMap(map);
	}
	
	public static void waitUntilEnd() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testHashMap(Map map) {
		/**
		 *  Test with HashMap (asynchronied map)
		 */
			System.out.println("START t1 and t2!!");
			
			Runnable r1 =  new GetThread(map);
			Thread t1 = new Thread(r1);
			
			Runnable r2 =  new SetThread(map);
			Thread t2 = new Thread(r2);
			t1.start();
			t2.start();
			
			System.out.println("FiNISH t1 and t2!!");
			System.out.println("=============================================================");
	}
	
	public void testSynchronizedMap(Map map) {
		/**
		 *  Test with synchronizedMap (synchronied map):
		 *  SynchronizedMap will throw an ConcurrentModificationException while one thread is updating the map 
		 *  and another thread is traversing the ITERATOR obtained from the map
		 */
			System.out.println("START t3 and t4!!");
			
			Map<Integer, String> synchronizedMap = Collections.synchronizedMap(map);
			
			Runnable r3 =  new GetThread(synchronizedMap);
			Thread t3 = new Thread(r3);
			
			Runnable r4 =  new SetThread(synchronizedMap);
			Thread t4 = new Thread(r4);
			t3.start();
			t4.start();
			
			System.out.println("FiNISH t3 and t4!!");
			System.out.println("=============================================================");
	}
	
	public void testCocurrentMap(Map map) {
		/**
		 *  Test with concurrentHashMap (synchronied map):
		 *  concurrentHashMap will NOT throw an ConcurrentModificationException while one thread is updating the map 
		 *  and another thread is traversing the ITERATOR obtained from the map
		 */
			System.out.println("START t5 and t6!!");
			
			Map<Integer, String> concurrentHashMap = new ConcurrentHashMap<Integer, String>(map);
			Runnable r5 =  new GetThread(concurrentHashMap);
			Thread t5 = new Thread(r5);
			
			Runnable r6 =  new SetThread(concurrentHashMap);
			Thread t6 = new Thread(r6);
			t5.start();
			t6.start();
			
			System.out.println("FiNISH t5 and t6!!");
			System.out.println("=============================================================");
	}
}

class GetThread implements Runnable{
	
	Map map;
	public GetThread(Map map) {
		this.map = map;
	}
	
	@Override
	public void run() {
//		try{
			Iterator iterator = map.entrySet().iterator();
			while(iterator.hasNext()) {
				Map.Entry element = (Map.Entry) iterator.next();
				System.out.println("Get: " + element);
				
				// Just get value to test multi thread
				String value = (String) element.getValue();
				
			}
//		} catch(ConcurrentModificationException c) {
////			throw new RuntimeException(String.format("It will cause by asynchronize map:[%s]", c));
//			System.out.println(String.format("It will cause by HashMap(asynchronize map):[%s]", c));
//		}
	}
	
}

class SetThread implements Runnable{
	
	Map map;
	public SetThread(Map map) {
		this.map = map;
	}
	
	@Override
	public void run() {
		for(int i = 4; i < 10; i++) {
			System.out.println("Set: " + "test" + i);
			map.put(i, "test" + i);
		}
	}
	
}
