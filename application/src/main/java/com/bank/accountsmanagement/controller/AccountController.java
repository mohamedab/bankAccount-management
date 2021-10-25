package com.bank.accountsmanagement.controller;


import com.bank.accountsmanagement.dto.OperationDto;
import com.bank.accountsmanagement.dto.StatementsDto;
import com.bank.accountsmanagement.mappers.AccountDtoMapper;
import com.bank.accountsmanagement.mappers.OperationDtoMapper;
import com.bank.accountsmanagement.models.Account;
import com.bank.accountsmanagement.models.Operation;
import com.bank.accountsmanagement.ports.api.AccountServicePort;
import com.bank.accountsmanagement.sevices.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/accounts/{accountIban}/operations")
public class AccountController {

    @Autowired
    private AccountServicePort accountServicePort;
    @Autowired
    private OperationDtoMapper operationDtoMapper;
    @Autowired
    private AccountDtoMapper accountDtoMapper;

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@PathVariable String accountIban, @RequestBody OperationDto operationDto) throws UnknownAccountException, IllegalArgumentException {
        return addOperationResponse(accountIban, operationDto);
    }


    @PostMapping("/withdrawal")
    public ResponseEntity<?> withdrawal(@PathVariable String accountIban, @RequestBody OperationDto operationDto) throws UnknownAccountException, IllegalArgumentException {
        return addOperationResponse(accountIban, operationDto);
    }

    @GetMapping("/history")
    public StatementsDto getHistory(@PathVariable String accountIban) throws UnknownAccountException {
        Optional<Account> account = accountServicePort.getAccountStatements(accountIban);
        return StatementsDto.builder()
                .accountDto(accountDtoMapper.toDto(account.get()))
                .operationsList(operationDtoMapper.toListDto(account.get().getOperations()))
                .build();
    }

    private ResponseEntity<?> addOperationResponse(@PathVariable String accountIban, @RequestBody OperationDto operationDto) throws UnknownAccountException {
        try {
            Operation operation = operationDtoMapper.fromDto(operationDto);
            accountServicePort.addOperation(accountIban, operation);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Unknown type of operation: " + operationDto.getTypeOperation());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ExceptionHandler(value = UnknownAccountException.class)
    public ResponseEntity<?> getExceptionError(UnknownAccountException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<?> getExceptionError(IllegalArgumentException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
