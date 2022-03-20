package com.bankproject.demo.dto;

import org.springframework.beans.factory.annotation.Value;

import com.bankproject.demo.model.Address;

public interface CustRespProjection {

	@Value("#{target.custName}")
	String getCustName();

	@Value("#{target.address}")
	Address getAddress();

	@Value("#{target.phoneNo}")
	String getPhoneNo();

	@Value("#{target.email}")
	String getEmail();

}
