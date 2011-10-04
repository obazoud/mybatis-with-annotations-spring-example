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

import java.util.List;

import com.igorbaiborodine.example.mybatis.address.Address;
import com.igorbaiborodine.example.mybatis.exceptions.ServiceException;

public interface CustomerService {

	short addCustomer(Customer customer_, Address address_) throws ServiceException;

	Customer findCustomer(short customerId_) throws ServiceException;
	
	boolean modifyCustomer(Customer customer_) throws ServiceException;
	
	boolean removeCustomer(short customerId) throws ServiceException;
	
	List<Customer> findCustomersToReward(
			byte minMonthlyPurchases_, double minDollarAmountPurchased_) throws ServiceException;
	
}
