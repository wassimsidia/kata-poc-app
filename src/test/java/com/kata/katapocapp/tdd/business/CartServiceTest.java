package com.kata.katapocapp.tdd.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kata.katapocapp.business.service.impl.CartServiceImpl;
import com.kata.katapocapp.business.service.impl.ShopperServiceImpl;
import com.kata.katapocapp.common.ProductType;
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
public class CartServiceTest {

	@InjectMocks
	CartServiceImpl cartService;

	@InjectMocks
	ShopperServiceImpl shopperService;

	@Mock
	CartDAO cartDAO;

	@Mock
	ShopperDAO shopperDAO;

	@Mock
	ProductDAO productDAO;

	@Mock
	Cart cart;

	@Captor
	private ArgumentCaptor<Cart> cartCaptor;

	// @BeforeClass
	// public static void setUp(){
	// cart = new Cart();
	// cart.setId(1);
	// }
	/**
	 * Before perform Test methods load mock DAO methods
	 * 
	 * @throws Exception
	 */
	@Before
	public void initTest() throws Exception {
		Shopper shopper = getShopperMock();

		Cart shopperCart = this.getCartMock();

		Product product = this.getProductMock();

		MockitoAnnotations.initMocks(this);

		// specify DAO methods response by mocked data
		when(cartDAO.save(any(Cart.class))).thenReturn(true);
		when(cartDAO.findByShopper(any(Shopper.class))).thenReturn(shopperCart);
		when(shopperDAO.findByShopperUid(UUID.fromString("eb066e42-95dc-40d2-a0c9-425fda5fae1e"))).thenReturn(shopper);
		when(productDAO.findByProductReference(any(String.class))).thenReturn(product);
	}

	/**
	 * 1 - Add new Product to Shopper - Cart : SUCCESS
	 */
	@Test
	public void addProductToShopperCartSuccess() {
		// given Shopper with Unique Id will add product to his cart
		Product productToBuy = getProductMock();
		UUID shopperUid = UUID.fromString("eb066e42-95dc-40d2-a0c9-425fda5fae1e");

		// Add product to cart
		Cart shopperCart = cartService.addNewProductToCart(shopperUid, productToBuy.getProductReference(), 2);

		// then we assert new shopper Cart details
		Double mustCosts = 7200.00;
		assertEquals(mustCosts, shopperCart.getCosts());
		assertEquals(shopperCart.getCartProducts().size(), 3);
		
		// check called services and DAO methods
		verify(cartDAO, times(1)).save(any(Cart.class));
		verify(shopperDAO, times(1)).findByShopperUid(any(UUID.class));
		verify(productDAO, times(1)).findByProductReference(any(String.class));
		verify(cartDAO, times(1)).findByShopper(any(Shopper.class));
	}

	/**
	 * 2 - Add new Product to Shopper - Cart : FAILS
	 */
	@Test(expected = IllegalStateException.class)
	public void addProductToShopperCartFails() {
		// given Shopper with incorrect Unique Id will add product to his cart
		// with incorrect product ID
		Product productToBuy = getProductMock();
		UUID shopperUid = UUID.fromString("eb066e42-95dc-40d2-a0c9-425fda5fae3e");

		// failed Add product to cart
		Cart shopperCart = cartService.addNewProductToCart(shopperUid, productToBuy.getProductReference(), 2);

		// Then we assert failed service response
		assertNull(shopperCart);
		
		// check called services and DAO methods
		verify(cartDAO, times(1)).findByShopper(any(Shopper.class));
		verify(shopperDAO, times(1)).findByShopperUid(any(UUID.class));
	}

	/**
	 * 3 - Remove product from shopper - cart : SUCCESS
	 */
	@Test
	public void removeProductFromShopperCartSuccess() {
		// given Shopper with Unique Id will remove product from his cart
		when(productDAO.findByProductReference("21221751")).thenReturn(getProduct2Mock());
		String productReference = "21221751";
		UUID shopperUid = UUID.fromString("eb066e42-95dc-40d2-a0c9-425fda5fae1e");

		// Remove product from cart
		Cart shopperCart = cartService.removeProductFromCart(shopperUid, productReference);

		// then we assert new shopper Cart details
		Double mustCosts = 2000.00;
		assertEquals(mustCosts, shopperCart.getCosts());
		assertEquals(shopperCart.getCartProducts().size(), 1);
		
		// check called services and DAO methods
		verify(cartDAO, times(1)).save(any(Cart.class));
		verify(cartDAO, times(1)).findByShopper(any(Shopper.class));
		verify(shopperDAO, times(1)).findByShopperUid(any(UUID.class));
		verify(productDAO, times(1)).findByProductReference(any(String.class));
	}

	/**
	 * 4 - Remove product from shopper - cart : FAILS
	 */
	@Test(expected = IllegalStateException.class)
	public void removeProductFromShopperCartFails() {
		// given Shopper with Unique Id will remove product from his cart with
		// fake product ID
		when(productDAO.findByProductReference("21221759")).thenReturn(null);
		String productReference = "21221759";
		UUID shopperUid = UUID.fromString("eb066e42-95dc-40d2-a0c9-425fda5fae1e");

		// try to remove product from cart
		Cart shopperCart = cartService.removeProductFromCart(shopperUid, productReference);

		// then assert failed response
		assertNull(shopperCart);
		
		// check called services and DAO methods
		verify(cartDAO, times(1)).findByShopper(any(Shopper.class));
		verify(shopperDAO, times(1)).findByShopperUid(any(UUID.class));
	}

