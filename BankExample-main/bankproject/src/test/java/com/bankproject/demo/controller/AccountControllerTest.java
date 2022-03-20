package com.bankproject.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bankproject.demo.dto.AccountDto;
import com.bankproject.demo.dto.AccountRespClassProjection;
import com.bankproject.demo.dto.AccountResponseDto;
import com.bankproject.demo.dto.AccountResponseProjection;
import com.bankproject.demo.service.AccountService;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

	@Mock
	AccountService accountService;

	@InjectMocks
	AccountController accountController;

	AccountDto accountDto;

	@BeforeEach
	public void setUp() {
		accountDto = new AccountDto();
		accountDto.setAccountNumber(232333444);
		accountDto.setAccountType("saving");
		accountDto.setBalance(324234);
	}

	@Test
	void testSaveData() {
		AccountResponseDto accountResponseDto = new AccountResponseDto();
		accountResponseDto.setAccountNumber(112233l);
		accountResponseDto.setBalance(20000);
		accountResponseDto.setAccountId(1);
		when(accountService.saveData(accountDto)).thenReturn(accountResponseDto);
		ResponseEntity<AccountResponseDto> saveDataResult = accountController.saveData(accountDto);
		assertNotNull(saveDataResult);
		verify(accountService, times(1)).saveData(Mockito.any(AccountDto.class));
		assertEquals(HttpStatus.CREATED, saveDataResult.getStatusCode());
		assertEquals(112233l, saveDataResult.getBody().getAccountNumber());
		assertEquals(20000, saveDataResult.getBody().getBalance());
		assertEquals(1, saveDataResult.getBody().getAccountId());
	}

	@Test
	void testGetAllTransactionByAccounts() {
		AccountResponseDto accountResponseDto = new AccountResponseDto();
		accountResponseDto.setAccountNumber(112233l);
		accountResponseDto.setBalance(20000);
		accountResponseDto.setAccountId(1);
		when(accountService.getAccountById(Mockito.anyInt())).thenReturn(accountResponseDto);
		AccountResponseDto allTransactionByAccountsRst = accountController
				.getAllTransactionByAccounts(Mockito.anyInt());
		assertNotNull(allTransactionByAccountsRst);
		verify(accountService, times(1)).getAccountById(Mockito.anyInt());
		assertEquals(112233l, allTransactionByAccountsRst.getAccountNumber());
		assertEquals(20000, allTransactionByAccountsRst.getBalance());
		assertEquals(1, allTransactionByAccountsRst.getAccountId());
	}

	@Test
	void testGetAllAccounts() {
		List<AccountResponseProjection> accountResponseProjections = new ArrayList<AccountResponseProjection>();
		AccountResponseProjection accountResponseProjection = new AccountResponseProjection();
		accountResponseProjection.setAccountNumber(112233l);
		accountResponseProjection.setBalance(20000);
		accountResponseProjection.setAccountId(1);
		accountResponseProjections.add(accountResponseProjection);
		when(accountService.getAllAccounts()).thenReturn(accountResponseProjections);
		List<AccountResponseProjection> allAccountsResult = accountController.getAllAccounts();
		assertNotNull(allAccountsResult);
		verify(accountService, times(1)).getAllAccounts();
		assertEquals(112233l, allAccountsResult.get(0).getAccountNumber());
		assertEquals(20000, allAccountsResult.get(0).getBalance());
		assertEquals(1, allAccountsResult.get(0).getAccountId());
	}

	@Test
	void testGetAccountByAccountNumber() {
		AccountRespClassProjection accountRespClassProjection = new AccountRespClassProjection(112233l, 20000, null,
				"Saving");
		when(accountService.getAccountByAccountNumber(Mockito.anyLong())).thenReturn(accountRespClassProjection);
		AccountRespClassProjection accountByAccountNumberRst = accountController
				.getAccountByAccountNumber(Mockito.anyLong());
		assertNotNull(accountByAccountNumberRst);
		verify(accountService, times(1)).getAccountByAccountNumber(Mockito.anyLong());
		assertEquals(112233l, accountByAccountNumberRst.getAccountNumber());
		assertEquals(20000, accountByAccountNumberRst.getBalance());
		assertEquals("Saving", accountByAccountNumberRst.getAccountType());
	}

}
