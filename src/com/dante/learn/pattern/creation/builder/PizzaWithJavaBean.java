package com.dante.learn.pattern.creation.builder;

public class PizzaWithJavaBean {
	private int size;
	private int cheese;
	private int pepperoni;
	private int bacon;

	// This is java bean pattern because it has constructor without argument
	public PizzaWithJavaBean() {

	}

	// Telescoping constructor pattern
	public PizzaWithJavaBean(int size, int cheese, int pepperoni, int bacon) {
		super();
		this.size = size;
		this.cheese = cheese;
		this.pepperoni = pepperoni;
		this.bacon = bacon;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCheese() {
		return cheese;
	}

	public void setCheese(int cheese) {
		this.cheese = cheese;
	}

	public int getPepperoni() {
		return pepperoni;
	}

	public void setPepperoni(int pepperoni) {
		this.pepperoni = pepperoni;
	}

	public int getBacon() {
		return bacon;
	}

	public void setBacon(int bacon) {
		this.bacon = bacon;
	}
	
//	@Override
//	public String toString() {
//		return String.format("cheese[%s] - pepperoni[%s] - bacon[%s]", cheese, pepperoni, bacon);
//	}

}
