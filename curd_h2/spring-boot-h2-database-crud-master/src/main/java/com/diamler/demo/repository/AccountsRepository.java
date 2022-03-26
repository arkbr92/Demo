package com.diamler.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.diamler.demo.model.Account;

public interface AccountsRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByFirstName(String firstName);

}