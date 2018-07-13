package com.dante.learn.core.collectionAndMap;

import java.util.Stack;

import org.junit.Test;

/**
 *	A Stack uses a "Last In First Out (LIFO)" principle
 */
public class TestStack {

	/**
	 *  The push(): 	pushes an object onto the top of the Stack.
	 *  The peek(): 	returns the object at the top of the Stack
	 *  The pop():	 	returns the object at the top of the stack, and removes the object from the Stack. 
	 */
	@Test
	public void runStack() {
		Stack stack = new Stack();

		stack.push("1");
		stack.push("2");
		stack.push("3");
		System.out.println(stack);
		
		Object objTop = stack.peek(); 	// Retrieve element from head.
		System.out.println(stack);

		Object obj3 = stack.pop(); 		// Retrieve element from head and Remove it 
		System.out.println(obj3);
		System.out.println(stack);

		Object obj2 = stack.pop(); 		// 	Retrieve element from head and Remove it 
		Object obj1 = stack.pop(); 		//  Retrieve element from head and Remove it 
		
		System.out.println(stack);
		Object obj4 = stack.pop();  	// throw exception if null
		System.out.println(stack);
	}
}
