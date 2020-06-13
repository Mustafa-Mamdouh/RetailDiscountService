package com.ups.retail.discount.services.types;

import com.ups.retail.discount.dtos.BillDto;
import com.ups.retail.discount.models.User;

public class DiscountContext {
	private BillDto billDto;
	private Short currentPercentageDiscount;
	private Integer currentCashDiscount;
	private User user;
	private Double percentageDiscountAmount;
	private Double CashDiscountAmount;
	private Double finalBill;

	public DiscountContext() {
		super();
		currentPercentageDiscount = 0;
		currentCashDiscount = 0;
	}

	public DiscountContext(BillDto billDto, User user) {
		this();
		this.billDto = billDto;
		this.user = user;
	}

	public DiscountContext(BillDto billDto, Short currentPercentageDiscount, Integer currentCashDiscount, User user) {
		this.billDto = billDto;
		this.currentPercentageDiscount = currentPercentageDiscount;
		this.currentCashDiscount = currentCashDiscount;
		this.user = user;
	}

	public BillDto getBillDto() {
		return billDto;
	}

	public void setBillDto(BillDto billDto) {
		this.billDto = billDto;
	}

	public Short getCurrentPercentageDiscount() {
		return currentPercentageDiscount;
	}

	public void setCurrentPercentageDiscount(Short currentPercentageDiscount) {
		this.currentPercentageDiscount = currentPercentageDiscount;
	}

	public Integer getCurrentCashDiscount() {
		return currentCashDiscount;
	}

	public void setCurrentCashDiscount(Integer currentCashDiscount) {
		this.currentCashDiscount = currentCashDiscount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
