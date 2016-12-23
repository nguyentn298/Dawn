package com.dante.learn.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dante.db.entity.Client;
import com.dante.db.entity.ComparatorCustom;

public class TestComparatorAndComparable {
	public static void main(String[] args) {
		List<Client> clients = new ArrayList<>();
		clients.add(new Client(4, "cc", 25, 70.0));
		clients.add(new Client(1, "bb", 28, 77.0));
		clients.add(new Client(5, "aa", 30, 89.0));
		clients.add(new Client(1, "dda", 28, 100));
		clients.add(new Client(1, "dda", 28, 100.0));
		System.out.println();
		System.out.println("value at 3: " + clients.get(3));
		System.out.println("value at 4: " + clients.get(4));
		System.out.println(clients.get(3).equals(clients.get(4)));
		
		System.out.println();
		testComparable(clients);
	}
	
	public static void testComparable(List<Client> clients) {

		Collections.sort(clients);
		System.out.printf("id\tname\tage\tmoney\t");
		System.out.println();
		for (Client client : clients) {
			
			System.out.printf("%d\t%s\t%d\t%f\t", client.getId(), client.getName(), client.getAge(), client.getMoney());
			System.out.println();
		}
	}
	
	public static void testComparator(List<Client> clients) {
		Collections.sort(clients, new ComparatorCustom());
		System.out.printf("id\tname\tage\tmoney\t");
		System.out.println();
		for (Client client : clients) {
			
			System.out.printf("%d\t%s\t%d\t%f\t", client.getId(), client.getName(), client.getAge(), client.getMoney());
			System.out.println();
		}
	}
}
