package com.bank.accountsmanagement.models;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Class represents an operation.
 *
 * @author mohamedabouelalaa
 */
public class Operation {

    private Integer id;
    private String label;
    private BigDecimal amount;
    private LocalDate date;
    private TypeOperation typeOperation;
    private Account account;

    public Account getAccount() {
        return account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) { this.id = id; }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }
}
