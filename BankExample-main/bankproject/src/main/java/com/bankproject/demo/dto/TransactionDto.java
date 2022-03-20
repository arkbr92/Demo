package com.bankproject.demo.dto;

import javax.validation.constraints.NotNull;

public class TransactionDto {

	@NotNull(message = "provide transaction amount")
	private double transactionedAmount;

	@NotNull(message = "provide to account id")
	private Integer formAccountId;

	@NotNull(message = "provide to account id")
	private Integer toAccountId;

	public double getTransactionedAmount() {
		return transactionedAmount;
	}

	public void setTransactionedAmount(double transactionedAmount) {
		this.transactionedAmount = transactionedAmount;
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

}
