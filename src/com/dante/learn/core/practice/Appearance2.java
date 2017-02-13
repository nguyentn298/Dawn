package com.dante.learn.core.practice;

import java.util.Random;

public class Appearance2 {
	public static void main(String[] args) {
//		square(15);
		cup(6);
		
//		System.out.println(Math.random()*(6));
		
	}
	
	public static void square(int n) {
		
		String result = "";
		if(n <= 0) {
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(i == 0 || i == n - 1) {
				for(int j = 0; j < n; j++) {
					result += "* ";
				}
				result += "\n";
				continue;
			}
			
			for(int k = 0; k < n; k++) {
				
				if(k == 0) {
					result += "* ";
					continue;
				}
				
				if(k == n - 1) {
					result += "*\n";
				} else {
					result += "  ";
				}
			}
			
		}
		
		System.out.println(result);
	}
	
	public static void cup(int n) {
		
		String result = "";
		int handCup = 0;
		int down = 0;
		int count = 0;
		if(n <= 0) {
			return;
		}
		if(n%2 == 0) {
			handCup = down = n/2;
		} else {
			handCup = down = n/2 + 1;
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		// steam
		int steam = 0;
		for(int h =0; h <= n/2; h++) {
			result += "  ";
			
			if(h == 0) {
				steam = n/2;
			} else {
				steam -= 1;
			}
			
			for(int t =steam; 0 < t && t <= steam; t--) {
				result += " ";
			}
			
			for(int k = 0; k <= h; k++) {
				result += "()";
			}
			result += "\n";
		}
		
		for(int i = 0; i < n; i++) {
			
			result += " ";
			
			if(i == 0 || i == n -1) {
				for(int j = 0; j < n; j++) {
					result += "* ";
				}
				result += "\n";
				continue;
			}
			
			for(int k = 0; k < n; k++) {
				if(k == 0) {
					result += "* ";
					continue;
				}
				
				if(k == n -1) {
					result += "*";
					if(i == 1 || i == n-2) {
						for(int l = 0; l < handCup; l++) {
							result += "=";
						}
					} else {
						for(int l = 0; l <= handCup; l++) {
							result += " ";
							
							if(l == handCup) {
								result += ")";
							}
						}
					}
					
					
					result += "\n";
				} else {
					result += "  ";
				}
				
				
			}
		}
		
		System.out.println(result);
	}
}
