package com.dante.learn.core.junit;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestJunitRunner {
	
	@Test
	public void testAnnotation() {
//		Result result = JUnitCore.runClasses(TestJunitAnnotation.class);
//		Result result = JUnitCore.runClasses(MoneyTest.class);
		Result result = JUnitCore.runClasses(MoneyTest.class);

		// Get log when failure
		for (Failure failure : result.getFailures()) {
			System.out.println("Failure: " + failure.toString());
		}

		// Get log when success
		System.out.println();
		System.out.println("Result: " + result.wasSuccessful());
	}
	
}
