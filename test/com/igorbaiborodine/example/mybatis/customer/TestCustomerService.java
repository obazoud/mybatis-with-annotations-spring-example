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

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.igorbaiborodine.example.mybatis.address.Address;
import com.igorbaiborodine.example.mybatis.exceptions.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/application-context.xml") 
public class TestCustomerService {
	@Resource
	private CustomerService _customerService;

	@Test
	public void testAddCustomer() throws ServiceException {

		int random = (new Random()).nextInt(1000);
		Customer customer = createCustomer(random);
		Address address = createAddress(random);
		
		short newCustomerId = _customerService.addCustomer(customer, address);
		assertTrue("test add customer failed - new customer id must be greater than 0",
				newCustomerId > 0);
	}

	@Test
	public void testFindCustomer() throws ServiceException {
		
		// 1, 1, MARY, SMITH, MARY.SMITH@sakilacustomer.org, 5, 0, 2006-02-14 22:04:36, 2011-09-21 18:52:54
		Customer customer = _customerService.findCustomer(new Short("1"));
		assertNotNull("test find customer failed - customer must not be null", customer);
		assertEquals("test find customer failed - first name must not be different from MARY", 
				"MARY", customer.getFirstName());
		assertEquals("test find customer failed - first name must not be different from SMITH", 
				"SMITH", customer.getLastName());
		
		customer = _customerService.findCustomer(new Short("-1"));
		assertNull("test find customer failed - customer must be null", customer);
	}
	
		
	@Test
	public void testModifyCustomer() throws ServiceException {
		
		short customerId = (short) 1;
		Customer customer = _customerService.findCustomer(customerId);
		assertNotNull("test modify customer failed - customer must not be null", customer);
		
		int random = (new Random()).nextInt(1000);
		String email = random + "_test.service@sakilacustomer.org";
		customer.setEmail(email);
		
		boolean isModified = _customerService.modifyCustomer(customer);
		assertTrue("test modify customer failed - modified flag must be equal to true", isModified);
		
		customer = _customerService.findCustomer(customerId);
		assertEquals("test modify customer failed - email was not modified", 
				email, customer.getEmail());
	}
	
	@Test 
	public void testFindCustomersToReward() throws ServiceException {

		byte minMonthlyPurchases = 7;
		double minDollarAmountPurchased = 20.0;
		
		List<Customer> customersToReward = _customerService.findCustomersToReward(
				minMonthlyPurchases, minDollarAmountPurchased);
		assertEquals("test find customers to rewards failed - record count must be equal to 2", 
				2, customersToReward.size());
	}
	
	private Address createAddress(int random_) {
		
		Address address = new Address();
		address.setAddress(random_ + " Test Service Ave.");
		address.setCityId(new Short("1"));
		address.setDistrict("MyBatis-Spring");
		address.setPostalCode("33333");
		address.setPhone("5556666666");
		
		return address;
	}

	private Customer createCustomer(int random_) {
		
		Customer customer = new Customer();
		String firstName = "FIRST_NAME_ANT_" + random_;
		String lastName = "LAST_NAME_ANT_" + random_;

		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(random_ + "_test.service@sakilacustomer.org");
		customer.setStoreId(new Byte("1"));
		customer.setActive(true);
		customer.setCreateDate(new Date());

		return customer;
	}

}
