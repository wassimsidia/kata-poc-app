package com.kata.katapocapp.business.service;

import java.util.List;

import com.kata.katapocapp.model.Shopper;

/**
 * Created by wassim on 2018/03/18
 */
public interface ShopperService {

	/**
	 * this method aims to retrieve ShopperAccount by email
	 * 
	 * @param email: shopper email
	 * @return {@link Shopper}
	 */
	public Shopper findByEmail(String email);

	/**
	 * save Shopper (Shopper registration) 
	 * 
	 * @param shopper :{@link Shopper} account form to save
	 * @return {@link Shopper}
	 */
	public Shopper save(Shopper shopper);
	
	/**
	 * This method aims to retrieve Shoppers list
	 * @return {@link List<Shopper>}
	 */
	public List<Shopper> getAllShoppers();

}
