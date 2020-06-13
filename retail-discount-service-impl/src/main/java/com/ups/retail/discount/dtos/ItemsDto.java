package com.ups.retail.discount.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ItemsDto {
	private String itemName;
	private Double quantity;
	private Double unitPrice;
	private String itemType;
	private Double totalPrice;
	@JsonIgnore
	private Boolean applyDiscount;

	public ItemsDto() {
		super();
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Boolean getApplyDiscount() {
		return applyDiscount;
	}

	public void setApplyDiscount(Boolean applyDiscount) {
		this.applyDiscount = applyDiscount;
	}

	@Override
	public String toString() {
		return "ItemsDto [itemName=" + itemName + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", itemType="
				+ itemType + ", totalPrice=" + totalPrice + ", applyDiscount=" + applyDiscount + "]";
	}

}
