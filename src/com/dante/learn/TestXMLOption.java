package com.dante.learn;

import org.apache.xmlbeans.XmlOptions;

public class TestXMLOption {
	public static void main(String[] args) {
		XmlOptions opts = new XmlOptions();
		opts.setSavePrettyPrint();
		opts.setSavePrettyPrintIndent(4);
		
//		Animal a = new Animal();
//		a.setType("This is a dog, test");
//		System.out.println(a.xmlText(opts));
	}

	
	public class Animal {
		String type;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		

	}
	
	
}
