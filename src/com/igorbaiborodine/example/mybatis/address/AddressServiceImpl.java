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
package com.igorbaiborodine.example.mybatis.address;

import org.apache.log4j.Logger;

import com.igorbaiborodine.example.mybatis.exceptions.ServiceException;

public class AddressServiceImpl implements AddressService {
	@SuppressWarnings("unused")
	private final Logger _logger = Logger.getLogger(getClass());
	private AddressMapper _addressMapper;
	
	public void setAddressMapper(AddressMapper addressMapper_) {
		_addressMapper = addressMapper_;
	}
	
	@Override
	public short addAddress(Address address_) throws ServiceException {
		
		short addressId = -1;
		try {
			int count = _addressMapper.insert(address_);
			assert(count > 0);
			addressId = address_.getAddressId();
		} catch (Throwable t) {
			String msg = String.format("Cannot add %s", address_.toString());
			throw new ServiceException(msg, t);
		} 
		return addressId;
	}
}
