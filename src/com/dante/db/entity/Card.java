package com.dante.db.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CARD_ID")
	private int cardId;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "EXPIRATION_RANGE")
	private int expirationRange;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@OneToMany(mappedBy = "card")
	List<CardDetail> cardDetails;

	public Card() {

	}

	public Card(String type, int expirationRange) {
		super();
		this.type = type;
		this.expirationRange = expirationRange;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getExpirationRange() {
		return expirationRange;
	}

	public void setExpirationRange(int expirationRange) {
		this.expirationRange = expirationRange;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public List<CardDetail> getCardDetails() {
		return cardDetails;
	}

	public void setCardDetails(List<CardDetail> cardDetails) {
		this.cardDetails = cardDetails;
	}

}
