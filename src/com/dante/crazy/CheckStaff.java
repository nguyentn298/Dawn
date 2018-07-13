package com.dante.crazy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class CheckStaff {

	public Map<String, String> staff = new HashMap<String, String>() {
		{
			put("nv1", "pass1");
			put("nv2", "pass2");
			put("nv3", "pass3");
		}
	};

	public Map<String, String> authenticationStaff = new HashMap<String, String>() {
		{
			put("nv1", "true");
			put("nv2", "false");
			put("nv3", "true");
		}
	};

	public static void main(String[] args) {
		CheckStaff test = new CheckStaff();
		test.operator();
	}

	public void operator() {
		Iterator<Entry<String, String>> iterator = staff.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> map = iterator.next();
			if (authenticationStaff.get(map.getKey()).equals("true")) {
				System.out.println(map.getKey());
			}
		}
	}

}
