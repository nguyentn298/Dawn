package com.dante.learn.core.junit;

public class Money {
	private double amount;
	private String currency;

	public Money() {

	}

	public Money(double amount, String currency) {
		super();
		this.amount = amount;
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Money add(Money m2) {
		Money m = new Money();
		m.setAmount(this.amount + m2.amount);
		return m;
	}
}
