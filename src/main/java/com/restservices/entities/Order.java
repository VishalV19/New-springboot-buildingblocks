package com.restservices.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orders")
public class Order extends ResourceSupport{

	@Id
	@GeneratedValue
	private Long orderid;
	private String orderdescription;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	//Generation constructor from super class
	public Order() {
		super();
		
	}

	
	// Getters & Setters
	public Long getOrderidLong() {
		return orderid;
	}

	public void setOrderidLong(Long orderidLong) {
		this.orderid = orderidLong;
	}

	public String getOrderdescriptionString() {
		return orderdescription;
	}

	public void setOrderdescriptionString(String orderdescriptionString) {
		this.orderdescription = orderdescriptionString;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
