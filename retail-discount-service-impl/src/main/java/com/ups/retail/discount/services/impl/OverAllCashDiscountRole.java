package com.ups.retail.discount.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ups.retail.discount.constants.SystemConstants;
import com.ups.retail.discount.dtos.ItemsDto;
import com.ups.retail.discount.services.interfaces.DiscountRole;
import com.ups.retail.discount.services.types.DiscountContext;
import com.ups.retail.discount.utilites.DiscountConfigurationUtility;

@Component
@Order(4)
public class OverAllCashDiscountRole implements DiscountRole {
	Integer cashAmountDiscount;
	Integer cashAmountDiscountUnit;

	@Autowired
	public OverAllCashDiscountRole(DiscountConfigurationUtility discountConfigurationUtility) {
		cashAmountDiscount = Integer
				.parseInt(discountConfigurationUtility.getProperty(SystemConstants.CashAmountDiscountKey));

		cashAmountDiscountUnit = Integer
				.parseInt(discountConfigurationUtility.getProperty(SystemConstants.CashAmountDiscountUnitKey));

	}

	@Override
	public void calculateDiscount(DiscountContext discountContext) {
		Double totalBill = calculateItemsPrice(discountContext.getCurrentPercentageDiscount(),
				discountContext.getBillDto().getItems(), discountContext);
		Double a = (totalBill / cashAmountDiscountUnit);
		a = (double) (a.intValue() * cashAmountDiscount);
		discountContext.setCashDiscountAmount(a);
		discountContext.setFinalBill(totalBill - a);

	}

	private Double calculateItemsPrice(Short currentPercentageDiscount, List<ItemsDto> items,
			DiscountContext discountContext) {
		Double totalBill = 0.0;
		Double totalPercentageDiscount = 0.0;
		for (ItemsDto itemsDto : items) {
			Double tempTotalItem = itemsDto.getQuantity() * itemsDto.getUnitPrice();
			if (itemsDto.getApplyDiscount()) {
				double discount = tempTotalItem * currentPercentageDiscount / 100;
				tempTotalItem -= discount;
				totalPercentageDiscount += discount;
			}
			itemsDto.setTotalPrice(tempTotalItem);
			totalBill += tempTotalItem;
		}

		discountContext.setPercentageDiscountAmount(totalPercentageDiscount);
		return totalBill;
	}

}
