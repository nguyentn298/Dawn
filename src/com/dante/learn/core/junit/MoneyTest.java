package com.dante.learn.core.junit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import junit.framework.Assert;
import junit.framework.TestCase;

public class MoneyTest {

	@Test
	public void TestAdd() {
		System.out.println("Begin");
		Money m1 = new Money(200, "VND");
		Money m2 = new Money(1000, "VND");
		Money result = m1.add(m2);
		Money expected = new Money(1300, "VND");
		assertTrue(result.equals(expected));
		System.out.println("Finish");
	}

}
