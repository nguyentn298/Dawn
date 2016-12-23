package com.dante.learn.core;

public class MyEnumTest {
	public static void main(String[] args) {
		ClassInnerClass test = new ClassInnerClass();
		ClassInnerClass.TimeTable inner = test.new TimeTable();
		String result = inner.getJob(MyEnum.MONDAY);
		System.out.println(result);
		
		ClassInnerMethod test2 = new ClassInnerMethod();
		test2.classInMethod();
	}
}

class ClassInnerClass {
	public class TimeTable {
		public String getJob(MyEnum weekDay) {
			 if(weekDay == MyEnum.SATURDAY || weekDay == MyEnum.SUNDAY) {
			 return "no job";
			 }
			return "coding";
		}
	}
}

class ClassInnerMethod {
	public void classInMethod() {
		final int number = 69;
		
		Test3 test3 = new Test3();
		test3.printTest3(number);
		
		System.out.println("This is printer of Test2: " + (number + 1));
	}
	
	private class Test3 {
		public void printTest3(int number) {
			System.out.println("This is printer of Test3: " + number);
		}
	}
	
}




