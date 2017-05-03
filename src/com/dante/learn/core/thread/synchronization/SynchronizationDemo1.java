package com.dante.learn.core.thread.synchronization;

class SynchronizationDemo1 {
	public static void main(String[] args) {
		FinTrans2 ft = new FinTrans2();
		TransThread2 tt1 = new TransThread2(ft, "Deposit Thread");
		TransThread2 tt2 = new TransThread2(ft, "Withdrawal Thread");
		tt1.start();
		tt2.start();
		System.out.println("In Main = " + Thread.holdsLock(ft));
	}
}

class FinTrans2 {
	public static String transName;
	public static double amount;

	// use Synchronized methods
	synchronized void update(String transName, double amount) {
		this.transName = transName;

		try {
			Thread.sleep((int) (Math.random() * 1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.amount = amount;
		System.out.println(this.transName + " = " + this.amount);

	}
}

class TransThread2 extends Thread {
	private FinTrans2 ft;

	TransThread2(FinTrans2 ft, String name) {
		super(name); // Save thread's name
		this.ft = ft; // Save reference to financial transaction object

	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			if (getName().equals("Deposit Thread")) {
				ft.update("Deposit", 2000);
			} else {
				ft.update("Withdrawad", 250);
			}
		}

//		for (int i = 0; i < 100; i++) {
//			if (getName().equals("Deposit Thread")) {
//				synchronized (ft) {
//					ft.transName = "Deposit";
//					try {
//						Thread.sleep((int) (Math.random() * 1000));
//					} catch (InterruptedException e) {
//					}
//					ft.amount = 2000.0;
//					System.out.println(ft.transName + " " + ft.amount);
//				}
//			} else {
//				synchronized (ft) {
//					ft.transName = "Withdrawal";
//					try {
//						Thread.sleep((int) (Math.random() * 1000));
//					} catch (InterruptedException e) {
//					}
//					ft.amount = 250.0;
//					System.out.println(ft.transName + " " + ft.amount);
//				}
//			}
//		}
	}
}
