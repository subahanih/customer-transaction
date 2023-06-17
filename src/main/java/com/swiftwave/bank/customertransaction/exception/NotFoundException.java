/**
 * Author: Mahaboob Subahan (subahanih@gmail.com)
 * Date: 17-06-2023
 */
package com.swiftwave.bank.customertransaction.exception;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = -8784298599939733050L;

	public NotFoundException(String message) {
        super(message);
    }
}
