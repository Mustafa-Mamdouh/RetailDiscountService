package com.ups.retail.discount.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ups.retail.discount.dtos.ServiceResponseDto;

@ControllerAdvice
public class ApplicationExcptionHandler {
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<ServiceResponseDto> handleGeneralException(final Exception internalServerException) {
		ServiceResponseDto serviceResult = new ServiceResponseDto(internalServerException.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResult);
	}

	@ExceptionHandler(ApplicationException.class)
	@ResponseBody
	public ResponseEntity<ServiceResponseDto> handleApplicationException(
			final ApplicationException applicationException) {
		ServiceResponseDto serviceResult = new ServiceResponseDto(applicationException.getReason());
		return ResponseEntity.status(applicationException.getResponseCode()).body(serviceResult);
	}

}
