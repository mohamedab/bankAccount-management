package com.bank.accountsmanagement.ports.spi;

import com.bank.accountsmanagement.models.Account;

import java.util.Optional;

public interface AccountPersistencePort {
    /**
     * Return the account if exists, otherwise <code>EMPTY</code>
     * @param accountIban The IBAN of the account
     * @return Account if exists, otherwise <code>EMPTY</code>
     */
    Optional<Account> get(String accountIban);

    /**
     * Save the account into DB.
     * @param account Account to save
     */
    Account save(Account account);
}
