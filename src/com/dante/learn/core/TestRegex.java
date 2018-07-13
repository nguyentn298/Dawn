package com.dante.learn.core;

//import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestRegex {

	public static void main (String args[]) {

		/*
		 * 	Find String
		 */

		String find1 = "term is 120 months";
		String regex1 = "[0-9]{1,3}";
		System.out.println("1 Original string->:"+find1+":  new string->"+findScript(find1, regex1));
		
		String find2 = "perentage of loan is: 4.715 % starting january 1, 2017";
		String regex2 = "[0-9]{1,2}\\.[0-9]{1,3}";
		System.out.println("2 Original string->:"+find2+":  new string->"+findScript(find2, regex2));

		/*
		 * 	Remove String
		 */

		String find3 = "parse this baby to Hamilton Constitution Writers";
		String regex3 = ".*to ";
		System.out.println("3 Original string->:"+find3+":  new string->"+removeScript(find3, regex3));
		

		String find4 = "We do not want parentheses (like this stuff 4.125) in our strings";
		String regex4 = " \\([a-zA-Z0-9. ]*\\)";
		System.out.println("4 Original string->:"+find4+":  new string->"+removeScript(find4, regex4));

		/*
		 * 	Replace old string by new string without pattern and matcher
		 */

		System.out.println();
		String oldString = "This is a dog dog dog";
		String regex = "dog";
		String newString = "cat";
		String replaceString = oldString.replaceFirst(regex, newString);
		System.out.println(replaceString);
		
		String test = testPosition("0089705941_ImagedLoanFile_pbm-slacy_20141211_115612047", "(\\d+)[_](\\w+)");
		System.out.println("Test: " + test);
	}
	
	private static String removeScript(String content, String sregex) {
//		Pattern p = Pattern.compile(sregex, Pattern.DOTALL);
		Pattern p = Pattern.compile(sregex);
		return p.matcher(content).replaceAll("");
	}

	private static String findScript(String content, String sregex) {
//		Pattern p = Pattern.compile(sregex, Pattern.DOTALL );
		Pattern p = Pattern.compile(sregex);
		Matcher matchmaker = p.matcher(content);
		if (matchmaker.find()) {
			return (matchmaker.group(0));
		}
		
		return null;
	}
	
	public static String testPosition(String content, String sregex) {
		Pattern pattern = Pattern.compile(sregex);
		Matcher matcher = pattern.matcher(content);
		String loanNumber = "";
		if (matcher.find()) {
			loanNumber = matcher.group(1);
		}
		
		return loanNumber;
	}
	
	@Test
	public void testOperator() {
		Pattern p = Pattern.compile(".*@.*[.]//d");
		Matcher matchmaker = p.matcher("test@abc.com");
		
		if (matchmaker.matches()) {
			System.out.println(matchmaker.group(0));
		} else {
			System.out.println("Fail");
		}
		
		
	}
}
