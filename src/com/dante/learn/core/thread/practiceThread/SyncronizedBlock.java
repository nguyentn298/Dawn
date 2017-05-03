package com.dante.learn.core.thread.practiceThread;

public class SyncronizedBlock {
	public static void main(String[] args) {
		MyAction m = new MyAction();
		MyRunnable1 m1 = new MyRunnable1(m);
		MyRunnable2 m2 = new MyRunnable2(m);
		Thread t1 = new Thread(m1);
		Thread t2 = new Thread(m2);

		// Deposit
		t1.start();

		// Draw
		t2.start();

		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("My money: =============================> " + m.money);
		
	}

}

class MyRunnable1 implements Runnable {
	MyAction m;

	public MyRunnable1(MyAction m) {
		this.m = m;
	}

	@Override
	public void run() {
			m.deposit();
	}

}

class MyRunnable2 implements Runnable {
	MyAction m;

	public MyRunnable2(MyAction m) {
		this.m = m;
	}

	@Override
	public void run() {
		m.draw();
	}

}

class MyAction {
	Integer money = 0;

	public void deposit() {
		System.out.println("Begin deposit!!!");
		synchronized (money) {
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				money += 15;
				System.out.println("Deposit 15");
			}
		}
		
		System.out.println("Finish deposit!!!");
	}

	public void draw() {
		System.out.println("Begin draw!!!");
		synchronized (money) {
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				money -= 10;
				System.out.println("Draw 10");
			}
		}
	

		System.out.println("Finish draw!!!");
	}

}