package com.dante.learn.core.oop.basic;

public class ObjectAndVariable {
	public String name ="danteDefault";
	public int grade = 69;

	// if dont have accessor method
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

}
