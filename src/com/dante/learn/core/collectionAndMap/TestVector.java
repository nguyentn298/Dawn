package com.dante.learn.core.collectionAndMap;

import java.util.Enumeration;
import java.util.Vector;

public class TestVector {
	public static void main(String[] args) {
		// initial size is 3, increment is 2
		Vector v = new Vector(3, 2);
		System.out.println("Initial size: " + v.size());
		System.out.println("Initial capacity: " + v.capacity());

		v.addElement(new Integer(1));
		v.addElement(new Integer(2));
		v.addElement(new Integer(3));
		v.addElement(new Integer(4));
		System.out.println("Capacity after four additions: " + v.capacity());

		v.addElement(new Double(5.45));
		System.out.println("Current capacity after add 5.45 (Double): " + v.capacity());

		v.addElement(new Double(6.08));
		System.out.println("Current capacity after add 6.08 (Double): " + v.capacity());
		
		v.addElement(new Integer(7));
		System.out.println("Current capacity after add 7 (Integer): " + v.capacity());

		v.addElement(new Float(9.4));
		System.out.println("Current capacity after add 9.4 (Float): " + v.capacity());
		
		v.addElement(new Integer(10));
		System.out.println("Current capacity after add 10 (Integer): " + v.capacity());

		v.addElement(new Integer(11));
		
		System.out.println("Current capacity after add 11 (Integer): " + v.capacity());
		
		v.addElement(new Integer(12));
		System.out.println("Current capacity after add 12 (Integer): " + v.capacity());
		System.out.println("First element: " + (Integer) v.firstElement());
		System.out.println("Last element: " + (Integer) v.lastElement());

		if (v.contains(new Integer(3)))
			System.out.println("Vector contains 3.");

		// enumerate the elements in the vector.
		Enumeration vEnum = v.elements();
		System.out.println("\nElements in vector:");

		while (vEnum.hasMoreElements())
			System.out.print(vEnum.nextElement() + " ");
		System.out.println();
		
		Integer message = (Integer) v.firstElement();
		v.removeElement(message);
		System.out.println("Current capacity after remove first element: " + v.capacity());
		
		Enumeration vEnum2 = v.elements();
		System.out.println("\nElements in vector:");
		while (vEnum2.hasMoreElements())
			System.out.print(vEnum2.nextElement() + " ");
		System.out.println();
	}
}
