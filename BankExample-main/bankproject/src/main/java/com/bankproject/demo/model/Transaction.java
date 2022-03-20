package com.bankproject.demo.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transaction_id")
	private Integer transactionId;

	@Column(name = "transaction_number")
	private String transactionNumber;

	@Column(name = "transaction_amount")
	private double transactionedAmount;

	@Column(name = "transaction_type")
	private String transactionType;

	@Column(name = "available_balance")
	private Double availableBalance;

	@Column(name = "transaction_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "account_Id")
	@JsonIgnoreProperties("transaction")
	private Account account;

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public double getTransactionedAmount() {
		return transactionedAmount;
	}

	public void setTransactionedAmount(double transactionedAmount) {
		this.transactionedAmount = transactionedAmount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(Double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}