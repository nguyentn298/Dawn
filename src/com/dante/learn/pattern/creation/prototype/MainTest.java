package com.dante.learn.pattern.creation.prototype;

public class MainTest {
	public static void main(String[] args) {
		PrototypeModule module = new PrototypeModule();
		String name = "two";
		
		initPrototype();
		Prototype protype = module.clonePrototypeByName(name);
		protype.execute();
		
	}
	
	public static void initPrototype() {
		PrototypeModule module = new PrototypeModule();
		module.addPrototype(new PrototypeOneImpl());
		module.addPrototype(new PrototypeTwoImpl());
		module.addPrototype(new PrototypeThreeImpl());
	}
}
