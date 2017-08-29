package com.dante.learn.pattern.creation.factory;

public class TestFactory {
	public static final int DOG = 1;
	public static final int CAT = 2;
	public static final int BUFFALO = 3;
	public static final int PANDA = 4;
	
	/**
	 * 1/ When I code alone at home/work, I avoid using factory patterns. 
	 * 2/ For small projects that won’t change a lot I also avoid factories.
	 * 3/ For medium to large projects involving multiple developers using the same code I find them useful.
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * Scenario: 
		 * Day 1: I want to view a dog when it's eating
		 * Day 10: I don't want to view the dog on Day 1 , I want to view a cat when it's eating
		 * Day 100: I don't want to view the cat on Day 100, I want to view a Buffalo when it's eating
		 */
		
		// Using a factory you could  easily switch from one implementation to another by modifying only the factory
		// i just change argument and don't care dev2, 3, 4 do anything and logic what they do
		Animal animal = animalFactory.getAnimal(TestFactory.DOG);
		animal.eat();
		
	}
}

interface Animal {
	public void eat();
}

class animalFactory {
	public static Animal getAnimal(int id) {
		Animal animal = null;
		switch(id) {
		case TestFactory.DOG:
			animal = new Dog2();
			break;
		case TestFactory.CAT:
			animal =  new myCat2();
			break;
		case TestFactory.BUFFALO:
			animal =  new BuffaloOfNeighbor2();
			break;
		case TestFactory.PANDA:
			animal =  new PandaInTheZoo2();
			break;
		default:
			System.out.println("Invalid animal!!!");
		}
		return animal;
	}
}

// I code this
class Dog2 implements Animal {

	@Override
	public void eat() {
		System.out.println("Dog eat meat");
	}
}

//Dev 2 code this
class myCat2 implements Animal {
	
	@Override
	public void eat() {
		System.out.println("Cat do a action, they eat fish");
	}
}

//Dev 3 code this
class BuffaloOfNeighbor2 implements Animal {
	
	@Override
	public void eat() {
		System.out.println("Buffalo make a easy action, they sit on the chair and eat grass");
	}
}

//Dev 4 code this
class PandaInTheZoo2 implements Animal {
	
	@Override
	public void eat() {
		System.out.println("Panda eat somthine like bamboo");
	}
}