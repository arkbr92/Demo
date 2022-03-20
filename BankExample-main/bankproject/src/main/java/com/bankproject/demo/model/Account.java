package com.bankproject.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "account_tbl")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id")
	private Integer accountId;

	@Column(name = "account_number")
	private Long accountNumber;

	@Column(name = "account_balance")
	private double balance;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_Id")
	@JsonIgnoreProperties("account")
	private Customer customer;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "account", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Transaction> transaction = new ArrayList<Transaction>();

	@Column(name = "account_type")
	private String accountType;

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

	/*
	 * public List<Customer> getCustomer() { return customer; }
	 * 
	 * public void setCustomer(List<Customer> customer) { this.customer = customer;
	 * }
	 */

	public String getAccountType() {
		return accountType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

}
