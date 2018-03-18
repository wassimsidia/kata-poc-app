package com.kata.katapocapp.model;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import com.kata.katapocapp.validation.DOB;

/**
 * Created by wassim on 2018/03/18
 */
public class Shopper {

	/**
	 * Shopper ID auto-generated
	 */
	private Integer id;

	/**
	 * Shopper unique ID
	 */
	private UUID shopperUid;

	/**
	 * Shopper First name
	 */
	private String firstName;

	/**
	 * Shopper last name
	 */
	private String lastName;

	/**
	 * Shopper email address
	 */
	private String email;

	/**
	 * Shopper password
	 */
	private String password;

	/**
	 * Shopper phone number
	 */
	private String phoneNumber;

	/**
	 * Shopper birth date
	 */
	@DOB(minimumAge = 2, maximumAge = 120, message = "Min Max birth date must be respected")
	private LocalDate birthDate;

	/**
	 * Shopper address
	 */
	private String address;

	/**
	 * Shopper country
	 */
	private String country;

	/**
	 * Cart create date
	 */
	private Instant dateCreated = Instant.now();

	/**
	 * Cart Update date : first update date will take create date
	 */
	private Instant dateUpdated = Instant.now();
	
	/**
	 * Constructor with fields
	 * @param id
	 * @param customerUid
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 * @param password
	 * @param birthDate
	 * @param address
	 * @param country
	 */
	public Shopper(Integer id, UUID customerUid, String firstName, String lastName, String email,
			String phoneNumber, String password, LocalDate birthDate, String address, String country) {
		super();
		this.id = id;
		this.shopperUid = customerUid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.birthDate = birthDate;
		this.address = address;
		this.country = country;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UUID getShopperUid() {
		return shopperUid;
	}

	public void setShopperUid(UUID shopperUid) {
		this.shopperUid = shopperUid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	/**
	 * Override to String method
	 */
	public String toString() {

		Field[] fields = Shopper.class.getFields();
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
		if (!(anotherObject instanceof Shopper)) {
			return false;
		}

		Shopper anotherShopper = (Shopper) anotherObject;

		return Objects.equals(shopperUid, anotherShopper.getShopperUid());
	}

	/**
	 * Deep equals method to compare Customers
	 * 
	 * @param anotherObject
	 * @return
	 */
	public boolean deepEquals(Object anotherObject) {
		if (!(anotherObject instanceof Shopper)) {
			return false;
		}

		Shopper anotherCustomer = (Shopper) anotherObject;
		// we compare shopper unique attributes
		return //Objects.equals(id, anotherCustomer.getId())
				//&& Objects.equals(shopperUid, anotherCustomer.getShopperUid())
				 Objects.equals(email, anotherCustomer.getEmail())
				&& Objects.equals(phoneNumber, anotherCustomer.getPhoneNumber());

	}

}
