package com.bankproject.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bankproject.demo.dto.CustRespProjection;
import com.bankproject.demo.dto.CustomerDto;
import com.bankproject.demo.dto.CustomerResponseDto;
import com.bankproject.demo.model.Customer;
import com.bankproject.demo.service.CustomerService;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerController customerController;
	CustomerDto customerDto;
	Customer customer;

	@BeforeEach
	public void setUp() {
		customerDto = new CustomerDto();
		customerDto.setPhoneNo("232333444");
		customerDto.setCustName("saving");
		customerDto.setEmail("hima@gmail.com");

		customer = new Customer();
		customer.setPhoneNo("232333444");
		customer.setCustName("saving");
		customer.setEmail("hima@gmail.com");
	}

	@Test
	void testAddCustomer() {
		when(customerService.addCustomer(customerDto)).thenReturn(customer);
		ResponseEntity<String> customerResult = customerController.addCustomer(customerDto);
		assertNotNull(customerResult);
		assertEquals("Customer Details Added successfully", customerResult.getBody());
		assertEquals(HttpStatus.ACCEPTED, customerResult.getStatusCode());
	}

	@Test
	void testGetCustomerByName() {
		List<CustRespProjection> customerResult = new ArrayList<CustRespProjection>();
		when(customerService.getCustomerByName(Mockito.anyString())).thenReturn(customerResult);
		List<CustRespProjection> customerByNameResult = customerController.getCustomerByName(Mockito.anyString());
		assertNotNull(customerByNameResult);
		verify(customerService, times(1)).getCustomerByName(Mockito.anyString());
	}

	@Test
	void testGetAllCustomerData() {
		List<CustomerResponseDto> allCustomerData = new ArrayList<CustomerResponseDto>();
		CustomerResponseDto customerResponseDto = new CustomerResponseDto();
		customerResponseDto.setPhoneNo("232333444");
		customerResponseDto.setCustName("saving");
		customerResponseDto.setEmail("hima@gmail.com");
		CustomerResponseDto customerResponseDto2 = new CustomerResponseDto();
		customerResponseDto2.setPhoneNo("5556677");
		customerResponseDto2.setCustName("current");
		customerResponseDto2.setEmail("hima2@gmail.com");
		allCustomerData.add(customerResponseDto);
		allCustomerData.add(customerResponseDto2);
		when(customerService.getAllCustomerData()).thenReturn(allCustomerData);
		List<CustomerResponseDto> allCustomerDataResult = customerController.getAllCustomerData();
		assertNotNull(allCustomerDataResult);
		verify(customerService, times(1)).getAllCustomerData();
		assertEquals("232333444", allCustomerDataResult.get(0).getPhoneNo());
		assertEquals("saving", allCustomerDataResult.get(0).getCustName());
		assertEquals("hima@gmail.com", allCustomerDataResult.get(0).getEmail());
		assertEquals("5556677", allCustomerDataResult.get(1).getPhoneNo());
		assertEquals("current", allCustomerDataResult.get(1).getCustName());
		assertEquals("hima2@gmail.com", allCustomerDataResult.get(1).getEmail());
	}

	@Test
	void testGetCustomerDataById() {
		CustomerResponseDto customerResponseDto = new CustomerResponseDto();
		customerResponseDto.setPhoneNo("232333444");
		customerResponseDto.setCustName("saving");
		customerResponseDto.setEmail("hima@gmail.com");
		when(customerService.getCustomerDataById(Mockito.anyInt())).thenReturn(customerResponseDto);
		CustomerResponseDto customerDataByIdResult = customerController.getCustomerDataById(Mockito.anyInt());
		assertNotNull(customerDataByIdResult);
		verify(customerService, times(1)).getCustomerDataById(Mockito.anyInt());
		assertEquals("232333444", customerDataByIdResult.getPhoneNo());
		assertEquals("saving", customerDataByIdResult.getCustName());
		assertEquals("hima@gmail.com", customerDataByIdResult.getEmail());
	}

}
