package com.dante.crazy;

import java.util.ArrayList;
import java.util.List;

public class findCoupleNumberBySpecificNumber {
	List<Integer> list = new ArrayList<Integer>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			for(int i = 0; i < 11; i++) {
				add(i);
			}
			
		}
	};

	/**
	 * Find couple number if Addition of it is specific number input.
	 */
	public void findCoupleNumber(int a) {
		
		List<String> listCouple = new ArrayList<String>();
		for(int i = 0; i < list.size(); i++) {
			for(int j = list.size(); j > 0; j--) {
				if(i + j == a) {
					listCouple.add(i + "-" + j);
				}
			}
		}
		
		for (String string : listCouple) {
			System.out.println(string);
		}
	}
	
	public static void main(String[] args) {
		findCoupleNumberBySpecificNumber test = new findCoupleNumberBySpecificNumber();
		test.findCoupleNumber(15);
	}
	
	public void findCoupleReview() {
		
	}
}
