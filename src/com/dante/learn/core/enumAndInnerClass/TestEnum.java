package com.dante.learn.core.enumAndInnerClass;

import java.util.HashMap;
import java.util.Map;

public class TestEnum {
	
	public static void main(String[] args) {
		TestEnum test = new TestEnum();
		String str = "TEST2";
		
		Map<MyEnum, String> map = new HashMap<MyEnum, String>(){
			{
				put(MyEnum.TEST1, "111");
				put(MyEnum.TEST2, "222");
				put(MyEnum.TEST3, "333");
			}
		};
		for (Map.Entry<MyEnum, String> element : map.entrySet()) {
			System.out.println(element.getKey() + " - " + element.getValue());
		}
	}
	public enum MyEnum{
		TEST1,
		TEST2,
		TEST3
	}
}

