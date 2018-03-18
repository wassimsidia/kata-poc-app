package com.kata.katapocapp.tdd.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.kata.katapocapp.business.service.impl.ShopperServiceImpl;
import com.kata.katapocapp.dao.CartDAO;
import com.kata.katapocapp.dao.ShopperDAO;
import com.kata.katapocapp.model.Shopper;

/**
 * Created by wassim on 2018/03/18
 */
public class ShopperServiceTest {

	@InjectMocks
	ShopperServiceImpl shopperService;

	@Mock
	ShopperDAO shopperDAO;

	@Mock
	Shopper shopper;

	@Mock
	CartDAO cartDAO;

	@Mock
	List<Shopper> shoppers;

	@Captor
	private ArgumentCaptor<Shopper> shopperCaptor;

	/**
	 * Before perform Test methods load shopper mock DAO methods
	 * 
	 * @throws Exception
	 */
	@Before
	public void initTest() throws Exception {
		List<Shopper> shoppers = getShoppersMock();

		MockitoAnnotations.initMocks(this);

		// specify DAO methods response by mocked data
		when(shopperDAO.save(any(Shopper.class))).thenReturn(true);
		when(shopperDAO.delete(any(String.class))).thenReturn(true);
		when(shopperDAO.findAll()).thenReturn(shoppers);

	}

	/**
	 * 1 - ADD new Shopper : SUCCESS
	 */
	@Test
	public void addShopperSuccess() {
		// Given shopper will sign up by setting his account details
		Shopper newShopper = getShopperMock();

		// add shopper
		shopper = shopperService.save(newShopper);

		// then assert shopper is added successfully
		assertNotEquals(shopper, null);
		assertEquals(shopper.getEmail(), newShopper.getEmail());

		// check called services and DAO methods
		verify(shopperDAO, times(1)).findAll();
		verify(shopperDAO, times(1)).save(any(Shopper.class));
	}

	/**
	 * 2 - ADD new Shopper : FAILS
	 */
	@Test(expected = IllegalStateException.class)
	public void addShopperExists() {
		// Given Shopper will sign up with existing email
		Shopper newShopper = getShopperMock();
		newShopper.setEmail("shopper1@gmail.com");
		newShopper.setPhoneNumber("0677500437");

		// try to save existing shopper
		shopper = shopperService.save(newShopper);

		// then assert sign up failed result
		assertNull(shopper);

		// check called services and DAO methods
		verify(shopperDAO, Mockito.never()).save(any(Shopper.class));
		verify(shopperDAO, times(1)).findAll();
	}

	/**
	 * 3- Get Shopper List : SUCCESS
	 */
	@Test
	public void getShoppersListSuccess() {
		// check shoppers list
		shoppers = shopperService.getAllShoppers();

		// then assert correct response
		assertThat(shoppers.size(), is(2));

		// check called services and DAO methods
		verify(shopperDAO, times(1)).findAll();
	}

	/**
	 * 4- Get Shopper List : FAILURE
	 */
	@Test(expected = IllegalStateException.class)
	public void getShoppersListFails() {
		when(shopperDAO.findAll()).thenReturn(null);
		// check choppers list fails
		List<Shopper> shoppers = shopperService.getAllShoppers();

		// assert failed response
		assertNull(shoppers);

		// check called services and DAO methods
		verify(shopperDAO, times(1)).findAll();
	}

	/**
	 * Mocking shoppers list to perform test cases
	 * 
	 * @return {@link List<Shopper>} shoppers list
	 */
	public List<Shopper> getShoppersMock() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		Shopper shopper1 = new Shopper(1, UUID.randomUUID(), "first name 1", "last name 1", "shopper1@gmail.com",
				"0677500437", "encryptedPassword", LocalDate.parse("1990-01-01", dtf), "street 1", "FR");
		Shopper shopper2 = new Shopper(2, UUID.randomUUID(), "first name 2", "last name 2", "shopper2@gmail.com",
				"0677500438", "encryptedPassword", LocalDate.parse("1990-01-02", dtf), "street 2", "FR");

		return new ArrayList<Shopper>(Arrays.asList(shopper1, shopper2));
	}

	/**
	 * Mock shopper
	 * 
	 * @return {@link Shopper}
	 */
	public Shopper getShopperMock() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return new Shopper(1, UUID.randomUUID(), "first name 3", "last name 3", "shopper3@gmail.com", "0677500436",
				"encryptedPassword", LocalDate.parse("1990-01-01", dtf), "street 3", "FR");
	}

}
