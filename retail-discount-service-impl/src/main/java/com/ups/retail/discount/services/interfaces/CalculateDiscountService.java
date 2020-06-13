package com.ups.retail.discount.services.interfaces;

import com.ups.retail.discount.dtos.BillDto;
import com.ups.retail.discount.dtos.ServiceResponseDto;

public interface CalculateDiscountService {
	ServiceResponseDto discountValue(BillDto billDto);
}
