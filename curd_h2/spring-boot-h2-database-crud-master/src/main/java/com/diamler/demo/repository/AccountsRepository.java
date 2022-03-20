package com.diamler.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.diamler.demo.model.Account;

public interface AccountsRepository extends JpaRepository<Account, Long> {

}