package com.dante.learn.pattern.creation.prototype;

import java.util.ArrayList;
import java.util.List;

public class PrototypeModule {
	private final static List<Prototype> prototypes = new ArrayList<>();

	public void addPrototype(Prototype p) {
		prototypes.add(p);
	}

	public Prototype clonePrototypeByName(String name) {
		for (Prototype prototype : prototypes) {
			if (prototype.getName().equalsIgnoreCase(name)) {
				return prototype.clone();
			}
		}

		System.out.println(name + ": doesn't exist");
		return null;
	}
}
