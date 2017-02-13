package com.dante.learn.core.thread;

public class TestThread {
	public static void main(String[] args) {
		
//		When you type java ThreadDemo to run the application, the JVM creates a starting thread of execution, which executes the main() method. 
//		By executing mt.start ();, 
//		the starting thread tells the JVM to create a second thread of execution that executes the byte code instructions comprising the MyThread object's run() method. 
//		When the start() method returns, 
//		the starting thread executes its for loop to print a table of squares, 
//		while the new thread executes the run() method to print the right-angle triangle.
		
		MyThread1 mt = new MyThread1();
		mt.start();
		for (int i = 0; i < 50; i++)
			System.out.println("i = " + i + ", i * i = " + i * i);
	}
}

class MyThread1 extends Thread {
	public void run() {
		for (int count = 1, row = 1; row < 20; row++, count++) {
			for (int i = 0; i < count; i++)
				System.out.print('*');
			System.out.print('\n');
		}
	}
}
