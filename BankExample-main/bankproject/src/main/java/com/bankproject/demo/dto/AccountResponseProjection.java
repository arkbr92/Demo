package com.bankproject.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "FieldHandler" })
public class AccountResponseProjection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer accountId;

	private Long accountNumber;

	private double balance;

	// private Customer customer;

	public AccountResponseProjection() {
		// super();
	}

	public AccountResponseProjection(Integer accountId, Long accountNumber, double balance) {
		super();
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
