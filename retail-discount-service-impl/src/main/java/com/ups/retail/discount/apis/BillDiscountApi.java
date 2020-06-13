package com.ups.retail.discount.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ups.retail.discount.constants.SystemConstants;
import com.ups.retail.discount.dtos.BillDto;
import com.ups.retail.discount.dtos.ServiceResponseDto;
import com.ups.retail.discount.services.interfaces.CalculateDiscountService;
import com.ups.retail.discount.utilites.JsonScehmaValidationManager;

@RestController
@RequestMapping("/discount")
public class BillDiscountApi {
	@Autowired
	CalculateDiscountService calculateDiscountService;
	@Autowired
	JsonScehmaValidationManager schemaValdiationManager;

	@PostMapping
	public ResponseEntity<ServiceResponseDto> calculateDiscountApi(@RequestBody BillDto billDto) {
		schemaValdiationManager.validate(billDto, SystemConstants.calculateSchemaName);
		ServiceResponseDto discountValue = calculateDiscountService.discountValue(billDto);
		return ResponseEntity.status(HttpStatus.OK).body(discountValue);
	}

}
