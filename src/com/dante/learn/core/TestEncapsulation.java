package com.dante.learn.core;

public class TestEncapsulation {
	
	/**
	  * Scenario:
	  * A car is move on any streets
	  * car1 has 100000 speeds (false)
	  * car2 has normal speeds (true)
	  */
	 public static void main(String[] args) {
		 /**
		  * Misuse
		  */
		Car car1 = new Car();
		car1.isStarted = true;
		car1.speed = 10;

		// go to the moon
		car1.speed = 100000000;
		car1.isReverse = false;

		 /**
		  * Sensible: USER CANNOT UPDATE speed (private field), USER HAS JUST USED method to update by developer's idea. 
		  */
		
		CarWithEncapsulation car2 = new CarWithEncapsulation();
		car2.start();
		car2.accelerate();
		car2.accelerate();
		car2.accelerate();
		car2.doBreak();
		car2.doBreak();
		car1.isReverse = false;
		 
	}
}

class Car {
	public float speed =0;
	public boolean isReverse = false;
	public boolean isStarted = false;
}

class CarWithEncapsulation {
	
	/* 
	 * ===================================== ENCAPSULATION ================================================================================================
	 * 
	 * Encapsulation relate to Access modifier (public , default, protected, private)
	 * PRIVATE FIELD must be store inside a method (example set or get)
	 * Example: 
			public void accelerate() {
				if (isStarted) {
					speed += 10;
				} else {
					// car is not started yet
				}
			}
	 * private isStarted and speed is ONLY working on accelerate() method
	 * Client do not know what happen in accelerate() method with isStarted (true) and speed (+=10)
	 * it must be executed in it's OWN METHOD.
	 * 
	 * Another EXAMPLE of the encapsulation is BankAccount. 
	 * Clients cannot directly access balance of the account. (balance is the PRIVATE FIELD)
	 * The access is controlled via methods like checkBalance(), deposit(), withdraw() method.
	 */
	
	private float speed = 0;
	private boolean isReverse = false;
	private boolean isStarted = false;
	
	public void start() {
		if (isStarted) {
			// the car is already started
		} else {
			isStarted = true;
		}
	}

	public void accelerate() {
		if (isStarted) {
			speed += 10;
		} else {
			// car is not started yet
		}
	}

	public void doBreak() {
		if (isStarted) {
			speed -= 10;
		} else {
			// car is not started yet
		}
	}

	public void stop() {
		if (isStarted) {
			isStarted = false;
		} else {
			// car is already stopped
		}
	}
}

