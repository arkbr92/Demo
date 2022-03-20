package com.bankproject.demo.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.bankproject.demo.model.Transaction;

public class AccountDto {

	@NotNull(message = "provide customer account number")
	private long accountNumber;

	@NotNull(message = "provide balance ")
	private double balance;

	@NotNull(message = "provide customer Id")
	private int custId;

	@NotEmpty(message = "provide account type")
	private String accountType;

	private List<Transaction> Transaction;

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public static Object findById(long accountNumber2) {
		// TODO Auto-generated method stub
		return accountNumber2;
	}

	public List<Transaction> getTransaction() {
		return Transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		Transaction = transaction;
	}

}
