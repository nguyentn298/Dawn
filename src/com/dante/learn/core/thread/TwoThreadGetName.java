package com.dante.learn.core.thread;

public class TwoThreadGetName extends Thread {
	public void run() {
		for (int i = 0; i < 10; i++) {
			printMsg();
		}
	}

	public void printMsg() {
		Thread t = Thread.currentThread();
		String name = t.getName();
		System.out.println("name=" + name);
	}
	
	public void printMsg2() {
		Thread t = new Thread();
		String name = t.getName();
		System.out.println("name2=" + name);
	}

	public static void main(String[] args) {
		TwoThreadGetName tt = new TwoThreadGetName();
		tt.start();
		for (int i = 0; i < 10; i++) {
			tt.printMsg();
		}
		tt.printMsg2();
	}
}
