package com.dante.learn.core.enumAndInnerClass;

public class TestEnum2 {
	public static void main(String[] args) {
		System.out.println("*************** Get Single Value ************");
		// single
		Gender gender = Gender.MALE;
		System.out.println(gender.getCode());
		System.out.println(gender.getNumber());
		System.out.println(gender.getText());
		System.out.println("*************** Get All Values ************");
		
		// get all value of enum
		Gender[] test = gender.values();
		for (Gender gender2 : test) {
			System.out.println(gender2.getCode());
			System.out.println(gender2.getNumber());
			System.out.println(gender2.getText());
		}
		System.out.println("*************** Get value by specific property ************");
		
		Gender gender3 = Gender.getGenderByCode("M");
		System.out.println(gender3);
	}
}

enum Gender {

	
	// Initialize the elements from Constructor.
		// The element is always final, static
		// depend to constructor
		MALE("M", "Male", "123"), FEMALE("F", "Female", "456"), TEST("T", "123", "789");

		private String code;
		private String text;
		private String number;

		// Constructor of Enum internal use only
		// Modifier of constructor is private
		// If you do not declare private, java alway understand is private.
		private Gender(String code, String text, String number) {
			this.code = code;
			this.text = text;
			this.number = number;
		}

		// Static method return Gender by code.
		public static Gender getGenderByCode(String code) {
			for (Gender gender : Gender.values()) {
				if (gender.code.equals(code)) {
					return gender;
				}
			}
			return null;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}
}
