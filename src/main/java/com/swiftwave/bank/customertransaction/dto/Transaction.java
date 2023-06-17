/**
 * Author: Mahaboob Subahan (subahanih@gmail.com)
 * Date: 17-06-2023
 */
package com.swiftwave.bank.customertransaction.dto;

import java.time.LocalDate;

import com.swiftwave.bank.customertransaction.util.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
	
	private String accountNumber;
	private String customerName;
	private double amount;
	private TransactionType transactionType;
    private LocalDate transactionDate;
    private String currencyCode;

}
