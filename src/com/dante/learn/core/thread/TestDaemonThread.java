package com.dante.learn.core.thread;

public class TestDaemonThread {
	public static void main(String[] args) {
		// workflow: Thread of Main method 	|--> myMain 			|--> main method finish when non-Daemon thread finish
		//									|--> myExtra(Daemon)	|--> main method finish when non-Daemon thread finish
		System.out.println("Start!!!");
		myMain main = new myMain();
		myExtra extra = new myExtra();

		// Finish from begin to end of myMain object
		main.start();

		// Finish when main method's finished (Daemon)
		extra.setDaemon(true);
		extra.start();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

		}
		System.out.println("Done!!!");

	}
}

class myMain extends Thread {

	@Override
	public void run() {
		System.out.println("Start Main Work!!!");
		for (int i = 0; i < 10; i++) {

			try {
				System.out.println("Main Work: " + i);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

			}
		}
		System.out.println("Finish Main!!!");
	}
}

class myExtra extends Thread {

	@Override
	public void run() {
		System.out.println("Start Extra!!!");

		try {
			// Daemon = true
			while (true) {
				for (int i = 0; i < 10; i++) {

					System.out.println("Extra Work: " + i + " (Optional Work)");
					Thread.sleep(1000);
				}
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Finish Extra!!!");
		}

	}
}
