package com.dante.learn.pattern.factory.factoryType;

public class AbstractFactoryPattern {
	public static void main(String[] args) {
		/*
		 * Of course, the factory methods can have parameters so that they return different types. 
		 * For example getEngine(String model) factory from SportCarFactory could return a Ferrari458Engine or a FerrariF50Engine or a Ferrari450Engine or …  
		 * depending on the parameter.
		 */
		myCarFactory carFactory = new SportCarFactory();
		Wheel myWheel = carFactory.getWheel();
		Engine myEngine = carFactory.getEngine();
	}
}

// ///////////////////////the different products
interface Wheel {
	public void turn();
}

class RaceCarWheel implements Wheel {
	public void turn() {
		// some stuff
	}
}

interface Engine {
	public void work();
}

class PowerfulEngine implements Engine {
	public void work() {
		// some stuff
	}
}

// ///////////////////////the factory
// it has 2 methods in factory, not similar to 3 remains
interface myCarFactory {
	public Engine getEngine();

	public Wheel getWheel();
}

class SportCarFactory implements myCarFactory {
	public Engine getEngine() {
		return new PowerfulEngine();
	}

	public Wheel getWheel() {
		return new RaceCarWheel();
	}
}