package com.kata.katapocapp.business.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.kata.katapocapp.business.service.CartService;
import com.kata.katapocapp.dao.CartDAO;
import com.kata.katapocapp.dao.ProductDAO;
import com.kata.katapocapp.dao.ShopperDAO;
import com.kata.katapocapp.model.Cart;
import com.kata.katapocapp.model.CartProduct;
import com.kata.katapocapp.model.Product;
import com.kata.katapocapp.model.Shopper;

/**
 * Created by wassim on 2018/03/18
 */
public class CartServiceImpl implements CartService {

	// TODO ADD try catch block and logger
	// private final Logger logger =
	// LogManager.getLogger(CartServiceImpl.class);

	/**
	 * Inject Cart DAO
	 */
	@Inject
	CartDAO cartDAO;

	/**
	 * Inject Shopper DAO
	 */
	@Inject
	ShopperDAO shopperDAO;

	/**
	 * Inject Product DAO
	 */
	@Inject
	ProductDAO productDAO;

	@Override
	public Cart addNewProductToCart(UUID shopperUid, String productReference, int quantity) {
		// get shopper
		Shopper shopper = shopperDAO.findByShopperUid(shopperUid);
		if (shopper == null) {
			throw new IllegalStateException("Error : no shopper found by this shopper-uid");
		}

		// get shopper cart 
		Cart cart = cartDAO.findByShopper(shopper);
		if (cart == null) {
			// If shopper cart is null we need to create cart
			cart = new Cart();
			cart.setCartUid(UUID.randomUUID());
			cart.setShopper(shopper);
		}

		// get product to add in cart
		Product product = productDAO.findByProductReference(productReference);
		if (product == null) {
			throw new IllegalStateException("Error : no product found with this product-reference");
		}

		// check If Cart already contains the product
		CartProduct cartProduct = cart.getCartProducts().stream()
				.filter(cartProductIterator -> cartProductIterator.getProduct().deepEquals(product)).findFirst()
				.orElse(null);// .ifPresent();
		if (cartProduct != null) {
			// if product exists in Cart we must update quantity by adding the
			// new quantity then update local amount and Cart global costs
			quantity += cartProduct.getQuantity();

			// to update global costs we must remove product costs to add new
			// quantity costs
			cart.setCosts(cart.getCosts() - cartProduct.getPrice());

			// in order to push new cart-product we need to remove old one,
			// Cascade is for ALL ManyToOne
			cart.getCartProducts().stream()
					.filter(cartProductIterator -> !cartProductIterator.getProduct().deepEquals(product));
		} else {
			// in case new Product to add
			cartProduct = new CartProduct();
			cartProduct.setProduct(product);
			cartProduct.setCart(cart);
		}

		// calculate product cost
		double newProductAmount = quantity * cartProduct.getProduct().getPrice();
		cartProduct.setQuantity(quantity);
		cartProduct.setPrice(newProductAmount);

		// update cart-global cost and products list then set new update date
		cart.setCosts(cart.getCosts() + newProductAmount);
		cart.getCartProducts().add(cartProduct);
		cart.setDateUpdated(Instant.now());

		// update cost
		if (!cartDAO.save(cart)) {
			throw new IllegalStateException("Error occured when trying to update Shopper Cart");
		}

		return cart;
	}

	@Override
	public Cart removeProductFromCart(UUID shopperUid, String productReference) {
		// get shopper
		Shopper shopper = shopperDAO.findByShopperUid(shopperUid);
		if (shopper == null) {
			throw new IllegalStateException("Error : no shopper found by this shopper-uid");
		}

		// get shopper cart 
		Cart cart = cartDAO.findByShopper(shopper);
		if (cart == null) {
			throw new IllegalStateException("Error : Cart already empty");
		}

		// get product to remove from cart
		Product product = productDAO.findByProductReference(productReference);
		if (product == null) {
			throw new IllegalStateException("Error : no product found with this product-reference");
		}

		// check If Cart already contains the product
		List<CartProduct> cartProducts = cart.getCartProducts().stream()
				.filter(cartProductIterator -> !cartProductIterator.getProduct().deepEquals(product))
				.collect(Collectors.toList());

		// update cart products and costs then set new update date
		cart.setCartProductsAndCosts(cartProducts);
		cart.setDateUpdated(Instant.now());

		// update Cart
		if (!cartDAO.save(cart)) {
			throw new IllegalStateException("Error occured when trying to update Shopper Cart");
		}

		return cart;
	}

	@Override
	public Cart getShopperCart(UUID shopperUid) {
		// get shopper
		Shopper shopper = shopperDAO.findByShopperUid(shopperUid);
		if (shopper == null) {
			throw new IllegalStateException("Error : no shopper found by this shopper-uid");
		}

		// get shopper cart 
		Cart cart = cartDAO.findByShopper(shopper);
		if (cart == null) {
			throw new IllegalStateException("Error : Cart already empty");
		}

		return cart;
	}

	@Override
	public List<CartProduct> getShopperCartProducts(UUID shopperUid) {
		// get SGopper
		Shopper shopper = shopperDAO.findByShopperUid(shopperUid);
		if (shopper == null) {
			throw new IllegalStateException("Error : no shopper found by this shopper-uid");
		}

		// get shopper cart 
		Cart cart = cartDAO.findByShopper(shopper);
		if (cart == null) {
			throw new IllegalStateException("Error : Cart already empty");
		}

		return cart.getCartProducts();
	}

}
