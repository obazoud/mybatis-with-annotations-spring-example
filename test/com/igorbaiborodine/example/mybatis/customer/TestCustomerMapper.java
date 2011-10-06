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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/application-context.xml") 
public class TestCustomerMapper {
	private final Logger _logger = Logger.getLogger(getClass());
	@Resource
	private CustomerMapper _customerMapper;
	
	@Test 
	public void testSelectByPrimaryKey() {
		Short customerId = new Short("1");
		Customer customer = _customerMapper.selectByPrimaryKey(customerId);
		
		assertNotNull("test select by primary key failed - customer must not be null", customer);
		_logger.debug(String.format("retrieved ", customer.toString()));
		assertTrue("test select by primary key failed - customer id must not be different", 
				customerId.equals(customer.getCustomerId()));
	}

	@Test
	public void testInsert() {
		Customer customer = createCustomer();
		
		int count = _customerMapper.insert(customer);
		assertEquals("test insert failed - insert count must be equal to 1", 1, count);
		assertNotNull("test insert failed - address id must not be null", customer.getCustomerId());
		assertTrue("test insert failed - address id must be greater than 0", 
				customer.getCustomerId() > 0);
	}

	@Test
	public void testDeleteByPrimaryKey() {

		Customer customer = createCustomer();
		int count = _customerMapper.insert(customer);
		assertEquals("test delete by primary key - insert count must be equal to 1", 1, count);
		
		count = _customerMapper.deleteByPrimaryKey(customer.getCustomerId());
		assertEquals("test delete by primary key - delete count must be equal to 1", 1, count);
		
		Customer deletedCustomer = _customerMapper.selectByPrimaryKey(customer.getCustomerId());
		assertNull("test delete by primary key - deleted customer must be null", deletedCustomer);
	}

	@Test
	public void testUpdateByPrimaryKey() {
		
		Short customerId = new Short("1");
		Customer customer = _customerMapper.selectByPrimaryKey(customerId);
		assertNotNull("test update by primary key failed - customer must not be null", customer);
		
		boolean isActive = ! customer.getActive();
		customer.setActive(isActive);
		
		int count = _customerMapper.updateByPrimaryKey(customer);
		assertEquals("test update by primary key failed - update count must be equal to 1", 1, count);
		
		customer = _customerMapper.selectByPrimaryKey(customerId);
		assertEquals("test update by primary key failed - active field not updated", 
				isActive, customer.getActive());
	}

	// execute script alter_payment_date.sql 
	// so rewards_report SP returns a result set with two customers
	@Test
	public void testGetRewardsReport() {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("min_monthly_purchases", new Byte("7"));
		params.put("min_dollar_amount_purchased", new Double("20.0"));
		
		List<Customer> customers = _customerMapper.getRewardsReport(params);
		assertNotNull("test get rewards report failed - customers list must not be null", 
				customers);
		assertNotNull("test get rewards report failed - count_rewardees out param must not be null", 
				params.get("count_rewardees")); 
		assertEquals("get customer rewards report failed - count_rewardees out param must be equal to 2",
				2, ((Integer) params.get("count_rewardees")).intValue());
	}

	private Customer createCustomer() {
		int random = (new Random()).nextInt(1000);
		String firstName = "FIRST_NAME_ANT_" + random;
		String lastName = "LAST_NAME_ANT_" + random;
		Byte storeId = new Byte("1");
		Short addressId = new Short("1");
		
		Customer customer = new Customer();
		
		customer.setAddressId(addressId);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(random + "_test.mapper@sakilacustomer.org");
		customer.setStoreId(storeId);
		customer.setActive(true);
		customer.setCreateDate(new Date());
		
		return customer;
	}
}
