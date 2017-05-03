package com.dante.learn.core;

import java.io.File;

public class TestFile {
	public static void main(String[] args) {
//		getParentFile();
//		createFile();
		System.out.println(System.getProperty("user.dir"));
		
	}
	

	
	public static void listFolderInFolder() {
		File file = new File("D:\\Nguyen\\test");
		String[] names = file.list();

		for (String name : names) {
			if (new File("D:\\Nguyen\\test\\" + name).isDirectory()) {
				System.out.println(name);
			}
		}
	}
	
	public static void createFile() {
		File file = new File("D:/Nguyen/test/user1/test3.txt");
		if(!file.exists()) {
			System.out.println("false");
			file.mkdirs();
		} else {
			System.out.println("Yes");
		}
		
		System.out.println(file.getAbsolutePath());
	}
	
	public static void getParentFile() {
		File file = new File("D:/Nguyen/test/user1", "user2/test3.txt");
		File parentFile = file.getParentFile();
		System.out.println(parentFile.getName());
	}
}
