package com.bankproject.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.bankproject.demo.model.Customer;
import com.bankproject.demo.model.Transaction;

public class AccountResponseDto {

	private Integer accountId;

	private Long accountNumber;

	private double balance;

	private Customer customer;

	private List<Transaction> Transaction = new ArrayList<Transaction>();

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
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
		return Transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		Transaction = transaction;
	}

}
