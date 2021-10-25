package com.bank.accountsmanagement.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bank account
 *
 * @author mohamedabouelalaa
 */
public class Account {
    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;
    private String label;
    private String iban;
    private String currency;
    private BigDecimal balance;

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    private List<Operation> operations;

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void addOperation(Operation operation){
        if(this.operations == null) {
            this.operations = new ArrayList<>();
        }
        this.operations.add(operation);
    }
}
