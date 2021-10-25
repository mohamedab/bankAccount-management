package com.bank.accountsmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatementsDto {
    List<OperationDto> operationsList;
    AccountDto accountDto;
}
