package com.dante.learn.pattern.creation.prototype;

public interface Prototype {
	public Prototype clone();
	public String getName();
	public void execute();
	
}
