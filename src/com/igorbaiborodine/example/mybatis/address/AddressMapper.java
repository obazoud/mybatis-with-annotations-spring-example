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

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;

import com.igorbaiborodine.example.mybatis.address.Address;

public interface AddressMapper {
	String INSERT = 
			"insert into address (address, address2, district, city_id, postal_code, phone, last_update)"
		    + " values (#{address,jdbcType=VARCHAR},"
			+ " #{address2,jdbcType=VARCHAR}, #{district,jdbcType=VARCHAR}, #{cityId,jdbcType=SMALLINT},"
		    + " #{postalCode,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, #{lastUpdate,jdbcType=TIMESTAMP})";
		  
	@Insert(INSERT)
	@Options(useGeneratedKeys = true, keyProperty = "address_id")
	@SelectKey(statement = "SELECT LAST_INSERT_ID();", 
			before = false, 
			keyProperty = "addressId", 
			resultType = short.class)
	int insert(Address record_);
}
