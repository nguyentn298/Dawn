package com.dante.ws.demo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "user")
public class User implements Serializable {

	private int id;
	private String name;
	private String job;

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

}
