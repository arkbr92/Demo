package com.bankproject.demo.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bankproject.demo.model.Transaction;

@Repository
public interface TransactionDao extends CrudRepository<Transaction, Long> {

	@Query("FROM Transaction u ORDER BY u.transactionId DESC")
	Transaction findFirstByOrderByDateDesc();

	/*
	 * @Query("FROM Transaction u WHERE u.transactionDate BETWEEN :startDate AND :endDate"
	 * ) List<Transaction> getAllTransactionByFromAndToDates(@Param("startDate")
	 * LocalDateTime startDate,
	 * 
	 * @Param("endDate") LocalDateTime endDate);
	 */
	List<Transaction> findByTransactionDateBetween(@Temporal(TemporalType.DATE) Date startDate,
			@Temporal(TemporalType.DATE) Date endDate);

	/*
	 * @Query("FROM Transaction u WHERE u.account_Id = :accountId")
	 * List<Transaction> getAllTransactionByAccountId(Integer accountId);
	 */

	// Account findById(long accountNumber);
	// long getAccountNumber();
	// void save(Transaction transaction);
	// void save(TransactionDao transactionDao);

}
