package com.kata.katapocapp.model;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Objects;

/**
 * Created by wassim on 2018/03/18
 */
public class Product {

	
	/**
	 * Product ID auto-generated
	 */
	private Integer id;
	
	/**
	 * Product unique reference
	 */
	private String productReference;
	
	/**
	 * Product name
	 */
	public String productName;
	
	/**
	 * Cart costs
	 */
	private double price;
	
	/**
	 * Product type which can be HIGT_TECH or Phone
	 */
	private String productType;
	
	/**
	 * Cart create date
	 */
	private Instant dateCreated = Instant.now();

	/**
	 * Cart Update date : first update date will take create date
	 */
	private Instant dateUpdated = Instant.now();


	/**
	 * Product fields constructor  
	 * @param id
	 * @param productReference
	 * @param price
	 * @param dateCreated
	 * @param dateUpdated
	 */
	public Product(Integer id, String productReference, String productName, double price, String productType, Instant dateCreated) {
		super();
		this.id = id;
		this.productReference = productReference;
		this.productName = productName;
		this.price = price;
		this.productType = productType;
		this.dateCreated = dateCreated;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductReference() {
		return productReference;
	}

	public void setProductReference(String productReference) {
		this.productReference = productReference;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
	
	public String getProductType() {
		return productType;
	}
	
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * Override to String method
	 */
	public String toString() {

		Field[] fields = Product.class.getFields();
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
		if (!(anotherObject instanceof Product)) {
			return false;
		}

		Product anotherShopper = (Product) anotherObject;

		return Objects.equals(productReference, anotherShopper.getProductReference());
	}

	/**
	 * Deep equals method to compare Products
	 * @param anotherObject
	 * @return
	 */
	public boolean deepEquals(Object anotherObject) {
		if (!(anotherObject instanceof Product)) {
			return false;
		}

		Product anotherProduct = (Product) anotherObject;
		// we compare Product unique attributes with name
		return  Objects.equals(productReference, anotherProduct.getProductReference())
				&& Objects.equals(productName, anotherProduct.getProductName())
				&& Objects.equals(productType, anotherProduct.getProductType());

	}

}
