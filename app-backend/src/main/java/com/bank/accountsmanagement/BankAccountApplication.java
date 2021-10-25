package com.bank.accountsmanagement;


import com.bank.accountsmanagement.config.AppConfig;
import com.bank.accountsmanagement.config.InfraConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({InfraConfig.class, AppConfig.class})
public class BankAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountApplication.class, args);
    }
}
