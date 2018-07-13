package com.dante.learn.pattern.creation.prototype;

public class PrototypeTwoImpl implements Prototype{

	private static String name = "Two";
	@Override
	public Prototype clone() {
		return new PrototypeTwoImpl();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void execute() {
		System.out.println(String.format("My name is [%s] and i'm eating", name));
	}

}