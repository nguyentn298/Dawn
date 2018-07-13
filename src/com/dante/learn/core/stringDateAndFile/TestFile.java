package com.dante.learn.core.stringDateAndFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class TestFile {
	public static void main(String[] args) throws IOException {
		
//		System.out.println("====================== getParentFile() ===========================");
//		// Use file.getParentFile();
//		getParentFile();
//		System.out.println();
//		
//		System.out.println("====================== createFolder() ===========================");
//		// Use mkdir to create folder
//		createFolder();
//		System.out.println();
//		
//		System.out.println("====================== createFile() ===========================");
//		// Use createNewFile() to create folder
//		createFile();
//		System.out.println();
//		
//		System.out.println("====================== getFileAndFolderName() ===========================");
//		// Use folder.listFiles() to return path name (e.g: 210080349990.rar)
//		// Use folder.list() to return name (e.g: D:\Nguyen\test\210080349990.rar)
//		getFileAndFolderName();
//		System.out.println();
//		
//		System.out.println("====================== getSpecificFile() ===========================");
//
//		// Use FilenameFilter() in folder.list() to filter specific file
//		// Use FileFilter() in folder.listFile() to filter specific file
//		// e.g: folder.list(new FilenameFilter(){...}
//		getSpecificFile();
//		System.out.println();
//		
//		System.out.println("====================== checkExistFileInFolder() ===========================");
//		// Use folder.list or listFile above, then check size of them
//		checkExistFileInFolder();
//		System.out.println();
//		
//		System.out.println("====================== getCurrentFile() ===========================");
//		// Use System.getProperty("user.dir") to get current file 
//		getCurrentFile();
//		System.out.println();
		
//		System.out.println("====================== testByteStream() ===========================");
		// Byte stream is suitable only for ASCII (American Standard Code for Information Interchange) character set.
//		testByteStream();
//		System.out.println();
		
//		System.out.println("====================== testCharaterStream() ===========================");
//		// Character stream can support all types of character sets ASCII, Unicode, UTF-8, UTF-16 etc.
//		testCharaterStream();
//		System.out.println();
		
		double i = 12.456;
		System.out.println(Math.round(i));
	}

	public static void readFileOriginal() {
		
	}
	public static String readFileByByte(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	public static void readFileByLine() {
		
		// java7: Get content of file by line (just aSCII)
		List<String> lines = new ArrayList<String>();
		try {
			lines = Files.readAllLines(Paths.get("D:/Nguyen/test/input.txt"), StandardCharsets.UTF_8);
			for (String string : lines) {
				System.out.println("List: " + string);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void copyFile(){
		FileReader in = null;
		FileWriter out = null;
		BufferedReader buffer = null;
		
		try {
			in = new FileReader(new File("D:/Nguyen/test/input.txt"));
			out = new FileWriter(new File("D:/Nguyen/test/output.txt"));

			// copy file A to file B with binary digit (it will convert string to digit)
			int c;
			if ((c = in.read()) != -1) {
				out.write(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(in);
			
		}
	}

	@SuppressWarnings("resource")
	public static void testByteStream() throws IOException {
		/**
		 * Programs use byte streams to perform input and output of 8-bit bytes. All byte stream classes are descended from InputStream and OutputStream.
		 * 
		 * Notice that both CopyBytes and CopyCharacters use an int variable to read to and write from. 
		 * However, in CopyCharacters, the int variable holds a character value in its last 16 bits; in CopyBytes, 
		 * the int variable holds a byte value in its last 8 bits.
		 */
		FileInputStream fin = new FileInputStream("D:/Nguyen/test/input.txt");
		int size = fin.available();
		for (int i = 0; i < size; i++) {
			System.out.print((char) fin.read());
		}
	}

	public static void testCharaterStream() throws IOException {
		
		/**
		 * All character stream classes are descended from Reader and Writer. 
		 * As with byte streams, there are character stream classes that specialize in file I/O: FileReader and FileWriter
		 */
		FileReader in = null;
		
		try {
			in = new FileReader("D:/Nguyen/test/input.txt");
			int test;
			while ((test = in.read()) != -1) {
				char str = (char) test;
				System.out.println(test + " - " + str);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(in);
		}

	}
	
	@Test
	public void testBufferStream() {
		FileReader in = null;
		BufferedReader buffer = null;
		List<String> list = new ArrayList<String>();
		int count = 0;
		BufferedWriter out;
		try {
			in = new FileReader("D:/Nguyen/test.csv");
			out = new BufferedWriter(new FileWriter("D:/Nguyen/testOutput.csv"));
			
			//Get content of line by orginal method
			buffer = new BufferedReader(in);
			String line = buffer.readLine();
			
			StringBuffer strs = new StringBuffer();
			while (null != line) {
				strs.append(line).append("\n");
				line = buffer.readLine();
				list.add(line);
			}
//			System.out.println(strs);
			
			
			for (String string : list) {
//				System.out.println(string);
				if(string == null) {
					continue;
				}
				String[] arr = string.split(",");
				if(arr.length <= 2) {
					System.out.println(string + "-------------------------------------------------------------------------");
					out.write(string + "-------------------------------------------------------------------------"+ "\n");
					count++;
				} else {
					System.out.println(string);
					out.write(string + "\n");
				}
				
				
			}
			
			System.out.println("count: " + count);
			
			// java7: Get all content of file
//			String test = readFileByByte("D:/Nguyen/test/input.txt", StandardCharsets.UTF_8);
//			System.out.println(test);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(buffer);
			IOUtils.closeQuietly(in);
//			IOUtils.closeQuietly(out);
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
