package com.dante.learn.pattern.creation.prototype;

public class PrototypeOneImpl implements Prototype{

	private static String name = "One";
	@Override
	public Prototype clone() {
		return new PrototypeOneImpl();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void execute() {
		System.out.println(String.format("My name is [%s] and i'm working", name));
	}

}
