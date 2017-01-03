package com.dante.db.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRODUCT_ID")
	private int productId;

	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	@Column(name = "PRODUCT_QUANTITY")
	private int productQuantity;

	@Column(name = "PRODUCT_COST")
	private Timestamp productCost;
	
	@Column(name = "DATE_CREATED")
	private Timestamp productDateCreated;
	
	@Column(name = "DATE_UPDATED")
	private Date productDateUpdated;
	public Product() {

	}
	
	public Product(int productId, String productName, int productQuantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productQuantity = productQuantity;
	}

	public Product(int productId, String productName, int productQuantity,
			Timestamp productCost, Timestamp productDateCreated,
			Date productDateUpdated) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.productCost = productCost;
		this.productDateCreated = productDateCreated;
		this.productDateUpdated = productDateUpdated;
	}

	public Timestamp getProductCost() {
		return productCost;
	}

	public void setProductCost(Timestamp productCost) {
		this.productCost = productCost;
	}

	public Timestamp getProductDateCreated() {
		return productDateCreated;
	}

	public void setProductDateCreated(Timestamp productDateCreated) {
		this.productDateCreated = productDateCreated;
	}

	public Date getProductDateUpdated() {
		return productDateUpdated;
	}

	public void setProductDateUpdated(Date productDateUpdated) {
		this.productDateUpdated = productDateUpdated;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

}
