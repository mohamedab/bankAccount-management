package com.bank.accountsmanagement.repositories;

import com.bank.accountsmanagement.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    AccountEntity findByIban(String accountIban);
}
