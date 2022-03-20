package com.diamler.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long Id;

    private String username;

    private String firstName;

    private String lastName;
}
