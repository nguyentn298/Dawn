package com.dante.learn.core.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestJunitAnnotation {
	// execute before class
	// this is setup (setup = Before)
	@BeforeClass
	public static void beforeClass() {
		System.out.println("in before class");
	}

	// execute after class
	// this is teardown (teardown = after)
	@AfterClass
	public static void afterClass() {
		System.out.println("in after class");
	}

	// execute before test
	@Before
	public void before() {
		System.out.println("in before");
	}

	// execute after test
	@After
	public void after() {
		System.out.println("in after");
	}

	// test case
	@Test(timeout = 1000)
	public void test() {
		System.out.println("Begin test");
		String a = "abc";
		String b = "abc";
		assertEquals(a, b);
		System.out.println("End test");
	}

	// test case ignore and will not execute
	@Ignore
	public void ignoreTest() {
		System.out.println("in ignore test");
	}
	
	@Test(expected = ArithmeticException.class)
	public void computeWrong() {
		System.out.println("Begin Division");
		int a = 0;
		int b = 1/a;
		System.out.println("End Division");
	}
}
