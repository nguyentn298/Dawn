package com.dante.learn.json;

public class Person {
	public String firstName;
	public String lastName;
	public int age;

	public Person() {
	}

	public Person(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public String toString() {
		return "[" + firstName + " " + lastName + " " + age + "]";
	}
}
