/**
 * Author: Mahaboob Subahan (subahanih@gmail.com)
 * Date: 17-06-2023
 */
package com.swiftwave.bank.customertransaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swiftwave.bank.customertransaction.annotation.SwiftWaveCusTranController;
import com.swiftwave.bank.customertransaction.dto.Balance;
import com.swiftwave.bank.customertransaction.exception.NotFoundException;
import com.swiftwave.bank.customertransaction.service.BalanceService;

@RestController
@SwiftWaveCusTranController
@RequestMapping("/customer-transaction")
public class CustomerTransactionController {
	private final BalanceService balanceService;

    @Autowired
    public CustomerTransactionController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }
    
    @GetMapping("/get-balance-by-accnumber/{accNumber}")
    public Balance getBalanceByAccNumber(@PathVariable String accNumber) throws NotFoundException{
    	return balanceService.getBalanceByAccNumber(accNumber);
    }
    
}
