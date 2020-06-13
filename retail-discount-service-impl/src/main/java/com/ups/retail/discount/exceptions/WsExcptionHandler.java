package com.ups.retail.discount.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ups.retail.discount.dtos.ServiceResponseDto;

@ControllerAdvice
public class WsExcptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<ServiceResponseDto> handleValidationException(final Exception internalServerException) {
		internalServerException.printStackTrace();

		ServiceResponseDto serviceResult = new ServiceResponseDto(internalServerException.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResult);
	}

	@ExceptionHandler(ApplicationException.class)
	@ResponseBody
	public ResponseEntity<ServiceResponseDto> handleApplicationException(
			final ApplicationException applicationException) {
		applicationException.printStackTrace();
		ServiceResponseDto serviceResult = new ServiceResponseDto(applicationException.getReason());
		return ResponseEntity.status(applicationException.getResponseCode()).body(serviceResult);
	}

}
