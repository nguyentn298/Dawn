package com.dante.learn.jms.demo;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MySender {
	public static void main(String[] args) {
		try {
			InitialContext ctx = new InitialContext();
			Object f = (Object) ctx.lookup("myQueueConnectionFactory");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
