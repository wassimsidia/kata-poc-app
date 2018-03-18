package com.kata.katapocapp.dao;


import java.util.UUID;

import com.kata.katapocapp.model.Shopper;

/**
 * Created by wassim on 2018/03/18
 */
public interface ShopperDAO extends CrudService<Shopper, String> {

	/**
	 * DAO : fetch {@link Shopper} shopper by account number
	 * @param shopperUid : {@link UUID} shopper unique Id
	 * @return : {@link Shopper}
	 */
	public Shopper findByShopperUid(UUID shopperUid); //throws Exception, ApplicationException;
	
	/**
	 * DAO : fetch {@link Shopper} by Email 
	 * @param email : Shopper email address
	 * @return {@link Shopper}
	 */
	public Shopper findByEmail(String email); //throws Exception, ApplicationException;
}
