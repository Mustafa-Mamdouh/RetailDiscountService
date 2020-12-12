package com.bs.eit.retail.discount.constants;

public enum ErrorMessages {
	INTERNAL_SERVER_ERROR_Message("Imternal Server Error", 500), Bad_Request("", 400);
	String message;
	Integer code;

	private ErrorMessages(String message, Integer code) {
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}

}
