package com.swiftwave.bank.customertransaction.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import com.swiftwave.bank.customertransaction.dto.Error;

public class CustomerTransactionExceptionHandlerTest {
	
	private CustomerTransactionExceptionHandler handler = new CustomerTransactionExceptionHandler();

    @Test
    void testNotFoundException() {
        String errorMessage = "Requested detail not found.";
        MockHttpServletRequest request = new MockHttpServletRequest();
        WebRequest webRequest = new ServletWebRequest(request);

        NotFoundException exception = new NotFoundException(errorMessage);
        Error error = handler.notFoundException(exception, webRequest);

        assertNotNull(error);
        assertEquals(LocalDateTime.now().getYear(), error.getTimestamp().getYear());
        assertEquals(HttpStatus.NOT_FOUND.value(), error.getStatus());
        assertEquals(errorMessage, error.getMessage());
    }

    @Test
    void testGlobalException() {
        String errorMessage = "Customer Transaction API exception.";
        MockHttpServletRequest request = new MockHttpServletRequest();
        WebRequest webRequest = new ServletWebRequest(request);

        Exception exception = new Exception(errorMessage);
        Error error = handler.globalException(exception, webRequest);

        assertNotNull(error);
        assertEquals(LocalDateTime.now().getYear(), error.getTimestamp().getYear());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), error.getStatus());
        assertEquals(errorMessage, error.getMessage());
    }

}
