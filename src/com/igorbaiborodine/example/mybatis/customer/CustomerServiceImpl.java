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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionManager;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.igorbaiborodine.example.mybatis.address.Address;
import com.igorbaiborodine.example.mybatis.address.AddressMapper;
import com.igorbaiborodine.example.mybatis.exceptions.ServiceException;

public class CustomerServiceImpl implements CustomerService {
	private final Logger _logger = Logger.getLogger(CustomerServiceImpl.class);
	private CustomerMapper _customerMapper;
	
	public void setCustomerMapper(CustomerMapper customerMapper_) {
		_customerMapper = customerMapper_;
	}
	
	@Override
	public Customer getCustomer(short customerId_) throws ServiceException {
		
		Short customerId = 1;
		Customer customer = _customerMapper.selectByPrimaryKey(customerId );
		
		return customer;
	}

	@Override
	public short addCustomer(Customer customer_, Address address_) throws ServiceException {
		short newCustomerId = -1;
		return newCustomerId;
	}

	@Override
	public List<Customer> getCustomerRewardsReport(byte minMonthlyPurchases_,
			double minDollarAmountPurchased_) throws ServiceException {
		List<Customer> rewardsReport = null;
		return rewardsReport;
	}
}
