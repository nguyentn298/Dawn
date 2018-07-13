package com.dante.learn.core.oop.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericClassWithUnboundedTypeParameter<T> {
	
	private T test;
	private GenericClassWithUnboundedTypeParameter<T> next;
	
	public GenericClassWithUnboundedTypeParameter(T test, GenericClassWithUnboundedTypeParameter<T> next) {
		this.next = next;
		this.test = test;

	}
	
	public static void main(String[] args) {
//		System.out.println();
//		List test = Arrays.asList(42);
//		for (Object object : test) {
//			System.out.println(object);
//		}
		
		List<String> a = new ArrayList<>();
		a.add("hello");
		List<String> b = new ArrayList<>();
		
		List[] test2 = new List[20];
		test2[0] = a;
		test2[1] = b;
		
//		String s = test2[0].get(0);
	
//		Object[] object = test2;
//		object[0] = Arrays.asList(42);
//		String s = test2[0].get(0);
	}
	
	<E> E[] makeArr() {
		return (E[]) new Object[1];
	}
}
