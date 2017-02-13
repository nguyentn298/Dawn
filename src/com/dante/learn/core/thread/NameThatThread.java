package com.dante.learn.core.thread;

//NameThatThread.java
public class NameThatThread {
	public static void main(String[] args) {
		MyThread2 mt;
		Thread t = Thread.currentThread();
		
		if (args.length == 0)
			mt = new MyThread2(t.getName());
		else
			mt = new MyThread2(args[0]);
		mt.start();
		
	}
}

class MyThread2 extends Thread {
	MyThread2() {
		// The compiler creates the byte code equivalent of super ();
		System.out.println("no param");
	}

	MyThread2(String name) {
		super(name); // Pass name to Thread superclass
		System.out.println("has param");
	}

	public void run() {
		System.out.println("My name is: " + getName());
	}
}