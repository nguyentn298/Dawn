package com.dante.learn.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunCMByJava {
	public static void main(String[] args) throws Exception {
		
		/**
		 * cmd.exe is the Command Prompt. /c tells the Command Prompt to run the rest of the line and then exit.
		 * Using a Command Prompt allows me to change directory - cd is built in to the Command Prompt
		 */
		
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
				"cd \"C:\\Program Files\\Microsoft SQL Server\" && dir");
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}
	}
}
