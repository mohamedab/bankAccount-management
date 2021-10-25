package com.bank.accountsmanagement.config;

import com.bank.accountsmanagement.adapters.AccountJpaAdapter;
import com.bank.accountsmanagement.entities.AccountEntity;
import com.bank.accountsmanagement.mappers.AccountMapper;
import com.bank.accountsmanagement.ports.api.AccountServicePort;
import com.bank.accountsmanagement.ports.spi.AccountPersistencePort;
import com.bank.accountsmanagement.repositories.AccountRepository;
import com.bank.accountsmanagement.sevices.AccountServiceImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackageClasses = {AccountJpaAdapter.class, AccountMapper.class})
@EntityScan(basePackageClasses = AccountEntity.class)
@EnableJpaRepositories(basePackageClasses = AccountRepository.class)
public class InfraConfig {

    @Bean
    public AccountPersistencePort accountPersistencePort() {
        return new AccountJpaAdapter();
    }

    @Bean
    public AccountServicePort accountService() {
        return new AccountServiceImpl(accountPersistencePort());
    }

}
