package com.dante.learn.core;

public class TestRecursion {

	private static int index = 0;
	private static int stoppingPoint = 40;

	public static void main(String[] args) {
		int n1 = 0;
		int n2 = 1;
		System.out.println("Start: " + index + " -> " + n1);
		fibonacciSequence(n1, n2);
	}

	public static void fibonacciSequence(int n1, int n2) {
		System.out.println("index: " + index + " -> " + n2);

		// make sure we have set an ending point so this Java recursion
		// doesn't go on forever.
		if (index == stoppingPoint)
			return;

		// make sure we increment our index so we make progress
		// toward the end.
		index++;

		fibonacciSequence(n2, n1 + n2);
	}
}
