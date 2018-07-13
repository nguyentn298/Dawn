package com.dante.ws.demo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User implements Serializable,Comparator<User> {

	private int id;
	private String name;
	private String job;

	public static void main(String[] args) {
		String[] b = {"a", "b"};
		
		List<Integer> list = new ArrayList<Integer>();
	}

	public User() {

	};

	public User(int id, String name, String job) {
		super();
		this.id = id;
		this.name = name;
		this.job = job;
	}

	public int getId() {
		return id;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	@XmlElement
	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return String.format("User[%s - %s - %s]", id, name, job);
	}

	@Override
	public int compare(User o1, User o2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
