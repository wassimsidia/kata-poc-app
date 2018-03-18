package com.kata.katapocapp.model;

/**
 * Created by wassim on 2018/03/18
 */
public class CartProduct {

	/**
	 * Cart ID auto-generated
	 */
	private Integer id;
	
	/**
	 * Cart 
	 */
	private Cart cart;

	/**
	 * Cart product
	 */
	private Product product;
	
	/**
	 * selected quantity
	 */
	private int quantity;
	
	/**
	 * product costs 
	 */
	private double price;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	

}
