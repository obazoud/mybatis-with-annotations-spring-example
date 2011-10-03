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

import java.util.Date;

public class Customer {
	
    private Short customerId;
    private Byte storeId;
    private String firstName;
    private String lastName;
    private String email;
    private Short addressId;
    private Boolean active;
    private Date createDate;
    private Date lastUpdate;
    
    public Short getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Short customerId) {
        this.customerId = customerId;
    }
    public Byte getStoreId() {
        return storeId;
    }
    public void setStoreId(Byte storeId) {
        this.storeId = storeId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
    public Short getAddressId() {
        return addressId;
    }
    public void setAddressId(Short addressId) {
        this.addressId = addressId;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", storeId=" + storeId
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", addressId=" + addressId + ", active="
				+ active + ", createDate=" + createDate + ", lastUpdate="
				+ lastUpdate + "]";
	}
}
