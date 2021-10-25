package com.bank.accountsmanagement.mappers;

import com.bank.accountsmanagement.entities.AccountEntity;
import com.bank.accountsmanagement.models.Account;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AccountMapper {

    AccountEntity toEntity(Account account);

    Account fromEntity(AccountEntity accountEntity);
}
