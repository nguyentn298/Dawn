package com.dante.learn.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestGenericMethod {

	/*
	 * 	Generic for array
	 */

	public static <T> void printArray(T[] inputArray) {
		// Hien thi cac phan tu mang
		for (T element : inputArray) {
			System.out.printf("%s, ", element);
		}
		System.out.println();
	}

	/*
	 * 	Generic for list int or String
	 */

	public static <T> void printList(List<T> list) {
		for (T t : list) {
			System.out.println("field: " + t);
			System.out.println();
		}
	}

	/*
	 * 	Generic for Object
	 */

	public static <T> void printListObject(List<T> list) throws InvocationTargetException, IllegalAccessException, IllegalArgumentException {
		for (Object object : list) {

			/*
			 * 	Generic by field
			 */

//			for (Field field : object.getClass().getDeclaredFields()) {
//			    field.setAccessible(true); // You might want to set modifier to public first.
//			    Object value = field.get(object); 
//			    if (value != null) {
//			        System.out.println(field.getName() + " = " + value);
//			    }
//			}

			/*
			 * 	Generic by method
			 */

			for (Method method : object.getClass().getDeclaredMethods()) {
			    if (Modifier.isPublic(method.getModifiers())
			        && method.getParameterTypes().length == 0
			        && method.getReturnType() != void.class
			        && (method.getName().startsWith("get") || method.getName().startsWith("is"))
			    ) {
			        Object value = method.invoke(object);
			        if (value != null) {
			        	String fieldName = method.getName();
			        	String regex = removeRegexName(fieldName, "^(get|is)");
			            System.out.println(regex + " = " + value);
			        }
			    }
			}
			
//			System.out.printf("%s ", object);
			System.out.println();
		}
	}

	public static String findRegexName(String input, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if(matcher.find()) {
			return matcher.group(0);
		}
		
		return input;
	}
	
	public static String removeRegexName(String input, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		
		return matcher.replaceAll("");
	}
	
	public static void main(String args[]) {
		// Tao cac mang Integer, Double va Character
		Integer[] intArray = { 1, 2, 3, 4, 5 };
		Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
		Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };
		List<Integer> listInteger = new ArrayList<Integer>();
		listInteger.add(1);
		listInteger.add(69);
		
		List<String> listString = new ArrayList<String>();
		listString.add("test1");
		listString.add("test69");

		System.out.println("Mang intArray bao gom:");
		printArray(intArray); // truyen mot mang Integer

		System.out.println("\nMang doubleArray bao gom:");
		printArray(doubleArray); // truyen mot mang Double

		System.out.println("\nMang charArray bao gom:");
		printArray(charArray); // truyen mot mang Character

		System.out.println();
		
		System.out.println("Print List Integer");
			printList(listInteger);
		System.out.println("Print List String");
			printList(listString);
		
		// Print object
		System.out.println();
		List<Animal> animals = new ArrayList<Animal>();
		
		TestGenericMethod test = new TestGenericMethod();
		TestGenericMethod.Animal animal1 = test.new Animal("Dog", 2);
		TestGenericMethod.Animal animal2 = test.new Animal("Cat", 5);
		animals.add(animal1);
		animals.add(animal2);
//		animals.add(new TestGeneric.Animal("Dog", 2));
			try {
				printListObject(animals);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	class Animal {
		private String name;
		private int quantity;
		
		public Animal(String name, int quantity) {
			super();
			this.name = name;
			this.quantity = quantity;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
	}
	
}
