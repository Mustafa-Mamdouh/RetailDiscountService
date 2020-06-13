package com.ups.retail.discount.dtos;

public class ServiceResponseDto {
	String errorMessage;
	Double percentageDiscountAmount;
	Double CashDiscountAmount;
	Double finalBill;

	public ServiceResponseDto() {
		super();
	}

	public ServiceResponseDto(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Double getPercentageDiscountAmount() {
		return percentageDiscountAmount;
	}

	public void setPercentageDiscountAmount(Double percentageDiscountAmount) {
		this.percentageDiscountAmount = percentageDiscountAmount;
	}

	public Double getCashDiscountAmount() {
		return CashDiscountAmount;
	}

	public void setCashDiscountAmount(Double cashDiscountAmount) {
		CashDiscountAmount = cashDiscountAmount;
	}

	public Double getFinalBill() {
		return finalBill;
	}

	public void setFinalBill(Double finalBill) {
		this.finalBill = finalBill;
	}

}
