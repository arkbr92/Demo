package com.bankproject.demo.dto;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TransactionResponseDto {

	@NotNull(message = "provide transaction amount")
	private double transactionedAmount;

	@NotNull(message = "provide to available bal")
	private double availableBalance;

	@NotNull(message = "provide transaction date")
	private Date transactionDate;

	private String transactionType;

	@NotNull(message = "provide transaction number ")
	private String transactionNumber;

	@NotNull(message = "provide to account id")
	private Integer formAccountId;

	@NotNull(message = "provide to account id")
	private Integer toAccountId;

	@NotNull(message = "provide from Date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fromDate;

	@NotNull(message = "provide to Date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date toDate;

	public double getTransactionedAmount() {
		return transactionedAmount;
	}

	public void setTransactionedAmount(double transactionedAmount) {
		this.transactionedAmount = transactionedAmount;
	}

	public double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public Integer getFormAccountId() {
		return formAccountId;
	}

	public void setFormAccountId(Integer formAccountId) {
		this.formAccountId = formAccountId;
	}

	public Integer getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(Integer toAccountId) {
		this.toAccountId = toAccountId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

}
