package com.ups.retail.discount.dtos;

import java.util.List;

public class BillDto {
	private  List<ItemsDto> items;
	private  String userId;

	public BillDto() {
		super();
	}

	public List<ItemsDto> getItems() {
		return items;
	}

	public void setItems(List<ItemsDto> items) {
		this.items = items;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "BillDto [items=" + items + ", userId=" + userId + "]";
	}

}
