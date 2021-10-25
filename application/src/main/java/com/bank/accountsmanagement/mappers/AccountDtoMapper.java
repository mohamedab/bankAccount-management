package com.bank.accountsmanagement.mappers;

import com.bank.accountsmanagement.dto.AccountDto;
import com.bank.accountsmanagement.models.Account;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AccountDtoMapper {

    AccountDto toDto(Account account);
}
