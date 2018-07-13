package com.dante.learn.core.collectionAndMap;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;

/**
 *	A Queue uses a "First In First Out (FIFO)" principle.  
 */
public class TestQueue {

	@Test
	public void testQueueCategory() {
		/**
		 * BlockingQueue is bounded queue or Blocking Queues
		 * It mean we need to set size of it
		 */
		BlockingQueue<String> myBlockingQueue = new LinkedBlockingQueue<>(2);
		System.out.println(myBlockingQueue.offer("one"));
		System.out.println(myBlockingQueue.offer("two"));
		System.out.println(myBlockingQueue);
		System.out.println(myBlockingQueue.offer("three")); // block here, because size = 2
		System.out.println(myBlockingQueue);

		/**
		 * Queue is unbounded queue or Non-Blocking Queues
		 * It mean we should not provide the size of the queue
		 */
		Queue<String> myQueue = new LinkedList<String>();
		System.out.println(myQueue.offer("one"));
		System.out.println(myQueue.offer("two"));
		System.out.println(myQueue);
		System.out.println(myQueue.offer("three"));
		System.out.println(myQueue);

		/**
		 * The java.util.Deque interface is a subtype of the java.util.Queue interface
		 * ArrayDeque stores its elements internally in an array. 
		 * If the number of elements exceeds the space in the array, a new array is allocated, and all elements moved over. 
		 * In other words, the ArrayDeque grows as needed, even if it stores its elements in an array. 
		 */
		Deque dequeA = new LinkedList();
		Deque dequeB = new ArrayDeque();
		
		dequeA.add     ("element 1");						//add element at tail
		dequeA.addFirst("element 2"); 						//add element at head
		dequeA.addLast ("element 3"); 						//add element at tail
		
		Object firstElement = dequeA.element();				// retrieve element
		Object firstElement2 = dequeA.getFirst();
		Object lastElement  = dequeA.getLast();
		
		Object removeFirstElement = dequeA.remove();		// remove element
		Object removeFirstElement2 = dequeA.removeFirst();
		Object removeLastElement  = dequeA.removeLast();
		
		//access via Iterator
		Iterator iterator = dequeA.iterator();
		while(iterator.hasNext()){
		  String element = (String) iterator.next();
		}

		//access via new for-loop
		for(Object object : dequeA) {
		    String element = (String) object;
		}
	}
	@Test
	public void testQueueFunction() {

		/**
		 * Add(): 		Insert data and throws an exception if the operation fails.
		 * Offer(): 	Insert data and return false or null if the operation fails.
		 */

		Queue<String> queue = new LinkedList<String>();
		queue.add("this");
		queue.offer("is");
		queue.add("a");
		queue.offer("Cat");
		System.out.println("My queue: " + queue);

		/**
		 * element():	Retrieve element from head data and throw exception if null.
		 * Peek():		Retrieve element from head data and return null if null.
		 */

		String b = queue.element();				
		System.out.println("Use element: " + b);
		System.out.println("after element: " + queue);

		String a = queue.peek();				
		System.out.println("Use peek: " + a);
		System.out.println("after peek: " + queue);

		/**
		 * Remove():	Retrieve and REMOVE data from head and throw exception if null.
		 * Poll():		Retrieve and REMOVE data from head and return false or null if null
		 */

		String d = queue.remove();				
		System.out.println("Use element: " + d);
		System.out.println("after element: " + queue);
		
		String c = queue.poll();				
		System.out.println("Use poll: " + c);
		System.out.println("after poll: " + queue);

		/**
		 * DrainTo(): Cut element of BlockingQueue to specific collection. (IMPORTANT: it's "cut and paste", not "copy and paste")
		 */
		List<String> list = new ArrayList<>();
		BlockingQueue<String> myBlockingQueue = new LinkedBlockingQueue<>(5);
		myBlockingQueue.add("Test1");
		myBlockingQueue.add("Test2");
		myBlockingQueue.add("Test3");
		myBlockingQueue.add("Test4");

		System.out.println("List before drain to: " + list);
		System.out.println("BlockingQueue before drainTo" + myBlockingQueue);	// original

		myBlockingQueue.drainTo(list, 3);										// drainTo with 3 element

		System.out.println("List after drain to: " + list);
		System.out.println("BlockingQueue after drainTo" + myBlockingQueue);
		
	}
}
