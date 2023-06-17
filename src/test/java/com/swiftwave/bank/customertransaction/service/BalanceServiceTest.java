package com.swiftwave.bank.customertransaction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.swiftwave.bank.customertransaction.dto.Balance;
import com.swiftwave.bank.customertransaction.dto.CumulativeBalance;
import com.swiftwave.bank.customertransaction.dto.MonthlyBalance;
import com.swiftwave.bank.customertransaction.dto.Transaction;
import com.swiftwave.bank.customertransaction.exception.NotFoundException;
import com.swiftwave.bank.customertransaction.util.TransactionType;

public class BalanceServiceTest {
	
	@Mock
    private TransactionService transactionService;

	@Mock
    private BalanceService balanceService;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
        balanceService = new BalanceService(transactionService);
    }
	
	@Test
	void testGetBalanceByAccNumberValid() throws NotFoundException {
	    String accNumber = "SW123";

	    List<Transaction> transactions = new ArrayList<>();
	    transactions.add(new Transaction("SW123", "Antii", 100.0, TransactionType.DEBIT, LocalDate.of(2022, 1, 1), "EUR"));
	    transactions.add(new Transaction("SW123", "Antii", 50.0, TransactionType.CREDIT, LocalDate.of(2022, 1, 3), "EUR"));

	    when(transactionService.getTransactionByAccNumber(accNumber)).thenReturn(transactions);

	    CumulativeBalance cumBalance = CumulativeBalance.builder()
	    		.balance(50.0).build();
	        
        MonthlyBalance janBalance = MonthlyBalance.builder()
    		.year(Year.of(2022))
    		.month(Month.valueOf("JANUARY"))
    		.balance(50.0).build();
        
        List<MonthlyBalance> lisyMonthlyBalance = List.of(janBalance);
        
        Balance expectedBalance  = Balance.builder()
    		.accountNumber("SW123")
    		.customerName("Antii")
    		.currencyCode("EUR")
    		.cumulativeBalance(cumBalance)
    		.monthlyBalances(lisyMonthlyBalance).build();

	    Balance actualBalance = balanceService.getBalanceByAccNumber(accNumber);
	    assertEquals(expectedBalance, actualBalance);
	    verify(transactionService, times(1)).getTransactionByAccNumber(accNumber);
	}
	
	@Test
	void testGetBalanceByAccNumberNoTransactions() throws NotFoundException {
	    String accNumber = "SW789";

	    when(transactionService.getTransactionByAccNumber(accNumber)).thenReturn(Collections.emptyList());

	    assertThrows(NotFoundException.class, () -> {
	        balanceService.getBalanceByAccNumber(accNumber);
	    });

	    verify(transactionService, times(1)).getTransactionByAccNumber(accNumber);
	}
	
	@Test
	void testGetBalanceByAccNumberWithDebitAndCreditTransactions() throws NotFoundException {
	    String accNumber = "SW456";

	    List<Transaction> transactions = new ArrayList<>();
	    transactions.add(new Transaction("SW456", "Liisa", 100.0, TransactionType.DEBIT, LocalDate.of(2022, 1, 1), "EUR"));
	    transactions.add(new Transaction("SW456", "Liisa", 50.0, TransactionType.CREDIT, LocalDate.of(2022, 1, 3), "EUR"));
	    transactions.add(new Transaction("SW456", "Liisa", 25.0, TransactionType.DEBIT, LocalDate.of(2022, 1, 5), "EUR"));

	    when(transactionService.getTransactionByAccNumber(accNumber)).thenReturn(transactions);

	    
	    CumulativeBalance cumBalance = CumulativeBalance.builder()
	    		.balance(75.0).build();
	        
        MonthlyBalance janBalance = MonthlyBalance.builder()
    		.year(Year.of(2022))
    		.month(Month.valueOf("JANUARY"))
    		.balance(75.0).build();
        
        List<MonthlyBalance> lisyMonthlyBalance = List.of(janBalance);
        
        Balance expectedBalance  = Balance.builder()
    		.accountNumber("SW456")
    		.customerName("Liisa")
    		.currencyCode("EUR")
    		.cumulativeBalance(cumBalance)
    		.monthlyBalances(lisyMonthlyBalance).build();

	    Balance actualBalance = balanceService.getBalanceByAccNumber(accNumber);

	    assertEquals(expectedBalance, actualBalance);
	    verify(transactionService, times(1)).getTransactionByAccNumber(accNumber);
	} 

}
