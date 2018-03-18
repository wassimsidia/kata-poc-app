package com.kata.katapocapp.model;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by wassim on 2018/03/18
 */
public class Cart {

	/**
	 * Cart ID auto-generated
	 */
	private Integer id;
	
	/**
	 * Cart unique reference
	 */
	private UUID cartUid;

	/**
	 * Cart dedicated shopper 
	 */
	private Shopper shopper;
	
	/**
	 * Cart costs : if cart is e
	 */
	private Double costs = 0.0 ;
	
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
	
	/**
	 * Shopper cart products list : we suppose that cart must be empty
	 */
	private List<CartProduct> cartProducts = new ArrayList<>();


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UUID getCartUid() {
		return cartUid;
	}

	public void setCartUid(UUID cartReference) {
		this.cartUid = cartReference;
	}

	public Shopper getShopper() {
		return shopper;
	}

	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
	}

	public Double getCosts() {
		return costs;
	}
	
	public void setCosts(Double costs) {
		this.costs = costs;
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
	
	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}
	
	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
	
	public void setCartProductsAndCosts(List<CartProduct> cartProducts) {
		this.costs = 0.0;
		cartProducts.forEach(cartProduct -> {
			this.costs += cartProduct.getPrice();
		});
		this.cartProducts = cartProducts;
	}
	
	/**
	 * Override to String method
	 */
	public String toString() {

		Field[] fields = Cart.class.getFields();
		StringBuffer sb = new StringBuffer();

		for (Field field : fields) {
			try {
				if (field.get(this) != null) {
					sb.append(field.getName() + " = " + field.get(this) + "\r\n");
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * Override equals method
	 */
	public boolean equals(Object anotherObject) {
		if (!(anotherObject instanceof Cart)) {
			return false;
		}

		Cart anotherShopper = (Cart) anotherObject;

		return Objects.equals(cartUid, anotherShopper.getCartUid());
	}

	/**
	 * Deep equals method to compare Cart
	 * @param anotherObject
	 * @return
	 */
	public boolean deepEquals(Object anotherObject) {
		if (!(anotherObject instanceof Cart)) {
			return false;
		}

		Cart anotherCart = (Cart) anotherObject;
		// we compare Cart unique attributes
		return Objects.equals(id, anotherCart.getId())
				&& Objects.equals(cartUid, anotherCart.getCartUid())
				&& Objects.equals(shopper, anotherCart.getShopper());

	}

}
