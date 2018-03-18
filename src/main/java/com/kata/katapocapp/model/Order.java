package com.kata.katapocapp.model;

import java.time.Instant;

/**
 * Created by wassim on 2018/03/18
 */
public class Order {

	/**
	 * Cart ID auto-generated
	 */
	private Integer id;
	
	/**
	 * Order unique reference
	 */
	private String orderReference;

	/**
	 * Order dedicated shopper 
	 */
	private Shopper shopper;
	
	/**
	 * Total order costs
	 */
	private Double amount;
	
	/**
	 * Cart create date
	 */
	private Instant dateCreated = Instant.now();

	/**
	 * Cart Update date : first update date will take create date
	 */
	private Instant dateUpdated = Instant.now();

	/**
	 * Cart expiration date when is not empty
	 */
	private Instant expirationDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

	public Shopper getShopper() {
		return shopper;
	}

	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Instant getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Instant dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Instant getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Instant dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Instant getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Instant expirationDate) {
		this.expirationDate = expirationDate;
	}

}
