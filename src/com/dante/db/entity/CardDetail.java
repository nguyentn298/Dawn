package com.dante.db.entity;

import java.sql.Timestamp;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "card_detail")
public class CardDetail {

	@Access(AccessType.PROPERTY)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CARD_DETAIL_ID")
	private int cardDetailId;

	// @ManyToOne(fetch = FetchType.LAZY) to load with object has @Access()
	// @Access(AccessType.PROPERTY)
	// ========================================
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CARD_ID")
	private Card card;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENT_ID")
	private Client client;
	

	@Column(name = "NUMBER")
	private int number;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	public CardDetail() {

	}

	public CardDetail(Card card, Client client, int number) {
		super();
		this.card = card;
		this.client = client;
		this.number = number;
	}

	public int getCardDetailId() {
		return cardDetailId;
	}

	public void setCardDetailId(int cardDetailId) {
		this.cardDetailId = cardDetailId;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

}
