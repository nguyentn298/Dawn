package com.dante.learn.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;


public class TestClass<T> {
	
	public static void main(String[] args) {
//		printClassName(new TestClass());
		
		TestClass test = new TestClass();
		Map<String, String> map = test.getResource();
		for (Entry<String, String> entry: map.entrySet()) {
			System.out.println("key: " + entry.getKey() + " - " + "value: " + entry.getValue());
		}
		System.out.println();
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
		InputStream in =  getClass().getResourceAsStream("testClassConfig.properties");
		try {
//			if(null == in) {
//				in = new FileInputStream("config.properties");
//			}
			properties.load(in);
			properties.clone();
			
			for (Object entry : properties.keySet()) {
				map.put(entry.toString(), properties.get(entry).toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
}
