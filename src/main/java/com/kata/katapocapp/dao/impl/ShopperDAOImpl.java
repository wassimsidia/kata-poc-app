package com.kata.katapocapp.dao.impl;

import java.util.List;
import java.util.UUID;

import com.kata.katapocapp.dao.ShopperDAO;
import com.kata.katapocapp.model.Shopper;

/**
 * Created by wassim on 2018/03/18
 * TODO specify DAO access layer 
 * To be continued after specifying ORM and data access layer  
 */
public class ShopperDAOImpl implements ShopperDAO {

	@Override
	public boolean save(Shopper entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Shopper findById(String entityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shopper> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String entityId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Shopper findByShopperUid(UUID shopperUid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shopper findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
