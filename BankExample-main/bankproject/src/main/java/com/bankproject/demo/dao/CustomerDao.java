package com.bankproject.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bankproject.demo.dto.CustRespProjection;
import com.bankproject.demo.model.Customer;

@Repository
public interface CustomerDao extends CrudRepository<Customer, Integer> {

	List<CustRespProjection> findByCustNameLike(String name);

	List<Customer> findByCustName(String name);

}
