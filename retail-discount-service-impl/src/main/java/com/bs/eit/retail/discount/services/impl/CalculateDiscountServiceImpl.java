package com.bs.eit.retail.discount.services.impl;

import java.util.List;
import java.util.Optional;

import com.bs.eit.retail.discount.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bs.eit.retail.discount.constants.ErrorMessages;
import com.bs.eit.retail.discount.dtos.BillDto;
import com.bs.eit.retail.discount.dtos.ServiceResponseDto;
import com.bs.eit.retail.discount.exceptions.ApplicationException;
import com.bs.eit.retail.discount.services.interfaces.CalculateDiscountService;
import com.bs.eit.retail.discount.services.interfaces.DiscountRole;
import com.bs.eit.retail.discount.services.types.DiscountContext;

@Component
class CalculateDiscountServiceImpl implements CalculateDiscountService {
	@Autowired
	private com.bs.eit.retail.discount.repos.UsersRepo UsersRepo;
	@Autowired
	private List<DiscountRole> roles;

	@Override
	public ServiceResponseDto discountValue(BillDto billDto) {
		Optional<User> findByUserId = UsersRepo.findByUserId(billDto.getUserId());
		if (!findByUserId.isPresent()) {
			throw new ApplicationException(ErrorMessages.Bad_Request, "Invalid User Id");
		}
		User user = findByUserId.get();
		DiscountContext discountContext = new DiscountContext(billDto, user);
		for (DiscountRole discountRole : roles) {
			discountRole.calculateDiscount(discountContext);
		}

		return prepareResponse(discountContext);
	}

	private ServiceResponseDto prepareResponse(DiscountContext discountContext) {
		ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
		serviceResponseDto.setCashDiscountAmount(discountContext.getCashDiscountAmount());
		serviceResponseDto.setFinalBill(discountContext.getFinalBill());
		serviceResponseDto.setPercentageDiscountAmount(discountContext.getPercentageDiscountAmount());
		return serviceResponseDto;
	}

}
