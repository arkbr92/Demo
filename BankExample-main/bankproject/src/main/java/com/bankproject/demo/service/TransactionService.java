package com.bankproject.demo.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.bankproject.demo.dto.TransactionDto;
import com.bankproject.demo.dto.TransactionResponseDto;
import com.bankproject.demo.exception.EntryNotFoundException;

public interface TransactionService {

	List<TransactionResponseDto> saveTransactions(@Valid TransactionDto transactionRequestDto)
			throws EntryNotFoundException;

	List<TransactionResponseDto> getAllTransactionByFromAndToDates(Date from, Date to);

}
