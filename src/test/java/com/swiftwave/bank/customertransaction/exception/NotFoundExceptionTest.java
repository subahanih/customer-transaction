package com.swiftwave.bank.customertransaction.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class NotFoundExceptionTest {
	
	@Test
    void testNotFoundException() {
        String errorMessage = "Requested detail not found.";
        NotFoundException exception = new NotFoundException(errorMessage);
        
        assertEquals(errorMessage, exception.getMessage());
    }

}
