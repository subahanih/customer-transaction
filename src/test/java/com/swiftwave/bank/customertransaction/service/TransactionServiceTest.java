package com.swiftwave.bank.customertransaction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.swiftwave.bank.customertransaction.dto.Transaction;
import com.swiftwave.bank.customertransaction.exception.NotFoundException;
import com.swiftwave.bank.customertransaction.util.TransactionType;

public class TransactionServiceTest {
	
	@Mock
	private TransactionService transactionService;
	
	@BeforeEach
    void setUp() {
        transactionService = mock(TransactionService.class);
    }
	
	@Test
	void testGetTransactionByAccNumber() throws NotFoundException {
	    String accNumber = "SW123";

	    List<Transaction> expectedTransactions = List.of(
	        new Transaction("SW123", "Antii", 100.0, TransactionType.DEBIT, LocalDate.of(2022, 1, 1), "EUR"),
	        new Transaction("SW123", "Antii", 50.0, TransactionType.CREDIT, LocalDate.of(2022, 1, 3), "EUR")
	    );

	    when(transactionService.getTransactionByAccNumber(accNumber)).thenReturn(expectedTransactions);

	    List<Transaction> actualTransactions = transactionService.getTransactionByAccNumber(accNumber);

	    assertEquals(expectedTransactions.size(), actualTransactions.size());
	    assertEquals(expectedTransactions, actualTransactions);
	    verify(transactionService, times(1)).getTransactionByAccNumber(accNumber);
	}
	
	@Test
	void testGetTransactionByAccNumberNotFound() throws NotFoundException {
	    String accNumber = "SW789";

	    when(transactionService.getTransactionByAccNumber(accNumber))
	    	.thenThrow(new NotFoundException("Transaction not found for given account number"));

	    NotFoundException exception = assertThrows(NotFoundException.class,
	            () -> transactionService.getTransactionByAccNumber(accNumber));

	    verify(transactionService, times(1)).getTransactionByAccNumber(accNumber);
	    assertEquals("Transaction not found for given account number", exception.getMessage());
	}

}
