package com.bankproject.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bankproject.demo.dto.TransactionDatesDto;
import com.bankproject.demo.dto.TransactionDto;
import com.bankproject.demo.dto.TransactionResponseDto;
import com.bankproject.demo.exception.EntryNotFoundException;
import com.bankproject.demo.service.AccountService;
import com.bankproject.demo.service.TransactionService;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

	@Mock
	AccountService accountService;

	@Mock
	TransactionService transcService;
	@InjectMocks
	TransactionController transactionController;

	TransactionDto transactionDto;
	TransactionDatesDto transactionDatesDto;

	@BeforeEach
	public void setUp() {
		transactionDto = new TransactionDto();
		transactionDto.setFormAccountId(2);
		transactionDto.setToAccountId(3);
		transactionDto.setTransactionedAmount(20000);

		transactionDatesDto = new TransactionDatesDto();
		transactionDatesDto.setFromDate(new Date(0));
		transactionDatesDto.setToDate(new Date(0));
	}

	@Test
	void testSaveTransactions() throws EntryNotFoundException {
		List<TransactionResponseDto> transactionResponseDtos = new ArrayList<TransactionResponseDto>();
		TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
		transactionResponseDto.setAvailableBalance(10000);
		transactionResponseDto.setFormAccountId(2);
		transactionResponseDto.setToAccountId(3);
		transactionResponseDto.setTransactionedAmount(20000);
		transactionResponseDto.setTransactionType("credit");
		transactionResponseDto.setTransactionNumber("223344322");
		transactionResponseDtos.add(transactionResponseDto);
		when(transcService.saveTransactions(transactionDto)).thenReturn(transactionResponseDtos);
		ResponseEntity<List<TransactionResponseDto>> saveTransactionsRst = transactionController
				.saveTransactions(transactionDto);
		assertNotNull(saveTransactionsRst);
		verify(transcService, times(1)).saveTransactions(transactionDto);
		assertEquals(HttpStatus.ACCEPTED, saveTransactionsRst.getStatusCode());
		assertEquals(10000, saveTransactionsRst.getBody().get(0).getAvailableBalance());
		assertEquals(2, saveTransactionsRst.getBody().get(0).getFormAccountId());
		assertEquals(3, saveTransactionsRst.getBody().get(0).getToAccountId());
		assertEquals(20000, saveTransactionsRst.getBody().get(0).getTransactionedAmount());
		assertEquals("credit", saveTransactionsRst.getBody().get(0).getTransactionType());
		assertEquals("223344322", saveTransactionsRst.getBody().get(0).getTransactionNumber());
	}

	@Test
	void testGetAllTransactionBetweenDates() throws EntryNotFoundException {
		List<TransactionResponseDto> transactionResponseDtos = new ArrayList<TransactionResponseDto>();
		TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
		transactionResponseDto.setAvailableBalance(10000);
		transactionResponseDto.setFormAccountId(2);
		transactionResponseDto.setToAccountId(3);
		transactionResponseDto.setTransactionedAmount(20000);
		transactionResponseDto.setTransactionType("credit");
		transactionResponseDto.setTransactionNumber("223344322");
		transactionResponseDtos.add(transactionResponseDto);
		when(transcService.getAllTransactionByFromAndToDates(new Date(0), new Date(0)))
				.thenReturn(transactionResponseDtos);
		List<TransactionResponseDto> allTransactionBetweenDatesRst = transactionController
				.getAllTransactionBetweenDates(transactionDatesDto);
		assertNotNull(allTransactionBetweenDatesRst);
		verify(transcService, times(1)).getAllTransactionByFromAndToDates(new Date(0), new Date(0));
		assertEquals(10000, allTransactionBetweenDatesRst.get(0).getAvailableBalance());
		assertEquals(2, allTransactionBetweenDatesRst.get(0).getFormAccountId());
		assertEquals(3, allTransactionBetweenDatesRst.get(0).getToAccountId());
		assertEquals(20000, allTransactionBetweenDatesRst.get(0).getTransactionedAmount());
		assertEquals("credit", allTransactionBetweenDatesRst.get(0).getTransactionType());
		assertEquals("223344322", allTransactionBetweenDatesRst.get(0).getTransactionNumber());
	}

}
