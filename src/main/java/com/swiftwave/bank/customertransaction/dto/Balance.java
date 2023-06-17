/**
 * Author: Mahaboob Subahan (subahanih@gmail.com)
 * Date: 17-06-2023
 */
package com.swiftwave.bank.customertransaction.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Balance {
	
	private String accountNumber;
	private String customerName;
	private String currencyCode;
	private CumulativeBalance cumulativeBalance;
	private List<MonthlyBalance> monthlyBalances;
 
}
