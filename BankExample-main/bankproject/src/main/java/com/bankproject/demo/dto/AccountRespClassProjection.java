package com.bankproject.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.bankproject.demo.model.Customer;
import com.bankproject.demo.model.Transaction;

public class AccountRespClassProjection {

	private Long accountNumber;

	private double balance;

	private Customer customer;

	private List<Transaction> transaction = new ArrayList<Transaction>();

	private String accountType;

	public AccountRespClassProjection(Long accountNumber, double balance, Customer customer, String accountType) {
		// super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.customer = customer;
		this.accountType = accountType;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
