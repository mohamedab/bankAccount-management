package com.bank.accountsmanagement.sevices;

/**
 * Thrown when the account balance is insufficient.
 */
public class AccountBalanceInsufficientException extends Exception {
    AccountBalanceInsufficientException(String msg){
        super(msg);
    }
}
