package com.dante.learn.pattern.behavior.iterator.channel;

import java.util.ArrayList;
import java.util.List;

public class Channel {
	private String hero;
	private ChannelTypeEnum type;
	private int index;
	public Channel() {
		System.out.println(index);
	}

	public Channel(String hero, ChannelTypeEnum type) {
		super();
		this.hero = hero;
		this.type = type;
		
	}

	public String getHero() {
		return hero;
	}

	public void setHero(String hero) {
		this.hero = hero;
	}

	public ChannelTypeEnum getType() {
		return type;
	}

	public void setType(ChannelTypeEnum type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type + " - " + hero;
	}

	
	public static void main(String[] args) {
		
		
//		List<String> list = new ArrayList<String>();
//		list.add("test1");
//		list.add("test2");
//		list.add("test3");
//		for (String string : list) {
//			System.out.println(string);
//		}
		
		Channel c = new Channel();
	}
}
