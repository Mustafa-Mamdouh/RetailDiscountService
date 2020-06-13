package com.ups.retail.discount.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ups.retail.discount.constants.SystemConstants;
import com.ups.retail.discount.dtos.ItemsDto;
import com.ups.retail.discount.services.interfaces.DiscountRole;
import com.ups.retail.discount.services.types.DiscountContext;
import com.ups.retail.discount.utilites.DiscountConfigurationUtility;

@Component
@Order(3)
public class ExculsionDiscountRole implements DiscountRole {
	Set<String> excludeFromDiscountItemTypes;

	@Autowired
	public ExculsionDiscountRole(DiscountConfigurationUtility discountConfigurationUtility) {
		excludeFromDiscountItemTypes = new HashSet<>();
		String[] split = discountConfigurationUtility.getProperty(SystemConstants.ExcludeFromDiscountKey).split(",");
		for (String string : split) {
			excludeFromDiscountItemTypes.add(string);
		}
	}

	@Override
	public void calculateDiscount(DiscountContext discountContext) {
		List<ItemsDto> items = discountContext.getBillDto().getItems();
		for (ItemsDto itemsDto : items) {
			if (excludeFromDiscountItemTypes.contains(itemsDto.getItemType())) {
				itemsDto.setApplyDiscount(false);
			}else {
				itemsDto.setApplyDiscount(true);
			}
		}
	}

}
