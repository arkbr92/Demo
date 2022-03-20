package com.bankproject.demo.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.bankproject.demo.model.Account;
import com.bankproject.demo.model.Address;

public class CustomerDto {

	@NotEmpty(message = "provide customer name")
	@Size(min = 5, max = 50)
	@Pattern(regexp = "^[a-zA-Z0-9_ ]*$")
	private String custName;

	@NotNull(message = "provide Address")
	private Address address;

	@NotEmpty(message = "Provide email id")
	@Email
	private String email;

	@NotNull(message = "provide mobile no ,only digits")
	@Pattern(regexp = "[0-9]{10}", message = "provide valid mobile no")
	private String phoneNo;

	@NotNull(message = "provide account Details")
	@NotEmpty(message = "Provide account details")
	private List<Account> account = new ArrayList<>();

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}

}
