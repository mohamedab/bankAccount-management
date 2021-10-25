package com.bank.accountsmanagement.config;


import com.bank.accountsmanagement.controller.AccountController;
import com.bank.accountsmanagement.mappers.AccountDtoMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {AccountController.class, AccountDtoMapper.class})
public class AppConfig {
}
