package com.dante.learn.core;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.junit.Test;

public class TestClass<T> {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		// printClassName(new TestClass());

		// TestClass test = new TestClass();
		// Map<String, String> map = test.getResource();
		// for (Entry<String, String> entry: map.entrySet()) {
		// System.out.println("key: " + entry.getKey() + " - " + "value: " +
		// entry.getValue());
		// }
		// System.out.println();

		TestClass test = new TestClass();
		test.getResource();
//		test.testInvokeMethodOfClass();
		
		
	}

	/**
	 * Reflection allows instantiation of new objects, invocation of methods, 
	 * and get/set operations on class variables dynamically at run time without having prior knowledge of its implementation.
	 */
	public void testInvokeMethodOfClass() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		Class cl = this.getClass(); 
//		Class cl = Class.forName("com.dante.learn.core.TestClass");
		Object object = cl.newInstance();
		
		
		Class[] paramInt = new Class[1];	
		paramInt[0] = Integer.TYPE;
		try {
			/**
			 * In method.Invoke(object, param), If the method is static you supply null. If the method is not static, 
			 * then while invoking you need to supply a valid MyObject instance instead of null
			 */
			Method mt = cl.getMethod("show", null);
			System.out.println(mt.invoke(object, null));
			
			/**
			 * This will show error because method show() is not static method
			 * System.out.println(mt.invoke(null, null));
			 */
			
			
			Method mt2 = cl.getMethod("showInt", paramInt);
			System.out.println(mt2.invoke(null, 123));
			System.out.println(mt2.invoke(object, 123));
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Integer show() {
//		System.out.println("this is show method");
		return 1;
	}

	public static void showInt(int number) {
		System.out.println("this is showInt method: " + number);
	}

	public static void printClassName(Object obj) {
		System.out.println("The class of " + obj + " is " + obj.getClass().getName());
		System.out.println();
		System.out.println("This is obj = " + obj);
		System.out.println("This is get Class = " + obj.getClass());
		System.out.println("This is get Class Name = " + obj.getClass().getName());
		System.out.println("This is get super generic = " + obj.getClass().getGenericSuperclass());
		System.out.println();
	}

	// Non static method
	public Map<String, String> getResource() {
		Map<String, String> map = new HashMap<String, String>();

		Properties properties = new Properties();
		InputStream in = getClass().getResourceAsStream("testClassConfig.properties");
		System.out.println("getClass().getResourceAsStream: " + in);
		try {
			// if(null == in) {
			// in = new FileInputStream("config.properties");
			// }
			properties.load(in);
//			properties.clone();

			for (Object entry : properties.keySet()) {
				map.put(entry.toString(), properties.get(entry).toString());
			}
			System.out.println(map);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}
}
