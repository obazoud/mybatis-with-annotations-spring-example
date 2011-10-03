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

import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/application-context.xml") 
public class TestAddressMapper {
	@Resource
	private AddressMapper _addressMapper;
	
	@Test
	public void testInsert() {
		Address address = createAddress();
		
		int count = _addressMapper.insert(address);
		assertEquals("test insert failed - count must be equal to 1", 1, count);
		assertNotNull("test insert failed - address id must not be null", address.getAddressId());
		assertTrue("test insert failed - address id must be greater than 0", 
				address.getAddressId() > 0);
	}
	
	private Address createAddress() {
		Address address = new Address();
		
		int random = new Random().nextInt(1000);
		address.setAddress(random + " Test Mapper Ave.");
		address.setCityId((short) 1);
		address.setDistrict("MyBatis-Spring");
		address.setPostalCode("11111");
		address.setPhone("8887777777");
		
		return address;
	}
}
