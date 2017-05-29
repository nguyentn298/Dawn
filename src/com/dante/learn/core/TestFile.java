package com.dante.learn.core;

import java.io.File;
import java.io.FilenameFilter;

public class TestFile {
	public static void main(String[] args) {
//		getParentFile();
//		createFile();
		System.out.println(System.getProperty("user.dir"));
		System.out.println(hasErrorFile());
		
		System.out.println("===============");
		listFileInFolder();
	}
	
	public static boolean hasErrorFile() {
		
		File inputDir = new File("D:/Nguyen/test/abc");
		String[] errorFiles = inputDir.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith("_error" + ".txt");
			}
		});
		return errorFiles.length > 0;
	}
	
	public static void checkFileInFolder() {
		File uuidFolder = new File("D:/Nguyen/test/abc");
		File ocrErrorFile = new File(uuidFolder, "xyz");
		if (!ocrErrorFile.exists()) {
			System.out.println("not exists");
		} else {
			System.out.println("Right!!");
		}
	}


	public static void listFileInFolder() {
		File folder = new File(
				"D:/home/jboss/dem/2947bca2-0c4f-49a6-bf65-f016f979a8a6");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
	}
	
	public static void listFolderInFolder() {
		File file = new File("D://Nguyen//test");
		String[] names = file.list();

		for (String name : names) {
			if (new File("D://Nguyen//test//" + name).isDirectory()) {
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
