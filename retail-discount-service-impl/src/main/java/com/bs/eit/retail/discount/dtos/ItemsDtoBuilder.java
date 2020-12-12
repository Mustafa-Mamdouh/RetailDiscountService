package com.bs.eit.retail.discount.dtos;

public class ItemsDtoBuilder {
    private String itemName;
    private Double quantity;
    private Double unitPrice;
    private String itemType;
    private Double totalPrice;
    private Boolean applyDiscount;

    public ItemsDtoBuilder itemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public ItemsDtoBuilder quantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public ItemsDtoBuilder unitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public ItemsDtoBuilder itemType(String itemType) {
        this.itemType = itemType;
        return this;
    }

    public ItemsDtoBuilder totalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public ItemsDtoBuilder applyDiscount(Boolean applyDiscount) {
        this.applyDiscount = applyDiscount;
        return this;
    }

    public ItemsDto build() {
        return new ItemsDto(itemName, quantity, unitPrice, itemType, totalPrice, applyDiscount);
    }
}