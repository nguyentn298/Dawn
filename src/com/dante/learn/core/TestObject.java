package com.dante.learn.core;

import java.util.ArrayList;
import java.util.List;

import com.dante.db.entity.CardDetail;
import com.dante.db.entity.Client;

public class TestObject {
	public static void main(String[] args) {
		List<CardDetail> cardDetails = new ArrayList<>();
		
		Client client2 = new Client(2, "test2", 20, 20000);
		Client client3 = new Client(3, "test3", 30, 30000);
		CardDetail cd2 = new CardDetail(client2, 2);
		CardDetail cd3 = new CardDetail(client3, 3);
		int a = 5;
		int b = 0;
		System.out.println("Before");
		System.out.println("a: " + a);
		System.out.println("client2: " + client2.getName());
		System.out.println("client2 number: " + client2.getId());
		System.out.println("client3: " + client3.getName());
		System.out.println();
		cardDetails.add(cd2);
		cardDetails.add(cd3);
		
		
		for (CardDetail cardDetail : cardDetails) {
			Client c1 = cardDetail.getClient();
			c1.setName("test11");
			c1.setId(90);
			 b = a;
			 b = 6;
		}
		
		System.out.println("After");
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("client2: " + client2.getName());
		System.out.println("client2 number: " + client2.getId());
		System.out.println("client3: " + client3.getName());
		System.out.println();
		
	}
}
