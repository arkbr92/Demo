package com.bankproject.demo.service;

import java.util.List;

import com.bankproject.demo.dto.CustRespProjection;
import com.bankproject.demo.dto.CustomerDto;
import com.bankproject.demo.dto.CustomerResponseDto;
import com.bankproject.demo.model.Customer;

public interface CustomerService {

	Customer addCustomer(CustomerDto customer);

	// List<CustomerResponseDto> getCustomerByName(String custName);
	List<CustRespProjection> getCustomerByName(String custName);

	CustomerResponseDto getCustomerDataById(Integer custId);

	List<CustomerResponseDto> getAllCustomerData();

}
