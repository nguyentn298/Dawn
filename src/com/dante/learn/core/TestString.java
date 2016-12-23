package com.dante.learn.core;

public class TestString {
	public static void main(String[] args) {
		String test = "I'm Cat cat";
		int indexOf = test.indexOf("a");
		String subString = test.substring(0, indexOf);
		String subStringofBeging = test.substring(indexOf);
		System.out.println("Index Of: " + indexOf);
		System.out.println("");
		System.out.println("SubString 0 --> indexOf: " + subString);
		System.out.println("");
		System.out.println("SubString of indexOf: " + subStringofBeging);
		Byte number = 1;
		
		String url = "http://localhost:8080/Dawn/user?name=dante&age=19";
		String id = "id=1&";
		
		int firstUrl = url.indexOf("?");
		String fullUrl = url.substring(0, firstUrl + 1) + id + url.substring(firstUrl + 1);
		System.out.println("");
		System.out.println("full url: " + fullUrl);
		
	}
}
