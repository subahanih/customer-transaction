/**
 * Author: Mahaboob Subahan (subahanih@gmail.com)
 * Date: 17-06-2023
 */
package com.swiftwave.bank.customertransaction.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.swiftwave.bank.customertransaction.dto.Error;

@RestControllerAdvice
public class CustomerTransactionExceptionHandler {
	
	private static final String ERROR_MSG_NOT_FOUND = "Requested detail not found.";
	private static final String API_EXCEPTION = "Customer Transaction API exception.";
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Error notFoundException(NotFoundException exception, WebRequest request) {
		return Error.builder()
			.timestamp(LocalDateTime.now())
			.status(HttpStatus.NOT_FOUND.value())
			.message(exception.getMessage())
			.error(ERROR_MSG_NOT_FOUND)
			.path(request.getDescription(false)).build();
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public Error globalException(Exception exception, WebRequest request) {
		return Error.builder()
			.timestamp(LocalDateTime.now())
			.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.message(exception.getMessage())
			.error(API_EXCEPTION)
			.path(request.getDescription(false)).build();
	}

}
