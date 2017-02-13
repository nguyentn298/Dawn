package com.dante.learn.core.junit;

import org.apache.commons.lang3.builder.EqualsBuilder;

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
		Money m3 = new Money();
		m3.setAmount(this.amount + m2.amount);
		m3.setCurrency("VND");
		return m3;
	}
	
	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Money)) {
			return false;
		}
		
		Money money = (Money) object;
		return new EqualsBuilder().append(this.amount, money.getAmount())
				.append(this.currency, money.currency)
				.isEquals();
	}
}
