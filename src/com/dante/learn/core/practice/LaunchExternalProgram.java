package com.dante.learn.core.practice;

import java.io.IOException;

public class LaunchExternalProgram {
	public static void main(String[] args) {
		openChrome();
	}

	public static void openChrome() {
		Runtime runtime = Runtime.getRuntime();
		try {
			String[] arr = { "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe", "youtube.com" };
			runtime.exec(arr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void openNotepad() {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("notepad.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
