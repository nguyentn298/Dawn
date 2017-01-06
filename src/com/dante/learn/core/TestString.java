package com.dante.learn.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

public class TestString {
	public static void main(String[] args) {
		TestString.covertListToStringByCharacter();

		
	}
	
	public void myString() {
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
	
	public static void covertListToStringByCharacter() {
		List<String> list = new ArrayList<>();
		// this is a cat
		list.add("this");
		list.add("is");
		list.add("a");
		list.add("cat");
		
		// use any character
		String useSpace = StringUtils.collectionToDelimitedString(list, " ");
		System.out.println("Use space: " + useSpace);
		System.out.println();
		
		// use prefix and suffix
		String useSpaceAndPrefixAndSuffix = StringUtils.collectionToDelimitedString(list, " ", "(", ")");
		System.out.println("Use Space, Prefix And Suffix" + useSpaceAndPrefixAndSuffix);
		System.out.println();
		
		// use comma
		String useComma= StringUtils.collectionToCommaDelimitedString(list);
		System.out.println("Use comma: " + useComma);
		System.out.println();
	}
}
