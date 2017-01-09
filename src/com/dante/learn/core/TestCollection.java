package com.dante.learn.core;

import java.util.LinkedList;
import java.util.Queue;

public class TestCollection {
	public static void main(String[] args) {
		Queue<String> queueValues = new LinkedList<String>();
		queueValues.add("this");
		queueValues.add("is");
		queueValues.add("a");
		queueValues.add("Cat");
		
		queueValues.remove("a");
		if(queueValues.contains("is")) {
			
		}
		System.out.println(queueValues);
		
	}
}
