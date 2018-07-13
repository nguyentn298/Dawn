package com.dante.learn.core.oop.inheritance;

public class TestchildClass extends TestParentClass {

	int childId;
	String childName;
	String childRole;

	public void testGetAccessModifier() {

		// public
		childId = parentId;

		// protected
		childName = parentName;

		// private (Cant get private field from superclass)
		// childRole = this.parentRole;
	}

}
