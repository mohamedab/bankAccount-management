package com.bank.accountsmanagement.ports.api;

import com.bank.accountsmanagement.models.Account;
import com.bank.accountsmanagement.models.Operation;
import com.bank.accountsmanagement.sevices.AccountBalanceInsufficientException;
import com.bank.accountsmanagement.sevices.UnknownAccountException;

import java.util.Optional;

public interface AccountServicePort {
    /**
     * Return the account if exists, otherwise <code>EMPTY</code>
     * @param accountIban The IBAN of the account
     * @return Account if exists, otherwise <code>EMPTY</code>
     */
    Optional<Account> get(String accountIban);

    /**
     * Add an operation to the account passed in the parameter.
     * @param accountIban The IBAN of the account
     * @param operation The operation to add
     * @throws UnknownAccountException Thrown when the account does not exist in DB.
     */
    void addOperation(String accountIban, Operation operation) throws UnknownAccountException, AccountBalanceInsufficientException;

    /**
     * Save the account into DB.
     * @param account Account to save
     */
    void saveAccount(Account account);

    /**
     * Get the statements of the account passed in parameter.
     * @param accountIban The IBAN of the account
     * @return Statements of the account passed in parameter.
     * @throws UnknownAccountException Thrown when the account does not exist in DB.
     */
    Optional<Account> getAccountStatements(String accountIban) throws UnknownAccountException;
}
