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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.igorbaiborodine.example.mybatis.address.Address;
import com.igorbaiborodine.example.mybatis.address.AddressMapper;
import com.igorbaiborodine.example.mybatis.exceptions.ServiceException;

@Transactional(rollbackForClassName={"ServiceException"})
public class CustomerServiceImpl implements CustomerService {
	private final Logger _logger = Logger.getLogger(getClass());
	private CustomerMapper _customerMapper;
	private AddressMapper _addressMapper;
	
	public void setCustomerMapper(CustomerMapper customerMapper_) {
		_customerMapper = customerMapper_;
	}
	public void setAddressMapper(AddressMapper addressMapper_) {
		_addressMapper = addressMapper_;
	}

	@Override
	public short addCustomer(Customer customer_, Address address_) throws ServiceException {
		
		short newCustomerId = -1;
		try {
			int count = _addressMapper.insert(address_); 
			assert(count == 1);
			assert(Short.valueOf(address_.getAddressId()) > 0);
			
			//double d = 1/0; // to test transaction roll-back 
			
			customer_.setAddressId(address_.getAddressId());
			count = _customerMapper.insert(customer_);
			assert(count == 1);
			assert(Short.valueOf(customer_.getCustomerId()) > 0);
			
			newCustomerId = Short.valueOf(customer_.getCustomerId());
		} catch (Throwable t) {
			String msg = String.format("Cannot add %s with %s", customer_.toString(), address_.toString());
			throw new ServiceException(msg, t);
		} 
		return newCustomerId;
	}

	@Override
	@Transactional(readOnly = true)
	public Customer findCustomer(short customerId_) throws ServiceException {
		
		Customer customer = null;
		try {
			customer = _customerMapper.selectByPrimaryKey(Short.valueOf(customerId_));
		} catch (Throwable t) {
			String msg = String.format("Cannot find customer with id [%d]", customerId_);
			throw new ServiceException(msg, t);
		}
		return customer;
	}

	@Override
	public boolean modifyCustomer(Customer customer_) throws ServiceException {
		
		int count = 0;
		try {
			count = _customerMapper.updateByPrimaryKey(customer_);
		} catch (Throwable t) {
			String msg = String.format("Cannot modify %d", customer_);
			throw new ServiceException(msg, t);
		}
		return (count == 1);
	}
	
	@Override
	public List<Customer> findCustomersToReward(byte minMonthlyPurchases_,
			double minDollarAmountPurchased_) throws ServiceException {
		List<Customer> customersToReward = new ArrayList<Customer>();
		Integer rewardeesCount;
		
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("min_monthly_purchases", new Byte(minMonthlyPurchases_));
			params.put("min_dollar_amount_purchased", new Double(minDollarAmountPurchased_));
			
			customersToReward = _customerMapper.getRewardsReport(params);
			rewardeesCount = (Integer) params.get("count_rewardees");
			_logger.debug(String.format("rewardees count is [%d]", rewardeesCount));
		} catch (Throwable t) {
			String msg = String.format("Cannot retrieve customer to reward list with params [%d,%d]", 
					minMonthlyPurchases_, minDollarAmountPurchased_);
			throw new ServiceException(msg, t);
		} 
		return customersToReward;
	}
}
