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

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.igorbaiborodine.example.mybatis.exceptions.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/application-context.xml") 
public class TestAddressService {
	@Resource
	private AddressServiceImpl _addressService;

	@Test
	public void testAddAddress() throws ServiceException {
		
		Address address = createAddress();
		short addressId = _addressService.addAddress(address);
		
		assertTrue("test add address failed - address id must be greater than 0", addressId > 0);
	}
	
	private Address createAddress() {
		Address address = new Address();
		
		int random = new Random().nextInt(1000);
		address.setAddress(random + " Test Service Ave.");
		address.setCityId((short) 1);
		address.setDistrict("MyBatis-Spring");
		address.setPostalCode("22222");
		address.setPhone("5554444444");
		
		return address;
	}
}