	/**
	 * 5 - Get shopper cart - products details: SUCCESS
	 */
	@Test
	public void getShopperCartProductsSuccess() {
		// given Shopper with Unique Id will check his cart - products
		UUID shopperUid = UUID.fromString("eb066e42-95dc-40d2-a0c9-425fda5fae1e");

		// get cart - product list
		List<CartProduct> shopperProducts = cartService.getShopperCartProducts(shopperUid);

		// assert correct shopper cart - products list
		assertEquals(shopperProducts.size(), 2);
		
		// check called services and DAO methods
		verify(cartDAO, times(1)).findByShopper(any(Shopper.class));
		verify(shopperDAO, times(1)).findByShopperUid(any(UUID.class));
	}

	/**
	 * 6 - Get shopper cart - products details: FAILS
	 */
	@Test(expected = IllegalStateException.class)
	public void getShopperCartProductsFails() {
		// given Shopper with fake Unique Id will check his cart - products
		UUID shopperUid = UUID.fromString("eb066e42-95dc-40d2-a0c9-425fda5fae18");

		// get product list with failed value
		List<CartProduct> shopperProducts = cartService.getShopperCartProducts(shopperUid);

		// then assert failed result
		assertNull(shopperProducts);
		
		// check called services and DAO methods
		verify(cartDAO, times(1)).findByShopper(any(Shopper.class));
		verify(shopperDAO, times(1)).findByShopperUid(any(UUID.class));
	}

	/**
	 * 7 - Get shopper cart - total costs : SUCCESS
	 */
	@Test
	public void getShopperCartCostsSuccess() {
		// given shopper with unique Id need to check his cart costs
		UUID shopperUid = UUID.fromString("eb066e42-95dc-40d2-a0c9-425fda5fae1e");

		// get Cart details
		Cart shopperCart = cartService.getShopperCart(shopperUid);

		// then assert cart - costs correct values
		Double cartMustCost = 6000.00;
		assertEquals(shopperCart.getCosts(), cartMustCost);
		
		// check called services and DAO methods
		verify(cartDAO, times(1)).findByShopper(any(Shopper.class));
		verify(shopperDAO, times(1)).findByShopperUid(any(UUID.class));
	}

	/**
	 * 8 - Get shopper cart - total costs : FAILS
	 */
	@Test(expected = IllegalStateException.class)
	public void getShopperCartCostsFails() {
		// given shopper with fake unique Id need to check his cart costs
		UUID shopperUid = UUID.fromString("eb066e42-95dc-40d2-a0c9-425fda5fae7e");

		// get cart failed details
		Cart shopperCart = cartService.getShopperCart(shopperUid);

		// asset failed response
		assertNull(shopperCart);
		
		// check called services and DAO methods
		verify(cartDAO, times(1)).findByShopper(any(Shopper.class));
		verify(shopperDAO, times(1)).findByShopperUid(any(UUID.class));
	}

	/**
	 * Get Shopper Mocket Cart
	 * 
	 * @return {@link Cart}
	 */
	public Cart getCartMock() {
		Cart cart = new Cart();
		cart.setShopper(getShopperMock());

		getProductsMock().forEach(productItr -> {
			CartProduct cartProduct = new CartProduct();
			cartProduct.setProduct(productItr);
			cartProduct.setQuantity(2);
			cartProduct.setPrice(productItr.getPrice() * 2);
			cart.getCartProducts().add(cartProduct);
			cart.setCosts(cart.getCosts() + cartProduct.getPrice());
		});

		return cart;
	}

	/**
	 * Set and get Mocked products
	 * 
	 * @return {@linkList<Product>}
	 */
	public List<Product> getProductsMock() {
		Product product1 = new Product(1, "21221751", "MAC", 2000.00, ProductType.HIGHT_TECH.getType(), Instant.now());
		Product product2 = new Product(2, "21221752", "iPhone", 1000.00, ProductType.PHONE.getType(), Instant.now());

		return new ArrayList<Product>(Arrays.asList(product1, product2));
	}

	/**
	 * Get Mocked product
	 * 
	 * @return {@link Product}
	 */
	public Product getProductMock() {
		return new Product(3, "21221750", "iPade", 600.00, ProductType.HIGHT_TECH.getType(), Instant.now());
	}

	/**
	 * Get Mocked product
	 * 
	 * @return {@link Product}
	 */
	public Product getProduct2Mock() {
		return new Product(1, "21221751", "MAC", 2000.00, ProductType.HIGHT_TECH.getType(), Instant.now());
	}

	/**
	 * Mock shopper
	 * 
	 * @return {@link Shopper}
	 */
	public Shopper getShopperMock() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return new Shopper(1, UUID.fromString("eb066e42-95dc-40d2-a0c9-425fda5fae1e"), "first name 3", "last name 3",
				"shopper3@gmail.com", "0677500436", "encryptedPassword", LocalDate.parse("1990-01-01", dtf), "street 3",
				"FR");
	}

}
