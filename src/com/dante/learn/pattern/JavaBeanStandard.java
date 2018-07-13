package com.dante.learn.pattern;

public class JavaBeanStandard {
	/*
	 * A JavaBean is just a standard
			1/ All properties private (use getters/setters)
			2/ A public no-argument constructor
			3/ Implements Serializable.
			That's it. It's just a convention. Lots of libraries depend on it though....
		
	* Disadvantage:
			A JavaBean may be in an inconsistent state partway through its construction since multiple calls are involved for object creation. 
			The class can not force the validity of the input parmeters via the constuctor
	 */
	
	private int index;
	private String name;

	// This is java bean pattern because it has constructor without argument and get, set method.
	public JavaBeanStandard() {

	}

	/*
	 * This is Telescoping constructor pattern
	 * Disadvantage:
	 * 		- If a class has 20 parameters, it must be considered using this pattern
	 */
	
	public JavaBeanStandard(int index, String name) {
		super();
		this.index = index;
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
