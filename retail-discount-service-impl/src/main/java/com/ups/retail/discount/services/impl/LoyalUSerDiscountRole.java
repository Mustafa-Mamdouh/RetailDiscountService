package com.ups.retail.discount.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ups.retail.discount.constants.ErrorMessages;
import com.ups.retail.discount.constants.SystemConstants;
import com.ups.retail.discount.exceptions.ApplicationException;
import com.ups.retail.discount.services.interfaces.DiscountRole;
import com.ups.retail.discount.services.types.DiscountContext;
import com.ups.retail.discount.utilites.DiscountConfigurationUtility;

@Component
@Order(2)
class LoyalUSerDiscountRole implements DiscountRole {
	Integer loyalYearsNumber;
	Integer discountValue;
	String discountType;

	@Autowired
	public LoyalUSerDiscountRole(DiscountConfigurationUtility discountConfigurationUtility) {
		loyalYearsNumber = Integer
				.parseInt(discountConfigurationUtility.getProperty(SystemConstants.LoyalUserYearCountKey));
		discountValue = Integer
				.parseInt(discountConfigurationUtility.getProperty(SystemConstants.LoyalUserDiscountValue));
		discountType = discountConfigurationUtility.getProperty(SystemConstants.LoyalUserDiscountType);
		if ("percentage".equals(discountType) && discountValue > 100) {
			throw new ApplicationException(ErrorMessages.INTERNAL_SERVER_ERROR_Message, "wrong configuration");
		}
	}

	@Override
	public void calculateDiscount(DiscountContext discountContext) {
		Date registerationDate = discountContext.getUser().getRegisterationDate();
		Date clonedDate = (Date) registerationDate.clone();
		clonedDate.setYear(clonedDate.getYear() + loyalYearsNumber);
		if (new Date().compareTo(clonedDate) > 0) {
			if ("percentage".equals(discountType) && discountContext.getCurrentPercentageDiscount() < discountValue) {
				discountContext.setCurrentPercentageDiscount((short) discountValue.intValue());
			} else if (discountContext.getCurrentCashDiscount() < discountValue) {
				discountContext.setCurrentCashDiscount(discountValue);
			}
		}
	}

}
