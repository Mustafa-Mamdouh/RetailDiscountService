package com.ups.retail.discount.exceptions;

import com.ups.retail.discount.constants.ErrorMessages;

public class ApplicationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -184305369725661529L;
	private Integer responseCode;
	private String reason;
	public ApplicationException(ErrorMessages errorMessage) {
		super(errorMessage.getMessage());
		this.responseCode = errorMessage.getCode();
	}
	public ApplicationException(ErrorMessages errorMessage,String reason) {
		super(errorMessage.getMessage());
		this.responseCode = errorMessage.getCode();
		this.reason=reason;
	}
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
