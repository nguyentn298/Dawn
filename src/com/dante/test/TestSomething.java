package com.dante.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestSomething {
	Log log = LogFactory.getLog(getClass());
	String test1;

	public static void main(String[] args)
			throws IllegalArgumentException, IllegalAccessException {

		TestSomething t = new TestSomething();

		// whileAndDoWhile();
//		testLabeledAndUnlabeledBreak();
//		testLabeledAndUnlabeledContinue();
//		testLabeledAndUnlabeledContinue2();
//		testLabeledAndUnlabeledContinue3();
		
//		int i = 0;
//		String test = "my substring";
//		System.out.println(test.charAt(++i));
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
//				System.out.println(String.format("i = %d, j = %d", i, j));
				t.log.info(String.format("i = %d, j = %d", i, j));
			}
		}
//		if(t.log.isDebugEnabled()) {
			t.log.debug("Test debug", new RuntimeException());
//		}
		
	}

	public void testLabeledContinue() {
		test:
			for(int i = 0; i < 3; i++) {
				System.out.println("i = " + i);
				for(int j = 0; j < 2; j++) {
					System.out.println("j = " + j);
					for(int k = 0; k < 4; k++) {
						System.out.println("k = " + k);
						if(k == 2) {
							continue test;
						}
					}
				}
			}
	}
	public static void testLabeledAndUnlabeledContinue() {
		String str = "This is my su2bstring, isn't it?";
		String search = "sub";
		boolean isTrue = false;
		
		test:
		for(int i = 0; i < str.length(); i++) {
			int j = i;
			int k = 0;
			int n = search.length();
			while(n-- != 0) {
				if(str.charAt(j++) != search.charAt(k++)) {
					continue test;
				}
			}
			isTrue= true;
			break test;
		}
		System.out.println((isTrue) ? "Found" : "Not found!!! ");
	}
	

	public static void testLabeledAndUnlabeledContinue2() {
		
		String str = "This is my su2bstring, isn't it?";
		String search = "sub";
		boolean isTrue = false;
		
		test:
		for(int i = 0; i < str.length(); i++) {
			int k = i;
			for(int j = 0; j < search.length(); j++) {
				if(str.charAt(k++) != search.charAt(j)) {
					continue test;
				}
			}
			isTrue= true;
			break test;
		}
		System.out.println((isTrue) ? "Found" : "Not found!!! ");
	}
	
	public static void testLabeledAndUnlabeledContinue3() {
		
		List<String> listManga = new ArrayList<String>() {
			{
				add("abcxz one abcxz piece abcxz");
				add("abcxz one love abcxz");
				add("abcxz Death is like a wind by my side abcxz");
				add("abcxz one abcxyz life abcxz");
			}
		};
		
		for (String string : listManga) {
			String search = "one";
			boolean isTrue = false;
			
			test:
			for(int i = 0; i < string.length(); i++) {
				int k = i;
				for(int j = 0; j < search.length(); j++) {
					if(string.charAt(k++) != search.charAt(j)) {
						continue test;
					}
				}
				isTrue= true;
				break test;
			}
			
			if(isTrue) {
				System.out.println(string);
			}
		}
		
	}
	
	public static void testLabeledAndUnlabeledBreak() {

		int[][] arrs = {
				{1, 2, 3, 4, 5}, 
				{6, 7, 8, 9, 10, 11, 12, 13, 14, 15},
				{3, 4, 5}
		};
		System.out.println(arrs.length);
		System.out.println(arrs[1].length);

		search : for (int i = 0; i < arrs.length; i++) {
			System.out.println(String.format("Outer Loop with i: %s", i));
			for (int j = 0; j < arrs[i].length; j++) {
				System.out.println(String.format("Inner Loop with j: %s", j));
				if (arrs[i][j] == 15) {
					System.out.println(String.format("i = %s and j = %s", i, j));
					continue;
				}
			}
		}

	}
	public static void whileAndDoWhile() {
		int count = 1;

		/**
		 * Evaluate condition first
		 */
		while (count < 11) {
			if (count == 5) {
				break;
			}

			System.out.println("while: " + count);
			count++;
		}

		/**
		 * Execute 1 time first and evaluate condition second
		 */
		int count2 = 11;
		do {
			System.out.println("Do while: " + count2);
			count2++;
		} while (count2 < 11);
	}

	public static long abs(long a) {
		return (a < 0) ? -a : a;
	}

	public static long abs2(long a) {
		if (a < 0) {
			return -a;
		} else {
			return a;
		}
	}

	public void testFieldAndClass() {
		Field[] declaredFields = TestSomething.class.getDeclaredFields();
		System.out.println(TestSomething.class.getDeclaredFields());
		for (Field f : declaredFields) {
			// System.out.println(f.getAnnotationsByType(TestSomething.class));

			String fieldName = f.getName();
			Class<?> type = f.getType();
			System.out.println("fieldName: " + fieldName);
			System.out.println("type: " + type);
			// System.out.println("f.get(0)0" + f.getByte(t));
		}
	}

	public static void testReference() {
		int a = 1;
		int b = 1;
		int c = a;
		System.out.println(String.format("a=[%d], b=[%d], c=[%d]", a, b, c));

		a = 2;
		c = a;
		System.out.println(String.format("a=[%d], b=[%d], c=[%d]", a, b, c));

		String d = "test";
		String e = "test";
		String f = d;
		System.out.println(String.format("d=[%s], e=[%s], f=[%s]", d, e, f));

		d = "abc";
		f = d;
		System.out.println(String.format("d=[%s], e=[%s], f=[%s]", d, e, f));
	}

	public void testAtomic() {
		AtomicInteger atomic = new AtomicInteger(1);
		atomic.incrementAndGet();
		System.out.println(atomic);

		AtomicReference<String> test = new AtomicReference("test String");
		System.out.println(test);
	}

	public void testPrimitive() {
		AtomicInteger atomic = new AtomicInteger(1);
		atomic.incrementAndGet();
		System.out.println(atomic);
	}

	public static void testOperator() {
		int a = 0;
		int b = a++;
		System.out.println(a);
		System.out.println(b);

		int c = ++a;
		System.out.println(a);
		System.out.println(c);
	}

}
