package com.dante.learn.core.junit;

import junit.framework.Assert;
import junit.framework.TestCase;

public class MoneyTest extends TestCase {
	public void TestAdd() {
		Money m1 = new Money(200, "VND");
		Money m2 = new Money(1000, "VND");
		Money result = m1.add(m2); 
		Money expected = new Money(1200, "VND"); 
		Assert.assertTrue(result.equals(expected)); 
	}
	
}
