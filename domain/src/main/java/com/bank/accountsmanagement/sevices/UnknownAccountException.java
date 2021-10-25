package com.bank.accountsmanagement.sevices;

/**
 * Exception thrown when the account is not found.
 */
public class UnknownAccountException extends Exception {

    public UnknownAccountException(String msg) {
        super(msg);
    }

}
