package com.bankproject.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	private String city;
	private String state;

	/*
	 * @OneToOne(mappedBy = "address") private Customer customer;
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * public Customer getCustomer() { return customer; } public void
	 * setCustomer(Customer customer) { this.customer = customer; }
	 */
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
