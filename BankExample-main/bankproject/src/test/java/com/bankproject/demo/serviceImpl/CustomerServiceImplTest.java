package com.bankproject.demo.serviceImpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bankproject.demo.dao.CustomerDao;
import com.bankproject.demo.dto.CustRespProjection;
import com.bankproject.demo.dto.CustomerDto;
import com.bankproject.demo.dto.CustomerResponseDto;
import com.bankproject.demo.model.Address;
import com.bankproject.demo.model.Customer;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

	@Mock
	private CustomerDao customerDao;

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;

	private CustomerDto customerDto;
	private Customer customer;

	@BeforeEach
	public void setUp() {
		customerDto = new CustomerDto();
		customerDto.setCustName("Rama");
		customerDto.setEmail("rama@gmail.com");
		customerDto.setPhoneNo("23453252");
		customer = new Customer();
		customer.setCustId(1);
		customer.setCustName("siva");
		customer.setEmail("email.d@gmail.com");
		customer.setPhoneNo("123456788");
		Address address = new Address();
		address.setCity("Bengalore");
		address.setState("Karnakata");
		customer.setAddress(address);
	}

	@Test
	void testAddCustomer() {
		when(customerDao.save(Mockito.any(Customer.class))).thenReturn(customer);
		Customer addCustomer = customerServiceImpl.addCustomer(customerDto);
		verify(customerDao, times(1)).save(Mockito.any(Customer.class));
		assertEquals("siva", addCustomer.getCustName());
		assertEquals("email.d@gmail.com", addCustomer.getEmail());
		assertEquals("123456788", addCustomer.getPhoneNo());
		assertEquals(1, addCustomer.getCustId());
	}

	@Test
	void testGetCustomerByName() {
		List<CustRespProjection> custRespProjections = new ArrayList<>();
		when(customerDao.findByCustNameLike(Mockito.anyString())).thenReturn(custRespProjections);
		customerServiceImpl.getCustomerByName(Mockito.anyString());
		verify(customerDao, times(1)).findByCustNameLike(Mockito.anyString());
	}

	@Test
	void testGetCustomerDataById() {
		Customer customer = new Customer();
		customer.setCustName("siva");
		customer.setEmail("rama@gmail.com");
		customer.setPhoneNo("432123344");
		Optional<Customer> customerOption = Optional.of(customer);
		when(customerDao.findById(Mockito.anyInt())).thenReturn(customerOption);
		CustomerResponseDto customerDataById = customerServiceImpl.getCustomerDataById(Mockito.anyInt());
		verify(customerDao, times(1)).findById(Mockito.anyInt());
		assertNotNull(customerDataById);
		assertEquals("siva", customerDataById.getCustName());
		assertEquals("rama@gmail.com", customerDataById.getEmail());
		assertEquals("432123344", customerDataById.getPhoneNo());
	}

}
