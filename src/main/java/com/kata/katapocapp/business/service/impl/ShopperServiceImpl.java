package com.kata.katapocapp.business.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.kata.katapocapp.business.service.ShopperService;
import com.kata.katapocapp.dao.ShopperDAO;
import com.kata.katapocapp.model.Shopper;

/**
 * Created by wassim on 2018/03/18
 */
public class ShopperServiceImpl implements ShopperService {

	// TODO ADD try catch block and logger
	// private final Logger logger =
	// LogManager.getLogger(ShopperServiceImpl.class);

	/**
	 * Inject shopperDAO
	 */
	@Inject
	ShopperDAO shopperDAO;

	@Override
	public Shopper findByEmail(String email) {
		// list of shopper
		List<Shopper> shoppersList = shopperDAO.findAll();

		// All shopper fetch verifications can be performed by ORM before
		// performing the action we just need to respect TDD scenarios
		Shopper shopper = shoppersList.stream().filter(shopperItem -> shopperItem.equals(email)).findFirst()
				.orElse(null);

		// in case no shopper retrieved system will throw exeption
		if (shopper == null)
			throw new IllegalStateException("No Shopper found.");

		return shopper;
	}

	@Override
	public Shopper save(Shopper shopper) {
		// list of shopper
		List<Shopper> shoppersList = shopperDAO.findAll();

		// we need to check if shopper exists by
		// adding this additional instruction respecting our TDD
		List<Shopper> shopperExits = shoppersList.stream()
				.filter(shopperIterator -> shopperIterator.deepEquals(shopper)).collect(Collectors.toList());
		if (!shopperExits.isEmpty())
			throw new IllegalStateException("Shopper with given Email already exists.");

		// continue creating shopper
		shopper.setShopperUid(UUID.randomUUID());
		shopper.setDateCreated(Instant.now());

		// continue by saving it
		// By default we suppose that shopper cascade is for all actions
		if (!shopperDAO.save(shopper))
			throw new IllegalStateException("New Shopper can't be saved.");

		return shopper;
	}

	@Override
	public List<Shopper> getAllShoppers() {
		// get all shoppers
		List<Shopper> shoppers = shopperDAO.findAll();

		if (shoppers == null || shoppers.isEmpty())
			throw new IllegalStateException("No shoppers found.");

		return shoppers;
	}

}
