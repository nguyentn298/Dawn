package com.dante.learn.pattern.creation.factory.type;

/**
 * 
 * @author dante
 *
 */
public class FactortyMethodPatern {
	public static void main(String[] args) {
		/**
		 * by Christophe : http://coding-geek.com/design-pattern-factory-patterns/
		 * As I said this is not a great example because in this example you shouldn’t use a factory method pattern since there is only one concrete factory.  
		 * It would be useful only if I have multiple ConcreteCarFactory implementations like SportCarFactory, VintageCarFactory, LuxeCarFactory, CheapCarFactory ….. 
		 * In this case, a developper could easily switch from one implementation to another since the factory method is always getCar().
		 */
		CarFactory carFactory = new ConcreteCarFactory();
		Car myCar = carFactory.getCar();
		myCar.drive();
	}
}

// ///////////////////////the products
interface Car {
	void drive();
}

class Mustang implements Car {
	public void drive() {
		System.out.println("I'm Mustang");
	}
}

class Ferrari implements Car {
	public void drive() {
		System.out.println("I'm ferrari");
	}
}

// /////////////////////// the factory
// the definition of the interface
interface CarFactory {
	public Car getCar();
}

// the real factory with an implementation of the getCar() factory method
// It would be useful only if I have multiple ConcreteCarFactory implementations like SportCarFactory, VintageCarFactory, LuxeCarFactory, CheapCarFactory ….. 
class ConcreteCarFactory implements CarFactory {
	// this class is instantiable
	public ConcreteCarFactory() {
		// some stuff
	}

	public Car getCar() {
		// choose which car you want, Ferrari or Mustang
		return new Ferrari();
	}
}
