/*******************************************************************************
 * Copyright 2011 Igor Baiborodine
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.igorbaiborodine.example.mybatis.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.igorbaiborodine.example.mybatis.address.Address;
import com.igorbaiborodine.example.mybatis.exceptions.ServiceException;

public class TestCustomerService {
	private static CustomerServiceImpl customerDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		SqlSessionManager sessionManager = null;
		
		try {
			Reader reader = Resources.getResourceAsReader("mybatisConfig.xml");
			sessionManager = SqlSessionManager.newInstance(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//customerDao = new CustomerDaoImpl(sessionManager);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDao = null;
	}

	@Test
	public void testGetCustomer() throws ServiceException {
		
		// 1, 1, MARY, SMITH, MARY.SMITH@sakilacustomer.org, 5, 0, 2006-02-14 22:04:36, 2011-09-21 18:52:54
		short customerId = 1;
		Customer customer = customerDao.getCustomer(customerId);
		assertNotNull("test get customer failed - customer must not be null", customer);
		assertEquals("test get customer failed - first name must not be different from MARY", 
				"MARY", customer.getFirstName());
		
		customerId = -1;
		customer = customerDao.getCustomer(customerId);
		assertNull("test get customer failed - customer must be null", customer);
	}
	
	@Test
	public void testAddCustomer() throws ServiceException {
		int random = (new Random()).nextInt(1000);
		Customer customer = createCustomer(random);
		Address address = createAddress(random);
		
		short newCustomerId = customerDao.addCustomer(customer, address);
		assertTrue("test add customer failed - new customer id must be greater than 0",
				newCustomerId > 0);
	}
	
	private Address createAddress(int random_) {
		Address address = new Address();
		
		address.setAddress(random_ + " Test Ave.");
		address.setCityId((short) 1);
		address.setDistrict("Vermont");
		address.setPostalCode("30101");
		address.setPhone("8887777" + random_);
		
		return address;
	}

	private Customer createCustomer(int random_) {
		Customer customer = new Customer();
		
		String firstName = "FIRST_NAME_" + random_;
		String lastName = "LAST_NAME_" + random_;
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		
		customer.setEmail(firstName + "." + lastName + "@sakilacustomer.org");
		customer.setStoreId((byte) 2);
		customer.setActive(true);
		customer.setCreateDate(new Date());
		
		return customer;
	}
	
	@Test 
	public void testGetCustomerRewardsReport() throws ServiceException {
		byte minMonthlyPurchases = 7;
		double minDollarAmountPurchased = 20.0;
		
		List<Customer> rewardsReport = customerDao.getCustomerRewardsReport(
				minMonthlyPurchases, minDollarAmountPurchased);
		assertEquals("test get customer rewards report failed - record count must be equal to 2", 
				2, rewardsReport.size());
	}
}
