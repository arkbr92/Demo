package com.bankproject.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankproject.demo.dao.AccountDao;
import com.bankproject.demo.dao.CustomerDao;
import com.bankproject.demo.dao.TransactionDao;
import com.bankproject.demo.dto.TransactionDto;
import com.bankproject.demo.dto.TransactionResponseDto;
import com.bankproject.demo.exception.EntryNotFoundException;
import com.bankproject.demo.model.Account;
import com.bankproject.demo.model.Transaction;
import com.bankproject.demo.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	TransactionDao transactionDao;
	@Autowired
	AccountDao accountDao;
	@Autowired
	CustomerDao custDao;

	public List<TransactionResponseDto> saveTransactions(TransactionDto transactionDto) throws EntryNotFoundException {
		if (transactionDto.getFormAccountId().equals(transactionDto.getToAccountId()))
			throw new EntryNotFoundException("sender's account and receivers cannot be same");

		Optional<Account> fromAccount = accountDao.findById(transactionDto.getFormAccountId());
		if (!fromAccount.isPresent())
			throw new EntryNotFoundException("cannot find sender's account id");
		Optional<Account> toAccount = accountDao.findById(transactionDto.getToAccountId());
		if (!toAccount.isPresent())
			throw new EntryNotFoundException("cannot find receiver's account id");

		Transaction debitTransaction = new Transaction();
		debitTransaction.setAccount(fromAccount.get());
		double availableAmountFromAccount = fromAccount.get().getBalance() - transactionDto.getTransactionedAmount();
		debitTransaction.setAvailableBalance(availableAmountFromAccount);
		debitTransaction.setTransactionDate(new Date());
		debitTransaction.setTransactionType("debit");
		debitTransaction.setTransactionedAmount(transactionDto.getTransactionedAmount());
		String transactionNumber = "BANK" + getTransactionNumber() + "TR";
		debitTransaction.setTransactionNumber(transactionNumber);
		transactionDao.save(debitTransaction);

		Transaction creditTransaction = saveCreditTransaction(transactionDto, transactionNumber, toAccount);

		List<TransactionResponseDto> transactionResponseDtos = new ArrayList<TransactionResponseDto>();
		TransactionResponseDto debitResponseDto = new TransactionResponseDto();
		BeanUtils.copyProperties(debitTransaction, debitResponseDto);
		TransactionResponseDto creditResponseDto = new TransactionResponseDto();
		BeanUtils.copyProperties(creditTransaction, creditResponseDto);
		transactionResponseDtos.add(debitResponseDto);
		transactionResponseDtos.add(creditResponseDto);
		return transactionResponseDtos;

	}

	public Transaction saveCreditTransaction(TransactionDto transactionRequestDto, String transactionNumber,
			Optional<Account> toAccount) {
		Transaction transaction = new Transaction();
		transaction.setAccount(toAccount.get());
		double availableAmountToAccount = toAccount.get().getBalance() + transactionRequestDto.getTransactionedAmount();
		transaction.setAvailableBalance(availableAmountToAccount);
		transaction.setTransactionDate(new Date());
		transaction.setTransactionType("credit");
		transaction.setTransactionedAmount(transactionRequestDto.getTransactionedAmount());
		transaction.setTransactionNumber(transactionNumber);
		transactionDao.save(transaction);
		return transaction;
	}

	public long getTransactionNumber() {
		return System.currentTimeMillis();
	}

	@Override
	public List<TransactionResponseDto> getAllTransactionByFromAndToDates(Date startDate, Date endDate) {

		List<TransactionResponseDto> transactionResponseDtos = new ArrayList<TransactionResponseDto>();
		List<Transaction> allTransactionByFromAndToDates = transactionDao.findByTransactionDateBetween(startDate,
				endDate);
		/*
		 * for(Transaction transaction : allTransactionByFromAndToDates) {
		 * TransactionResponseDto responseDto = new TransactionResponseDto();
		 * BeanUtils.copyProperties(transaction, responseDto);
		 * transactionResponseDtos.add(responseDto); }
		 */

		allTransactionByFromAndToDates.stream().forEach(transaction -> {
			TransactionResponseDto responseDto = new TransactionResponseDto();
			BeanUtils.copyProperties(transaction, responseDto);
			transactionResponseDtos.add(responseDto);
		});
		return transactionResponseDtos;
	}

}
