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

import java.util.Date;

public class Address {

	private Short addressId;
    private String address;
    private String address2;
    private String district;
    private Short cityId;
    private String postalCode;
    private String phone;
    private Date lastUpdate;
    
    public Short getAddressId() {
        return addressId;
    }
    public void setAddressId(Short addressId) {
        this.addressId = addressId;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2 == null ? null : address2.trim();
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }
    public Short getCityId() {
        return cityId;
    }
    public void setCityId(Short cityId) {
        this.cityId = cityId;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", address=" + address
				+ ", address2=" + address2 + ", district=" + district
				+ ", cityId=" + cityId + ", postalCode=" + postalCode
				+ ", phone=" + phone + ", lastUpdate=" + lastUpdate + "]";
	}
}
