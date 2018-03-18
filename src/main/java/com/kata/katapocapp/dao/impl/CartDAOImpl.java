package com.kata.katapocapp.dao.impl;

import java.util.List;

import com.kata.katapocapp.dao.CartDAO;
import com.kata.katapocapp.model.Cart;
import com.kata.katapocapp.model.Shopper;

/**
 * Cart DAO services Implementation
 * Created by wassim on 2018/03/18
 * To be continued after specifying ORM and data access layer  
 */
public class CartDAOImpl implements CartDAO {

	@Override
	public boolean save(Cart entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cart findById(String entityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cart> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String entityId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cart findByShopper(Shopper shopper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart findByCartUid(String cartUid) {
		// TODO Auto-generated method stub
		return null;
	}



}
