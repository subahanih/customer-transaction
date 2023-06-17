/**
 * Author: Mahaboob Subahan (subahanih@gmail.com)
 * Date: 17-06-2023
 */
package com.swiftwave.bank.customertransaction.service;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftwave.bank.customertransaction.dto.Balance;
import com.swiftwave.bank.customertransaction.dto.CumulativeBalance;
import com.swiftwave.bank.customertransaction.dto.MonthlyBalance;
import com.swiftwave.bank.customertransaction.dto.Transaction;
import com.swiftwave.bank.customertransaction.exception.NotFoundException;
import com.swiftwave.bank.customertransaction.util.TransactionType;

@Service
public class BalanceService {
	private final static Logger log = LoggerFactory.getLogger(BalanceService.class);
	
	private final TransactionService transactionService;
	
	@Autowired
	public BalanceService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	public Balance getBalanceByAccNumber(String accNumber) throws NotFoundException {
		log.info("Inside getBalanceByAccNumber");
		Balance balance = new Balance();
		List<Transaction> sortedTransactions = new ArrayList<>();
		sortedTransactions = transactionService.getTransactionByAccNumber(accNumber);
		
		if (sortedTransactions.isEmpty()) {
			throw new NotFoundException("Transaction not found for given account number: " + accNumber
				+ " Available acc numbers: [SW123 & SW456]");
	    }
		
		Collections.sort(sortedTransactions, Comparator.comparing(Transaction::getTransactionDate));
		Transaction firstTransaction = sortedTransactions.get(0);
		balance.setAccountNumber(firstTransaction.getAccountNumber());
		balance.setCustomerName(firstTransaction.getCustomerName());
		balance.setCurrencyCode(firstTransaction.getCurrencyCode());

		
		Set<YearMonth> listYearMonth = sortedTransactions.parallelStream()
			.map(transaction -> YearMonth.from(transaction.getTransactionDate()))
			.collect(Collectors.toSet());
		
		List<MonthlyBalance> monthlyBalances = new ArrayList<MonthlyBalance>();
		CumulativeBalance cumulativeBalance = new CumulativeBalance();

		for(YearMonth yearMonth: listYearMonth ){
			Year year = Year.of(yearMonth.getYear());
			Month month = yearMonth.getMonth();
			double balanceMonthly = 0.0;
			
			for(Transaction transaction: sortedTransactions){
				if(transaction.getTransactionDate().getYear() == yearMonth.getYear() &&
					transaction.getTransactionDate().getMonth() == yearMonth.getMonth()) {
					
					if(transaction.getTransactionType().equals(TransactionType.DEBIT)) 
						balanceMonthly += transaction.getAmount();

					else if(transaction.getTransactionType().equals(TransactionType.CREDIT)) 
						balanceMonthly -= transaction.getAmount();
				} 
			}
			monthlyBalances.add(new MonthlyBalance(year, month, balanceMonthly));
			cumulativeBalance.setBalance(cumulativeBalance.getBalance() + balanceMonthly);
		}
		balance.setCumulativeBalance(cumulativeBalance);
		balance.setMonthlyBalances(monthlyBalances);
		log.info("Cumulative & Monthly balances are calculated successfully.");
		return balance;
	}
}
