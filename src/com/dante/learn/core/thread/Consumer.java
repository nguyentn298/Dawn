package com.dante.learn.core.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Consumer extends Thread {
	Producer producer;

	Consumer(Producer p) {
		producer = p;
	}

	@Override
	public void run() {
		try {
			while (true) {
				String message = producer.getMessage();
				System.out.println("Got message: " + message);
				// sleep(200);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Producer producer = new Producer();
		producer.start();
		new Consumer(producer).start();
		System.out.println("Finish Main");
	}
}

class Producer extends Thread {

	static final int MAXQUEUE = 5;
//	private Vector messages = new Vector();
	private List messages = new ArrayList();

	@Override
	public void run() {
		try {
			// While true ==> infinite loop.
			while (true) {
				System.out.println("Begin Putting!!!!!!!!");
				putMessage();
				System.out.println("End Putting!!!!!!!!");
				// sleep(5000);
			}
		} catch (InterruptedException e) {
		}
	}

	private synchronized void putMessage() throws InterruptedException {
//		Thread.sleep(3000);
		while (messages.size() == MAXQUEUE) {
			System.out.println("Current size: " + messages.size());
			System.out.println("size is max and waiting for removed messages notify!!");
			wait();
		}
		System.out.println("Add Element before put message ...");
//		messages.addElement(new java.util.Date().toString());
		messages.add(new java.util.Date().toString());
		System.out.println("put message success!!!");
		System.out.println();
		System.out.println("Before Notify putMessage");
		notify();
		System.out.println("After Notify putMessage");
		System.out.println();
		// Later, when the necessary event happens, the thread that is running
		// it calls notify() from a block synchronized on the same object.
	}

	// Called by Consumer
	public synchronized String getMessage() throws InterruptedException {
		System.out.println("Before Notify getMessage");
		notify();
		System.out.println("After Notify getMessage");
		
		// Use while here if getMessage() run faster than putMessage in first time.
		while (messages.size() == 0) {
			System.out.println("size is zero and waiting for messages notify!!");
			wait();// By executing wait() from a synchronized block, a thread
					// gives up its hold on the lock and goes to sleep.
		}
		System.out.println("Get and remove message ...");
//		String message = (String) messages.firstElement();
//		messages.removeElement(message);
		
		String message = (String) messages.get(0);
		messages.remove(message);
		
		System.out.println("Current size: " + messages.size());
		System.out.println();
		return message;
	}
}