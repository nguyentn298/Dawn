package com.dante.learn.pattern.creation.builder;

public class MainPizza {
	public static void main(String[] args) {
		
		/*
		 * =============== BUILDER PATTERN =========================
		 * It is really only useful if you are going to have more than 4 or 5 parameters for a constructor. 
		 */
		PizzaWithBuilderPattern pizza = new PizzaWithBuilderPattern.Builder(12)
								.cheese(5)
								.pepperoni(7)
								.bacon(4)
								.build();
		
		System.out.println(pizza);
		
		PizzaWithJavaBean pizzaWithoutBuilder = new PizzaWithJavaBean();
		pizzaWithoutBuilder.setBacon(2);
		
		System.out.println(pizzaWithoutBuilder.getBacon());
		System.out.println(pizzaWithoutBuilder.getCheese());
		System.out.println(pizzaWithoutBuilder.getPepperoni());
	}
}
