package com.bankproject.demo.service;

import java.util.List;

import com.bankproject.demo.dto.AccountDto;
import com.bankproject.demo.dto.AccountRespClassProjection;
import com.bankproject.demo.dto.AccountResponseDto;
import com.bankproject.demo.dto.AccountResponseProjection;
import com.bankproject.demo.exception.CustomerNotFoundException;

public interface AccountService {

	AccountResponseDto saveData(AccountDto accountDto) throws CustomerNotFoundException;

	AccountResponseDto getAccountById(Integer accountId) throws CustomerNotFoundException;

	List<AccountResponseProjection> getAllAccounts();

	AccountRespClassProjection getAccountByAccountNumber(Long accountNumber);

}
