package com.bs.eit.retail.discount.apis;

import com.bs.eit.retail.discount.constants.SystemConstants;
import com.bs.eit.retail.discount.services.interfaces.CalculateDiscountService;
import com.bs.eit.retail.discount.utilites.JsonScehmaValidationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.eit.retail.discount.dtos.BillDto;
import com.bs.eit.retail.discount.dtos.ServiceResponseDto;

@RestController
@RequestMapping("/discount")
public class BillDiscountApi {
	@Autowired
	private CalculateDiscountService calculateDiscountService;
	@Autowired
	private JsonScehmaValidationManager schemaValdiationManager;

	@PostMapping
	public ResponseEntity<ServiceResponseDto> calculateDiscountApi(@RequestBody BillDto billDto) {
		schemaValdiationManager.validate(billDto, SystemConstants.calculateSchemaName);
		ServiceResponseDto discountValue = calculateDiscountService.discountValue(billDto);
		return ResponseEntity.status(HttpStatus.OK).body(discountValue);
	}

}
