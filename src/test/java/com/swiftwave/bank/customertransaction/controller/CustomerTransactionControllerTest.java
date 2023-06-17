package com.swiftwave.bank.customertransaction.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Month;
import java.time.Year;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.swiftwave.bank.customertransaction.dto.Balance;
import com.swiftwave.bank.customertransaction.dto.CumulativeBalance;
import com.swiftwave.bank.customertransaction.dto.MonthlyBalance;
import com.swiftwave.bank.customertransaction.exception.NotFoundException;
import com.swiftwave.bank.customertransaction.service.BalanceService;

public class CustomerTransactionControllerTest {
	
	@Mock
	private BalanceService balanceService;
	
	@InjectMocks
    private CustomerTransactionController controller;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
    void testGetBalanceByAccNumber() throws NotFoundException {

        String accNumber = "123456789";
        
        CumulativeBalance cumBalance = CumulativeBalance.builder()
    		.balance(888.4300000000001).build();
        
        MonthlyBalance janBalance = MonthlyBalance.builder()
    		.year(Year.of(2023))
    		.month(Month.valueOf("JANUARY"))
    		.balance(358.85).build();
        
        MonthlyBalance febBalance = MonthlyBalance.builder()
        		.year(Year.of(2023))
        		.month(Month.valueOf("FEBRUARY"))
        		.balance(478.6).build();
        
        List<MonthlyBalance> lisyMonthlyBalance = List.of(janBalance, febBalance);
        
        Balance balance = Balance.builder()
    		.accountNumber("SW123")
    		.customerName("Antii")
    		.currencyCode("EUR")
    		.cumulativeBalance(cumBalance)
    		.monthlyBalances(lisyMonthlyBalance).build();

        when(balanceService.getBalanceByAccNumber(accNumber)).thenReturn(balance);
        
        Balance response = controller.getBalanceByAccNumber(accNumber);
        
        assertEquals(balance, response);
        verify(balanceService, times(1)).getBalanceByAccNumber(accNumber);
    }
    
	@Test
	void testGetBalanceByAccNumberNotFound() throws NotFoundException {
	    String accNumber = "SW789";
	    when(balanceService.getBalanceByAccNumber(accNumber))
	    	.thenThrow(new NotFoundException("Transaction not found for given account number"));

	    NotFoundException exception = assertThrows(NotFoundException.class,
	            () -> controller.getBalanceByAccNumber(accNumber));

	    verify(balanceService, times(1)).getBalanceByAccNumber(accNumber);
	    assertEquals("Transaction not found for given account number", exception.getMessage());
	}

}
