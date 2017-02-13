package com.dante.learn.core;

import java.io.File;

public class TestFile {
	public static void main(String[] args) {
		File file = new File("D:/Nguyen/test/user1", "user2/test3.txt");
		if(!file.exists()) {
			System.out.println("false");
		} else {
			System.out.println("Yes");
		}
		
		System.out.println(file.getAbsolutePath());
	}
}
