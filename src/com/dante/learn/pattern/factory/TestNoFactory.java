package com.dante.learn.pattern.factory;

public class TestNoFactory {
	public static void main(String[] args) {
		/**
		 * Scenario: 
		 * Day 1: I want to view a dog when it's eating
		 * Day 10: I don't want to view the dog on Day 1 , I want to view a cat when it's eating
		 * Day 100: I don't want to view the cat on Day 100, I want to view a Buffalo when it's eating
		 */
		
		// This code will be changed when requirement change at Day 10, Day 100.
		// when change, i don't know class name of dev 2, 3, 4 and variable name of them.
		// I must review logic of their class and instantiate object and set appropriate method.
		Dog dog = new Dog();
		dog.eat();
		
	}
}

// I code this
class Dog {
	public void eat() {
		System.out.println("Dog eat meat");
	}
}

//Dev 2 code this
class myCat {
	public void doEat() {
		System.out.println("Cat do a action, they eat fish");
	}
}

//Dev 3 code this
class BuffaloOfNeighbor {
	public void makeEat() {
		System.out.println("Buffalo make a easy action, they sit on the chair and eat grass");
	}
}

//Dev 4 code this
class PandaInTheZoo {
	public void eatSomething() {
		System.out.println("Panda eat somthine like bamboo");
	}
}
