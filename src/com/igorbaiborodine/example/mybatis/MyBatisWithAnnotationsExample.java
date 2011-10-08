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


package com.igorbaiborodine.example.mybatis;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.igorbaiborodine.example.mybatis.customer.Customer;
import com.igorbaiborodine.example.mybatis.customer.CustomerService;
import com.igorbaiborodine.example.mybatis.exceptions.ServiceException;

class MyBatisWithAnnotaionsExample {
	private final static Logger logger = Logger.getLogger(MyBatisWithAnnotaionsExample.class);
	
	public static void main(String[] args) throws ServiceException {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/application-context.xml");
		CustomerService customerService = (CustomerService) ctx.getBean("customerService");
		
		Customer customer = customerService.findCustomer((short)1);
		logger.info("Found " + customer);
	}
}	
