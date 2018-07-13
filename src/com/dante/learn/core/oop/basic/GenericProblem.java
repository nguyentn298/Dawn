package com.dante.learn.core.oop.basic;

import java.util.ArrayList;
import java.util.List;

public class GenericProblem {
	/**
	 * Why primitive type cant pass as type prameter to collection in java? 
	 * Because: when pass it into any method, it will copy actual parameter to formal parameter in method, so cant change value of actual parameter
	 * In contrast, when pass object to method, it will share address of object, so we can change value by address of actual parameter
	 */
	
	/**
	 * terminology:

    formal parameter: the parameter variable that is listed (along with its type) in the method declaration
    actual parameter: the parameter that is given when the method is called

	 */
	public static void main(String[] args) {
		
		/**
		 * Test with type parameter is String type in collection
		 */
		String a = "test1"; // this is actual parameter
		String b = "test2"; // this is actual parameter
		
		/**
		 * list.add(object): object is formal parameter, example add(String string)
		 */
		List<String> list = new ArrayList<>();
		
		/**
		 * In any method, actual parameter of object will share address, not share value
		 */
		list.add(a);
		list.add(b);
		
		/**
		 * I easly can change value list element
		 */
		list.set(0, "test3");
		getList(list);
		
		/**
		 * Test with type parameter is primitive type in collection
		 */
		
		
	}
	
	public static void getList(List<?> list) {
		for (Object object : list) {
			System.out.println(object);
		}
	}
	

	
}
