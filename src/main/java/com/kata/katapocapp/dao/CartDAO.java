package com.kata.katapocapp.dao;


import com.kata.katapocapp.model.Cart;
import com.kata.katapocapp.model.Shopper;

/**
 * Created by wassim on 2018/03/16
 */
public interface CartDAO extends CrudService<Cart, String> {

	/**
	 * This method aims to find Cart by shopper number
	 * @param shopper : {@link Shopper} cat - shopper
	 * @return {@link Cart}
	 */
	public Cart findByShopper(Shopper shopper); //  throws Exception, ApplicationException;	
	
	/**
	 * This method aims to find Shopper Cart by cart-reference 
	 * @param shopper : cartUid
	 * @return {@link Cart}
	 */
	public Cart findByCartUid(String cartUid); //  throws Exception, ApplicationException;	
	
}
