package com.dante.learn.pattern.behavior.iterator;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Iterator;

import com.dante.learn.pattern.behavior.iterator.channel.Channel;

public class MyIterator {
	public static void main(String[] args) {
		
		/*		=> When to use Iterator <=
		 * 1/ use an iterator and its remove method
		 * 2/ copy the elements you want to keep in another list
		 * 3/ Provide a way to access the elements of an aggregate object sequentially without exposing its underlying representation.
		 * Note: User don't know everything of collection (ArrayList, LinkedList, HashSet, TreeSet or Array), they have just get iterator and execute on it.
		 */
		
		testNoIterator();
		testIterator();
		
		/*
		 * 		==> hasNext() like this: <== (check size of collection)
		 * int index;
		 * if(index < names.lenght) {
		 * 		return true;
		 * } else
		 * 		return false;
		 * =====================================================================
		 * 		==> next() like this: 
			public Channel next() {
				Channel c = names.get(index);
				index++;
				return c;
			}
		 */
	}
	
	public static void testNoIterator() {
		System.out.println("===================================================");
		System.out.println("testNoooooo");
		
		List<String> list = myList();
		System.out.println("List's size: " + list.size());
		System.out.println();
		try{
			for (String string : list) {
				System.out.println("Name:" + string);
				if(string.equalsIgnoreCase("Talon")) {
					list.remove("Talon");
				}
				
				/*
				 * Exception appear because of removing.
				 */
			}
		} catch(ConcurrentModificationException e) {
			System.out.println(String.format("=> An Exception appears in console because of removing: [%s] <=", e));
		}
		
		System.out.println();
		System.out.println("List's size after removing: " + list.size());
		
	}
	
	public static void testIterator() {
		System.out.println("===================================================");
		System.out.println("testIterator");
		List<String> list = myList();
		System.out.println("List's size: " + list.size());
		System.out.println();
		
//		Iterator<String> iterator = list.iterator();
//		while(iterator.hasNext())
		
		for(Iterator<String> iterator = list.iterator();iterator.hasNext();) {
			String name = iterator.next();
			System.out.println("Name:" + name);
			if(name.equalsIgnoreCase("Talon")) {
				
				/*
				 * remove or copy element to another list
				 */
				iterator.remove();
			}
			
			/*
			 * Exception is not appear because of removing.
			 */
		}
		
		System.out.println();
		System.out.println(String.format("An Exception is not appeared in console because of iterator"));
		System.out.println("List's size after remove: " + list.size());
		System.out.println();
	}
	public static List<String> myList() {
		
		List<String> list = new ArrayList<String>(){
			{
				add("Zed");
				add("Talon");
				add("Shen");
				add("Kennen");
			}
		};
		return list;
	}
}
