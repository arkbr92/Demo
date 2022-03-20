package com.bankproject.demo.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import com.bankproject.demo.dto.AccountDto;
import com.bankproject.demo.dto.AccountRespClassProjection;
import com.bankproject.demo.dto.AccountResponseDto;
import com.bankproject.demo.dto.AccountResponseProjection;
import com.bankproject.demo.model.Account;
import com.bankproject.demo.model.Customer;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

	@InjectMocks
	private AccountServiceImpl accountServiceImpl;
	@Mock
	private AccountDao accountDao;

	@Mock
	private CustomerDao customerDao;

	private AccountDto accountDto;
	private AccountResponseDto accountResponseDto;
	private Customer customer;
	private Account account;

	@BeforeEach
	public void setUp() {
		accountDto = new AccountDto();
		accountDto.setAccountNumber(2323);
		accountDto.setAccountType("saving");
		accountDto.setBalance(1000);
		accountDto.setCustId(1);
		accountDto.setTransaction(null);

		customer = new Customer();
		customer.setCustName("Rama");
		customer.setEmail("rama@gmail.com");

		account = new Account();
		account.setAccountNumber(2323l);
		account.setAccountType("saving");
		account.setBalance(1000);
		account.setCustomer(customer);
	}

	@Test
	void testSaveData() {

		Customer customer = new Customer();
		customer.setCustName("siva");
		customer.setEmail("rama@gmail.com");
		customer.setPhoneNo("432123344");
		Optional<Customer> customerOption = Optional.of(customer);
		when(customerDao.findById(Mockito.anyInt())).thenReturn(customerOption);
		AccountResponseDto saveDataResult = accountServiceImpl.saveData(accountDto);

		assertNotNull(saveDataResult);
		verify(customerDao, times(1)).findById(Mockito.anyInt());
		assertEquals(2323l, saveDataResult.getAccountNumber());
		assertEquals(1000, saveDataResult.getBalance());
	}

	@Test
	void testGetAccountById() {

		Optional<Account> accountOptional = Optional.of(account);
		when(accountDao.findById(Mockito.anyInt())).thenReturn(accountOptional);

		AccountResponseDto accountByIdResult = accountServiceImpl.getAccountById(1);

		assertNotNull(accountByIdResult);
		verify(accountDao, times(1)).findById(Mockito.anyInt());
		assertEquals(2323l, accountByIdResult.getAccountNumber());
		assertEquals(1000, accountByIdResult.getBalance());
	}

	@Test
	void testGetAllAccounts() {
		List<AccountResponseProjection> accountResponseProjections = new ArrayList<AccountResponseProjection>();
		AccountResponseProjection account = new AccountResponseProjection();
		account.setAccountId(1);
		account.setAccountNumber(2323l);
		account.setBalance(1000);
		accountResponseProjections.add(account);
		when(accountDao.findAllAccounts()).thenReturn(accountResponseProjections);

		List<AccountResponseProjection> allAccounts = accountServiceImpl.getAllAccounts();

		assertNotNull(allAccounts);
		verify(accountDao, times(1)).findAllAccounts();
		assertTrue(allAccounts.size() > 0);
		assertEquals(2323l, allAccounts.get(0).getAccountNumber());
		assertEquals(1000, allAccounts.get(0).getBalance());

	}

	@Test
	void testGetAccountByAccountNumber() {
		AccountRespClassProjection account = new AccountRespClassProjection(2323l, 1000, null, "saving");
		when(accountDao.findByAccountNumber(2323l)).thenReturn(account);
		AccountRespClassProjection accounts = accountServiceImpl.getAccountByAccountNumber(2323l);
		assertNotNull(accounts);
		verify(accountDao, times(1)).findByAccountNumber(2323l);
		assertEquals(2323l, accounts.getAccountNumber());
		assertEquals(1000, accounts.getBalance());
		assertEquals("saving", accounts.getAccountType());
	}

}
