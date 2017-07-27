package com.dante.learn.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestFile {
	public static void main(String[] args) throws IOException {
		
		System.out.println("====================== getParentFile() ===========================");
		// Use file.getParentFile();
		getParentFile();
		System.out.println();
		
		System.out.println("====================== createFolder() ===========================");
		// Use mkdir to create folder
		createFolder();
		System.out.println();
		
		System.out.println("====================== createFile() ===========================");
		// Use createNewFile() to create folder
		createFile();
		System.out.println();
		
		System.out.println("====================== getFileAndFolderName() ===========================");
		// Use folder.listFiles() to return path name (e.g: 210080349990.rar)
		// Use folder.list() to return name (e.g: D:\Nguyen\test\210080349990.rar)
		getFileAndFolderName();
		System.out.println();
		
		System.out.println("====================== getSpecificFile() ===========================");

		// Use FilenameFilter() in folder.list() to filter specific file
		// Use FileFilter() in folder.listFile() to filter specific file
		// e.g: folder.list(new FilenameFilter(){...}
		getSpecificFile();
		System.out.println();
		
		System.out.println("====================== checkExistFileInFolder() ===========================");
		// Use folder.list or listFile above, then check size of them
		checkExistFileInFolder();
		System.out.println();
		
		System.out.println("====================== getCurrentFile() ===========================");
		// Use System.getProperty("user.dir") to get current file 
		getCurrentFile();
		System.out.println();
		
		System.out.println("====================== testByteStream() ===========================");
		// Byte stream is suitable only for ASCII character set.
		testByteStream();
		System.out.println();
		
		System.out.println("====================== testCharaterStream() ===========================");
		// Character stream can support all types of character sets ASCII, Unicode, UTF-8, UTF-16 etc.
		testCharaterStream();
		System.out.println();
	}

	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	@SuppressWarnings("resource")
	public static void testByteStream() throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream("D:/Nguyen/test/input.txt");
			out = new FileOutputStream("D:/Nguyen/test/output.txt");

			int c;
			while ((c = in.read()) != -1) {
				// copy input to output
				out.write(c);
			}

			FileInputStream fin = new FileInputStream(
					"D:/Nguyen/test/input.txt");
			int size = fin.available();

			for (int i = 0; i < size; i++) {
				System.out.print((char) fin.read());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	public static void testCharaterStream() throws IOException {
		FileReader in = null;
		FileWriter out = null;
		BufferedReader buffer = null;
		try {
			in = new FileReader(new File("D:/Nguyen/test/input.txt"));
			out = new FileWriter(new File("D:/Nguyen/test/output.txt"));

			int c;
			if ((c = in.read()) != -1) {
				out.write(c);
			}
			
			//Get content of line by orginal method
			buffer = new BufferedReader(in);
			StringBuffer strs = new StringBuffer();

			String line = buffer.readLine();
			while (null != line) {
				strs.append(line).append("\n");
				line = buffer.readLine();
			}
			System.out.println(strs);
			
			// java7: Get all content of file
			String test = readFile("D:/Nguyen/test/input.txt", StandardCharsets.UTF_8);
			System.out.println(test);
			
			// java7: Get content of file by line 
			List<String> lines = Files.readAllLines(Paths.get("D:/Nguyen/test/input.txt"), StandardCharsets.UTF_8);
			for (String string : lines) {
				System.out.println("List: " + string);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (buffer != null) {
				buffer.close();
			}
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}

	}

	public static boolean checkExistFileInFolder() {
		File folder = new File("D:/Nguyen/test");
		String[] errorFiles = folder.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith("_error" + ".txt");
			}
		});
		
		System.out.println("size: " + errorFiles.length);
		
		return errorFiles.length > 0;
	}

	public static void getSpecificFile() {
		File folder = new File("D:/Nguyen/test");
		
		// Use file.list() return name (e.g: 210080349990.rar)
		System.out.println("---- Use file.list() ----");
		String[] useList = folder.list(new FilenameFilter() {
			@Override
			public boolean accept(File pathname, String name) {
//				System.out.println("Path: " + pathname);
				return name.endsWith(".txt") && !name.endsWith("_hehe.txt");
			}
		});
		for (String string : useList) {
			System.out.println(string);
		}
		System.out.println("");
		
		// Use file.listFiles() return path name (e.g: D:\Nguyen\test\210080349990.rar)
		System.out.println("---- Use file.listFiles() ----");
		File[] useListFile = folder.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
//				System.out.println("Path: " + pathname);
				return pathname.getName().endsWith(".txt") && !pathname.getName().endsWith("_hehe.txt");
			}
		});

		for (File file : useListFile) {
			System.out.println("File: " + file);
		}
	}

	public static void getFileAndFolderName() {
		File folder = new File("D:/Nguyen/test");
		
		System.out.println("---- Use file.list() ----");
		String[] useList = folder.list();
		for (String string : useList) {
			System.out.println("File or Folder: " + string);
		}
		
		System.out.println();
		System.out.println("---- Use file.listFiles() ----");
		File[] useListFile = folder.listFiles();

		for (int i = 0; i < useListFile.length; i++) {
			if (useListFile[i].isFile()) {
				System.out.println("File" + i + ": " + useListFile[i].getName());
			} else if (useListFile[i].isDirectory()) {
				System.out.println("Folder" + i + ": " + useListFile[i].getName());
			}
		}
	}

	public static void createFolder() {
		File file = new File("D:/Nguyen/test/user1", "user2/test3");
		if (!file.exists()) {
			System.out.println("No");
			file.mkdirs();
		} else {
			System.out.println("Yes");
		}

		System.out.println(file.getAbsolutePath());
	}

	public static void createFile() throws IOException {
		File file = new File("D:/Nguyen/test/user1", "user2/test3.txt");
		if (!file.exists()) {
			System.out.println("No");
			file.createNewFile();
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
	
	public static void getCurrentFile() {
		System.out.println(System.getProperty("user.dir"));
	}
}
