package com.restservices.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue
	private Long orderidLong;
	private String orderdescriptionString;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	//Generation constructor from super class
	public Order() {
		super();
		
	}

	
	// Getters & Setters
	public Long getOrderidLong() {
		return orderidLong;
	}

	public void setOrderidLong(Long orderidLong) {
		this.orderidLong = orderidLong;
	}

	public String getOrderdescriptionString() {
		return orderdescriptionString;
	}

	public void setOrderdescriptionString(String orderdescriptionString) {
		this.orderdescriptionString = orderdescriptionString;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
