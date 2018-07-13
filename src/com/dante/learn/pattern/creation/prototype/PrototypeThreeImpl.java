package com.dante.learn.pattern.creation.prototype;

public class PrototypeThreeImpl implements Prototype{

	private static String name = "Three";
	@Override
	public Prototype clone() {
		return new PrototypeThreeImpl();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void execute() {
		System.out.println(String.format("My name is [%s] and i'm sleeping", name));
	}

}
