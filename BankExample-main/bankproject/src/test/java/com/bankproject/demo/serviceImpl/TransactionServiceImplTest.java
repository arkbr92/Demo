package com.bankproject.demo.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bankproject.demo.dao.AccountDao;
import com.bankproject.demo.dao.CustomerDao;
import com.bankproject.demo.dao.TransactionDao;
import com.bankproject.demo.dto.TransactionDto;
import com.bankproject.demo.dto.TransactionResponseDto;
import com.bankproject.demo.exception.EntryNotFoundException;
import com.bankproject.demo.model.Account;
import com.bankproject.demo.model.Transaction;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

	@Mock
	private TransactionDao transactionDao;
	@Mock
	private AccountDao accountDao;
	@Mock
	private CustomerDao custDao;
	@InjectMocks
	private TransactionServiceImpl transactionServiceImpl;
	TransactionDto transactionDto;
	Transaction transaction;

	@BeforeEach
	public void setUp() {
		transactionDto = new TransactionDto();
		transactionDto.setFormAccountId(2);
		transactionDto.setToAccountId(3);
		transactionDto.setTransactionedAmount(2000);

		transaction = new Transaction();
		transaction.setTransactionId(12);
		transaction.setTransactionNumber("Trans1");
		transaction.setTransactionType("debit");
	}

	@Test
	void testSaveTransactions() throws EntryNotFoundException {
		Account account = new Account();
		account.setAccountId(1);
		account.setAccountNumber(2323l);
		account.setBalance(1000);
		Optional<Account> accountOption = Optional.of(account);
		when(accountDao.findById(Mockito.anyInt())).thenReturn(accountOption);
		List<TransactionResponseDto> saveTransactions = transactionServiceImpl.saveTransactions(transactionDto);
		assertNotNull(saveTransactions);
		verify(accountDao, times(2)).findById(Mockito.anyInt());

		assertEquals(-1000, saveTransactions.get(0).getAvailableBalance());
		assertEquals(2000, saveTransactions.get(0).getTransactionedAmount());
		assertEquals("debit", saveTransactions.get(0).getTransactionType());
	}

	@Test
	void testGetAllTransactionByFromAndToDates() throws EntryNotFoundException {
		Account account = new Account();
		account.setAccountId(1);
		account.setAccountNumber(2323l);
		account.setBalance(1000);
		Optional<Account> accountOption = Optional.of(account);
		List<Transaction> allTransactionByFromAndToDates = new ArrayList<Transaction>();
		allTransactionByFromAndToDates.add(transaction);
		when(accountDao.findById(Mockito.anyInt())).thenReturn(accountOption);
		// when(transactionDao.findByTransactionDateBetween(new Date(), new
		// Date())).thenReturn(allTransactionByFromAndToDates);
		List<TransactionResponseDto> saveTransactions = transactionServiceImpl.saveTransactions(transactionDto);

		assertNotNull(saveTransactions);
		verify(accountDao, times(2)).findById(Mockito.anyInt());
		// verify(transactionDao, times(1)).findByTransactionDateBetween(new Date(), new
		// Date());
		assertEquals(-1000, saveTransactions.get(0).getAvailableBalance());
		assertEquals(2000, saveTransactions.get(0).getTransactionedAmount());
		assertEquals("debit", saveTransactions.get(0).getTransactionType());

	}

}
