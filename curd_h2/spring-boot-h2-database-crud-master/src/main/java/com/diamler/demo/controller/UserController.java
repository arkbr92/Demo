 package com.diamler.demo.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamler.demo.model.Account;
import com.diamler.demo.repository.AccountsRepository;

/**
 * Adding User Account Operation
 * @author arunkbr
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	static Logger logger = Logger.getLogger(UserController.class.getName());
    @Autowired
    private AccountsRepository accountsRepository;
    
    /**
     * get all Account details
     * @param pageable
     * @param assembler
     * @return  all Account details
     */
    
    @GetMapping("/getAllUser")
    public ResponseEntity<List<Account>> getAllAccounts() {
    	logger.info("UserController getAllAccounts started:: ");
    	List<Account> allAccounts = accountsRepository.findAll();
    	logger.info("UserController getAllAccounts ended:: ");
        return new ResponseEntity<>(allAccounts, HttpStatus.OK);
    }
    
    /**
     * save Account deatils
     * @param account
     * @return 
     */
    
    @PostMapping("/save")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
    	logger.info("AccountController createAccount started:: ");
		try {
			Account accountSaved = accountsRepository.save(account);
			logger.info("AccountController createAccount ended:: ");
			return new ResponseEntity<>(accountSaved, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    /**
     * delete account details
     * @param id
     * @return
     */
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") long id) {
    	logger.info("AccountController deleteAccount started:: ");
      try {
        accountsRepository.deleteById(id);
        
        logger.info("AccountController deleteAccount ended:: ");
        return new ResponseEntity<>("Account was deleted successfully.", HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>("Cannot delete Account.", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}
