package com.bank.accountsmanagement.adapters;

import com.bank.accountsmanagement.entities.AccountEntity;
import com.bank.accountsmanagement.mappers.AccountMapper;
import com.bank.accountsmanagement.models.Account;
import com.bank.accountsmanagement.ports.spi.AccountPersistencePort;
import com.bank.accountsmanagement.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountJpaAdapter implements AccountPersistencePort {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Optional<Account> get(String accountIban) {
        AccountEntity accountEntity = accountRepository.findByIban(accountIban);
        if (accountEntity == null) {
            return Optional.empty();
        }
        return Optional.of(accountMapper.fromEntity(accountEntity));
    }

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = accountMapper.toEntity(account);
        accountRepository.save(accountEntity);
        return account;
    }

}
