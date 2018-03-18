package com.kata.katapocapp.dao;

import com.kata.katapocapp.model.Product;

/**
 * Created by wassim on 2018/03/18
 */
public interface ProductDAO extends CrudService<Product, String> {

	/**
	 * This method aims to find Product by product reference
	 * 
	 * @param productReference
	 *            : product unique reference
	 * @return {@link Product}
	 */
	public Product findByProductReference(String productReference); // throws
																	// Exception,
																	// ApplicationException;

}
