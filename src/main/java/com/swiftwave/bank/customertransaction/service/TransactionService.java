/**
 * Author: Mahaboob Subahan (subahanih@gmail.com)
 * Date: 17-06-2023
 */
package com.swiftwave.bank.customertransaction.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.swiftwave.bank.customertransaction.dto.Transaction;
import com.swiftwave.bank.customertransaction.exception.NotFoundException;
import com.swiftwave.bank.customertransaction.util.TransactionType;

/*
 * This class act as API which provides transaction details.  
 */
@Service
public class TransactionService {

	public List<Transaction> getTransactionByAccNumber(String accNumber) throws NotFoundException {
		List<Transaction> transactionsForAccNumber = new ArrayList<>();
		
		transactionsForAccNumber = getTransaction().stream()
			.filter(transaction -> transaction.getAccountNumber().equalsIgnoreCase(accNumber))
			.collect(Collectors.toList());
			
		if(!transactionsForAccNumber.isEmpty())
			return transactionsForAccNumber;
		else throw new NotFoundException("Transaction not found for given account number: " + accNumber
			+ " Available acc numbers: [SW123 & SW456]");
	}
	
	private List<Transaction> getTransaction() {
		return List.of(
			new Transaction("SW123", "Antii", 100.0, TransactionType.DEBIT,  LocalDate.of(2022, 1, 1), "EUR"),
			new Transaction("SW123", "Antii", 50.0, TransactionType.CREDIT,  LocalDate.of(2022, 1, 3), "EUR"),
			new Transaction("SW123", "Antii", 300.0, TransactionType.DEBIT,  LocalDate.of(2022, 2, 5), "EUR"), 
			new Transaction("SW123", "Antii", 67.85, TransactionType.CREDIT,  LocalDate.of(2022, 2, 11), "EUR"),
			new Transaction("SW123", "Antii", 20.0, TransactionType.DEBIT,  LocalDate.of(2022, 2, 18), "EUR"),
			new Transaction("SW123", "Antii", 100.0, TransactionType.DEBIT,  LocalDate.of(2023, 1, 1), "EUR"),
			new Transaction("SW123", "Antii", 50.0, TransactionType.CREDIT,  LocalDate.of(2023, 1, 3), "EUR"),
			new Transaction("SW123", "Antii", 300.0, TransactionType.DEBIT,  LocalDate.of(2023, 1, 5), "EUR"),
			new Transaction("SW123", "Antii", 67.85, TransactionType.CREDIT,  LocalDate.of(2023, 1, 11), "EUR"),
			new Transaction("SW123", "Antii", 20.0, TransactionType.DEBIT,  LocalDate.of(2023, 1, 18), "EUR"),
			new Transaction("SW123", "Antii", 7.19, TransactionType.CREDIT,  LocalDate.of(2023, 1, 31), "EUR"),
			new Transaction("SW123", "Antii", 85.32, TransactionType.CREDIT,  LocalDate.of(2023, 2, 2), "EUR"),
			new Transaction("SW123", "Antii", 670.0, TransactionType.DEBIT,  LocalDate.of(2023, 2, 4), "EUR"),
			new Transaction("SW123", "Antii", 16.15, TransactionType.CREDIT,  LocalDate.of(2023, 2, 6), "EUR"),
			new Transaction("SW123", "Antii", 8.16, TransactionType.CREDIT,  LocalDate.of(2023, 2, 12), "EUR"),
			new Transaction("SW123", "Antii", 250.0, TransactionType.DEBIT,  LocalDate.of(2023, 3, 3), "EUR"),
			new Transaction("SW123", "Antii", 160.0, TransactionType.DEBIT,  LocalDate.of(2023, 3, 5), "EUR"),
			new Transaction("SW123", "Antii", 78.66, TransactionType.CREDIT,  LocalDate.of(2023, 3, 17), "EUR"),
			new Transaction("SW123", "Antii", 80.35, TransactionType.CREDIT,  LocalDate.of(2023, 3, 19), "EUR"),
			new Transaction("SW123", "Antii", 51.0, TransactionType.CREDIT,  LocalDate.of(2023, 3, 21), "EUR"),
			new Transaction("SW123", "Antii", 125.0, TransactionType.DEBIT,  LocalDate.of(2023, 3, 25), "EUR"),
			new Transaction("SW123", "Antii", 25.0, TransactionType.DEBIT,  LocalDate.of(2023, 4, 5), "EUR"),
			new Transaction("SW123", "Antii", 95.0, TransactionType.CREDIT,  LocalDate.of(2023, 4, 15), "EUR"),
			new Transaction("SW123", "Antii", 15.0, TransactionType.CREDIT,  LocalDate.of(2023, 4, 16), "EUR"),
			new Transaction("SW123", "Antii", 58.61, TransactionType.DEBIT,  LocalDate.of(2023, 4, 19), "EUR"),
			new Transaction("SW123", "Antii", 36.70, TransactionType.CREDIT,  LocalDate.of(2023, 4, 20), "EUR"),
			new Transaction("SW123", "Antii", 400.0, TransactionType.DEBIT,  LocalDate.of(2023, 4, 22), "EUR"),
			new Transaction("SW123", "Antii", 350.0, TransactionType.CREDIT,  LocalDate.of(2023, 4, 23), "EUR"),
			new Transaction("SW123", "Antii", 10.05, TransactionType.CREDIT,  LocalDate.of(2023, 4, 30), "EUR"),
			
			new Transaction("SW456", "Liisa", 200.0, TransactionType.DEBIT,  LocalDate.of(2023, 1, 15), "EUR"),
			new Transaction("SW456", "Liisa", 15.0, TransactionType.CREDIT,  LocalDate.of(2023, 1, 18), "EUR"),
			new Transaction("SW456", "Liisa", 32.15, TransactionType.CREDIT,  LocalDate.of(2023, 1, 22), "EUR"),
			new Transaction("SW456", "Liisa", 150.0, TransactionType.DEBIT,  LocalDate.of(2023, 1, 30), "EUR"),
			new Transaction("SW456", "Liisa", 56.0, TransactionType.DEBIT,  LocalDate.of(2023, 1, 31), "EUR"),
			new Transaction("SW456", "Liisa", 12.08, TransactionType.CREDIT,  LocalDate.of(2023, 2, 2), "EUR"),
			new Transaction("SW456", "Liisa", 100.0, TransactionType.DEBIT,  LocalDate.of(2023, 2, 4), "EUR"),
			new Transaction("SW456", "Liisa", 350.0, TransactionType.DEBIT,  LocalDate.of(2023, 2, 5), "EUR"),
			new Transaction("SW456", "Liisa", 68.32, TransactionType.CREDIT,  LocalDate.of(2023, 2, 9), "EUR"),
			new Transaction("SW456", "Liisa", 9.0, TransactionType.CREDIT,  LocalDate.of(2023, 2, 16), "EUR"),
			new Transaction("SW456", "Liisa", 70.0, TransactionType.CREDIT,  LocalDate.of(2023, 2, 19), "EUR"),
			new Transaction("SW456", "Liisa", 188.0, TransactionType.DEBIT,  LocalDate.of(2023, 2, 21), "EUR"),
			new Transaction("SW456", "Liisa", 49.0, TransactionType.CREDIT,  LocalDate.of(2023, 3, 14), "EUR"),
			new Transaction("SW456", "Liisa", 2.15, TransactionType.CREDIT,  LocalDate.of(2023, 3, 18), "EUR"),
			new Transaction("SW456", "Liisa", 90.0, TransactionType.DEBIT,  LocalDate.of(2023, 3, 19), "EUR"),
			new Transaction("SW456", "Liisa", 35.86, TransactionType.CREDIT,  LocalDate.of(2023, 3, 28), "EUR"),
			new Transaction("SW456", "Liisa", 12.01, TransactionType.CREDIT,  LocalDate.of(2023, 3, 29), "EUR"),
			new Transaction("SW456", "Liisa", 60.0, TransactionType.DEBIT,  LocalDate.of(2023, 3, 30), "EUR")
		);
	}
}
