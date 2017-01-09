package com.dante.learn.core.junit;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestJunitAnnotationRunner {
	
	@Test
	public void testAnnotation() {
		Result result = JUnitCore.runClasses(TestJunitAnnotation.class);

		// Get log when failure
		for (Failure failure : result.getFailures()) {
			System.out.println("Failure: " + failure.toString());
		}

		// Get log when success
		System.out.println("Result: " + result.wasSuccessful());
	}
}
