package com.dante.learn.core.practice;

public class Appearance {
	public static void main(String[] args) {
//		sample(5);
		sample2(8);
	}

	public static void sample(int n) {
		String result = "";
		if (n == 0) {
			return;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				// i = 1
				if (j == 0 && i == 0) {
					result = "*\n";
					continue;
				}

				if (j == 0) {
					result += "* ";
					continue;
				}
				
				if(i != n -1) {
					
					if (j == i) {
						result += "*\n";
						continue;
					} 
					result += "  ";
					
				} else {
					result += "* ";
				}

			}
		}

		System.out.println(result);
	}
	
	public static void sample2(int n) {
		
		String result = "";
		
		if(n == 0) {
			return;
		}
		
		// foot
		if(n%2==0) {
			int position = n/2;
			for(int i = 0; i < position; i++) {
				for(int j = 0; j < position; j++) {
					result += " ";
				}
				for(int k = 0; k < position; k++) {
					result += "* ";
				}
				result += "\n";
			}
		} else {
			int position = n/2;
			for(int i = 0; i < position; i++) {
				for(int j = 0; j < position; j++) {
					result += " ";
				}
				for(int k = 0; k < position + 1; k++) {
					result += "* ";
				}
				result += "\n";
			}
		}
		
		// body
		for(int i = n - 1; 0 <= i && i < n; i--) {
			
			for(int j = 0; j <= i; j ++) {
				
				if(i == n - 1 && i != j) {
					result += "* ";
					continue;
				} else if(i == n - 1 && i == j) {
					result += "*\n";
					continue;
				}
				
				if(i != n-1) {
					
					if(i == j) {
						result += "*\n";
						continue;
					} else {
						result += "* ";
					}
				}
			}
			
				for(int k = 0; k <= n-1-i; k++) {
						result += " ";
				}
			
		}
		
		System.out.println(result);
	}
}
