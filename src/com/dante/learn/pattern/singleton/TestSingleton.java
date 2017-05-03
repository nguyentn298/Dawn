package com.dante.learn.pattern.singleton;

public class TestSingleton {
	public static void main(String[] args) {
		
		// p1 is Error beccause it's private constructor
		// Product p1 = new Product();
		
		// p2 set id and name
		Product p2 = Product.getInstance();
		p2.setId(1);
		p2.setName("Dante");
		System.out.println("id: " + p2.getId());
		System.out.println("name: " + p2.getName());
		
		// p3 not set any property, but still have value of p2
		Product p3 = Product.getInstance();
		System.out.println("id: " + p3.getId());
		System.out.println("name: " + p3.getName());
		
		/**
		 * This pattern involves a single class which is responsible to create an object while making sure that only single object gets created. 
		 * This class provides a way to access its only object which can be accessed directly without need to instantiate the object of the class
		 * Use singleton when client want this object is only exists, and no create new any object of it.
		 * or avoid multi thread
		 */
	}
}

class Product {
	private int id;
	private String name;
	
	private static Product instance = new Product();
	
	public static Product getInstance() {
		return instance;
	}
	private Product() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
