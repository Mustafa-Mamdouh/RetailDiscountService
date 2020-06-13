package com.ups.retail.discount.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ServiceResponseDto {

	private String errorMessage;
	private Double percentageDiscountAmount;
	private Double CashDiscountAmount;
	private Double finalBill;

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
