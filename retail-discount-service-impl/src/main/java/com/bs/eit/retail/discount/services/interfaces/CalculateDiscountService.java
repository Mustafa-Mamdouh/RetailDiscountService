package com.bs.eit.retail.discount.services.interfaces;

import com.bs.eit.retail.discount.dtos.BillDto;
import com.bs.eit.retail.discount.dtos.ServiceResponseDto;

public interface CalculateDiscountService {
	ServiceResponseDto discountValue(BillDto billDto);
}
