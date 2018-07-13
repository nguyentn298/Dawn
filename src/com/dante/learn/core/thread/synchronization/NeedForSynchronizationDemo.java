package com.dante.learn.core.thread.synchronization;

class NeedForSynchronizationDemo {
	public static void main(String[] args) {
		
		System.out.println("Start process!!");
		FinTrans ft = new FinTrans();
		TransThread tt1 = new TransThread(ft, "Deposit Thread");
		TransThread tt2 = new TransThread(ft, "Withdrawal Thread");
		
		System.out.println("Start tt1");
		tt1.start();
		
		System.out.println("Start tt2");
		tt2.start();
	}
}

class FinTrans {
	public static String name;
	public static double amount;
}

class TransThread extends Thread {
	private FinTrans ft;

	TransThread(FinTrans ft, String name) {
		super(name); // Save thread's name
		this.ft = ft; // Save reference to financial transaction object
	}

	// Deposit assigns "Deposit Thread" to name and sleep 500 milliseconds
	// Withdrawal assigns "Withdrawal Thread" to name and sleep 100 milliseconds
	// Withdrawal finish execution and print the result ==>	"Withdrawal Thread": 250
	// Deposit Thread awake and print the resutl		==>	"Withdrawal Thread": 2000 (It's must be "Deposit Thread": 2000)
	// Becasue: "Deposit Thread" get name after awake, but name is changed by "Withdrawal Thread"
	public void run() {
		for (int i = 0; i < 5; i++) {
			if (getName().equals("Deposit Thread")) {
				// Start of deposit thread's critical code section
				ft.name = "Deposit";
				try {
					Thread.sleep((int) (Math.random() * 1000));
				} catch (InterruptedException e) {
				}
				ft.amount = 2000.0;
				System.out.println(ft.name + " " + ft.amount);
				// End of deposit thread's critical code section
			} else {
				// Start of withdrawal thread's critical code section
				ft.name = "Withdrawal";
				try {
					Thread.sleep((int) (Math.random() * 1000));
				} catch (InterruptedException e) {
				}
				ft.amount = 250.0;
				System.out.println(ft.name + " " + ft.amount);
				// End of withdrawal thread's critical code section
			}
		}
	}
}
