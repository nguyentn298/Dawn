package com.dante.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dante
 *
 */
/**
 * @author dante
 *
 */
@Entity
@Table(name="client")
public class Client implements Comparable<Client> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CLIENT_ID")
	private int id;
	
	@Column(name="CLIENT_NAME")
	private String name;
	
	@Column(name="CLIENT_AGE")
	private int age;
	
	@Column(name="CLIENT_MONEY")
	private double money;

	public Client(int id, String name, int age, double money) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.money = money;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	/*
	 * Sort by original
	 */

	@Override
	public int compareTo(Client client) {
		int compareName = name.compareTo(client.getName());
		int compareId = id - client.getId();
		int compareAge = age - client.getAge();
		int compareMoney = (int) (money - client.getMoney());

		// DESC

//		int compareName = client.getName().compareTo(name);
//		int compareId = client.getId() - id;
//		int compareAge = client.getAge() - age;
//		int compareMoney = (int) (client.getMoney() - money);
		
		return (compareId == 0) ? (compareName == 0 ? compareAge : compareName) : compareId;
	}

	/*
	 * Sort by compareToBuilder()
	 */

//	@Override
//	public int compareTo(Client o) {
//		return new CompareToBuilder()
//		.append(this.name, o.name)
//		.append(this.money, o.money)
//		.toComparison();
//	}

	/*
	 * Compare by original
	 */

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Client)) {
			return false;
		}
		
		Client client = (Client) obj;
		boolean compareId = (this.id == client.id);
		boolean compareName = this.name.equals(name);
		boolean compareAge = (this.age == client.age);
		boolean compareMoney = (this.money == client.money);
		
		return (compareId ? (compareName ? compareMoney : compareName): compareId);
	}

	/*
	 * Compare by equalBuilder
	 */

//	@Override
//	public boolean equals(Object obj) {
//		Client client = (Client) obj;
//		return new EqualsBuilder()
//		.append(name, client.name)
//		.append(money, client.money)
//		.append(age, client.age)
//		.append(id, client.id)
//		.isEquals();
//	}
}
