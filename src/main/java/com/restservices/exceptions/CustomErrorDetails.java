package com.restservices.exceptions;

import java.util.Date;

//Simple custom error details bean
public class CustomErrorDetails {

	private Date timestamp;
	private String message;
	private String errordetails;
	
	//Generating field constructor
	public CustomErrorDetails(Date timestamp, String message, String errordetails) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errordetails = errordetails;
	}
	
	// Generating getters & setters
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrordetails() {
		return errordetails;
	}

	public void setErrordetails(String errordetails) {
		this.errordetails = errordetails;
	}
	
	
	
	
}
