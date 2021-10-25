package com.bank.accountsmanagement.sevices;

import com.bank.accountsmanagement.models.Account;
import com.bank.accountsmanagement.models.Operation;
import com.bank.accountsmanagement.models.TypeOperation;
import com.bank.accountsmanagement.ports.api.AccountServicePort;
import com.bank.accountsmanagement.ports.spi.AccountPersistencePort;

import java.util.Optional;

public class AccountServiceImpl implements AccountServicePort {

    private AccountPersistencePort accountPersistencePort;

    public AccountServiceImpl(AccountPersistencePort accountPersistencePort) {
        this.accountPersistencePort = accountPersistencePort;
    }

    @Override
    public Optional<Account> get(String accountIban) {
        return accountPersistencePort.get(accountIban);
    }

    @Override
    public void addOperation(String accountIban, Operation operation) throws UnknownAccountException {
        Optional<Account> accountOptional = get(accountIban);
        if(!accountOptional.isPresent()) {
            throw new UnknownAccountException("Account:" + accountIban + " is unknown");
        }
        Account account = accountOptional.get();
        account.addOperation(operation);
        if(TypeOperation.WITHDRAWAL.equals(operation.getTypeOperation())) {
            account.setBalance(account.getBalance().subtract(operation.getAmount()));
        } else {
            account.setBalance(account.getBalance().add(operation.getAmount()));
        }
        saveAccount(account);
    }

    @Override
    public void saveAccount(Account account) {
        accountPersistencePort.save(account);
    }

    @Override
    public Optional<Account> getAccountStatements(String accountIban) throws UnknownAccountException {
        Optional<Account> accountOptional = get(accountIban);
        if(!accountOptional.isPresent()) {
            throw new UnknownAccountException("Account:" + accountIban + " is unknown");
        }
        return accountOptional;
    }

}
