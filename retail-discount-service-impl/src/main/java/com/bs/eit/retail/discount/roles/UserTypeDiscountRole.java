package com.bs.eit.retail.discount.roles;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import com.bs.eit.retail.discount.repos.UserTypesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bs.eit.retail.discount.models.UserTypes;
import com.bs.eit.retail.discount.services.interfaces.DiscountRole;
import com.bs.eit.retail.discount.services.types.DiscountContext;

@Component
@Order(10)
class UserTypeDiscountRole implements DiscountRole {
	@Autowired
	private UserTypesRepo userTypesRepo;
	private  HashMap<Integer, UserTypes> cachedUserTypesMap;

	@PostConstruct
	public void init() {
		cachedUserTypesMap = new HashMap<>();
		List<UserTypes> findAll = userTypesRepo.findAll();
		if (findAll != null) {
			findAll.forEach(type -> cachedUserTypesMap.put(type.getId(), type));
		}
	}

	@Override
	public void calculateDiscount(DiscountContext discountContext) {
		UserTypes userTypes = cachedUserTypesMap.get(discountContext.getUser().getUserType().getId());
		short discountValue = userTypes.getDiscountValue();
		if (discountContext.getCurrentPercentageDiscount() < discountValue) {
			discountContext.setCurrentPercentageDiscount(discountValue);
		}
	}

}
