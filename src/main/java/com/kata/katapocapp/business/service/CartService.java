package com.kata.katapocapp.business.service;

import java.util.List;
import java.util.UUID;

import com.kata.katapocapp.model.Cart;
import com.kata.katapocapp.model.CartProduct;
import com.kata.katapocapp.model.Product;

/**
 * Created by wassim on 2018/03/18
 */
public interface CartService {


	/**
	 * This method aims to add product to shopper
	 * 
	 * @param shopperUid
	 *            : the {@link Shopper} unique Id
	 * @param productReference
	 *            : {@link Product} unique reference
	 * @return {@link Cart} shopper cart after update
	 */
	public Cart addNewProductToCart(UUID shopperUid, String productReference, int quantity);

	/**
	 * This method aims to remove Product from shopper Cart
	 * 
	 * @param shopperUid
	 *            : shopper {@link Shopper} number
	 * @return {@link Cart} shopper cart after update
	 */
	public Cart removeProductFromCart(UUID shopperUid, String productReference);

	/**
	 * This method aims to get Shopper - Cart
	 * 
	 * @param shopperUid
	 *            : the {@link Shopper} unique Id
	 * @return {@link Cart} shopper cart
	 */
	public Cart getShopperCart(UUID shopperUid);

	/**
	 * This method aims to retrieve Shopper Cart - Products
	 * 
	 * @param shopperUid
	 *            : the {@link Shopper} unique Id
	 * @return {@link List<CartProduct>} list of cart - products
	 */
	public List<CartProduct> getShopperCartProducts(UUID shopperUid);

}
