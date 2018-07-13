package com.dante.learn;

public class TestEnvironment {
	public static void main(String[] args) {
		String str = System.getenv("AWS_REGION_NAME");
		System.out.println(str);
		
		String javaVersion = System.getProperty("java.version");
		System.out.println(javaVersion);
	}
}
