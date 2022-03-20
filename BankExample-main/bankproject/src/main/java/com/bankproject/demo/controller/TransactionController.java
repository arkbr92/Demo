package com.bankproject.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankproject.demo.dto.TransactionDatesDto;
import com.bankproject.demo.dto.TransactionDto;
import com.bankproject.demo.dto.TransactionResponseDto;
import com.bankproject.demo.exception.EntryNotFoundException;
import com.bankproject.demo.service.AccountService;
import com.bankproject.demo.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	AccountService accountService;

	@Autowired
	TransactionService transcService;

	@PostMapping("/transactions/save")
	public ResponseEntity<List<TransactionResponseDto>> saveTransactions(
			@Valid @RequestBody TransactionDto transactionRequestDto) throws EntryNotFoundException {
		List<TransactionResponseDto> saveTransactions = transcService.saveTransactions(transactionRequestDto);
		return new ResponseEntity<List<TransactionResponseDto>>(saveTransactions, HttpStatus.ACCEPTED);
	}

	@PostMapping("/getAllTransactionByAccountIdDates")
	public List<TransactionResponseDto> getAllTransactionBetweenDates(
			@RequestBody TransactionDatesDto transactionDatesDto) {
		return (List<TransactionResponseDto>) transcService
				.getAllTransactionByFromAndToDates(transactionDatesDto.getFromDate(), transactionDatesDto.getToDate());
	}

}
