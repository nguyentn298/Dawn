package com.dante.learn.core.thread;

public class RunnableDemo implements Runnable {
	private Thread t;

	public void run() {
//		while (t == Thread.currentThread()) {
			int width = randoomNumber(30);
			if (width < 2)
				width += 2;

			int height = randoomNumber(10);
			if (height < 2)
				height += 2;

			drawOptimize(width, height);
//		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public void stop() {
		if (t != null)
			t = null;
	}

	private void draw(int width, int height) {
		for (int c = 0; c < width; c++)
			System.out.print('*');
		
		System.out.print('\n');

		for (int r = 0; r < height - 2; r++) {
			System.out.print('*');

			for (int c = 0; c < width - 2; c++)
				System.out.print(' ');

			System.out.print('*');

			System.out.print('\n');
		}

		for (int c = 0; c < width; c++)
			System.out.print('*');

		System.out.print('\n');
	}
	
	private void drawOptimize(int width, int height) {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				System.out.print("*");
			}
			System.out.print("\n");
		}
	}

	private int randoomNumber(int limit) {
		// Return a random number x in the range 0 <= x < limit.

		return (int) (Math.random() * limit);
	}
	
	public static void main(String[] args) {
		RunnableDemo r = new RunnableDemo();
		Thread t = new Thread(r);
		t.start();
	}
}
