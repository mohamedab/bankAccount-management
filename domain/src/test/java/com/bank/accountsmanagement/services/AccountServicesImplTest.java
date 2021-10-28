package com.bank.accountsmanagement.services;

import com.bank.accountsmanagement.models.Account;
import com.bank.accountsmanagement.models.Operation;
import com.bank.accountsmanagement.models.TypeOperation;
import com.bank.accountsmanagement.ports.api.AccountServicePort;
import com.bank.accountsmanagement.ports.spi.AccountPersistencePort;
import com.bank.accountsmanagement.sevices.AccountBalanceInsufficientException;
import com.bank.accountsmanagement.sevices.AccountServiceImpl;
import com.bank.accountsmanagement.sevices.UnknownAccountException;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class  AccountServicesImplTest {

    private AccountPersistencePort accountPersistencePort;
    private AccountServicePort accountService;

    @Before
    public void setUp() {
        accountPersistencePort = mock(AccountPersistencePort.class);
        accountService = new AccountServiceImpl(accountPersistencePort);
    }


    @Test
    public void testSaveAccount_nullParam() {
        accountService.saveAccount(null);
        verify(accountPersistencePort).save(null);
    }

    @Test
    public void testSaveAccount_ok() {
        final Account account = new Account();
        account.setId(1);
        accountService.saveAccount(account);
        verify(accountPersistencePort).save(account);
    }

    @Test
    public void testGetAccount_nullParm() {
        Optional<Account> account = accountService.get(null);

        verify(accountPersistencePort).get(null);
        assertFalse(account.isPresent());
    }

    @Test
    public void addOperation_ParamsNulls() {
        try {
            accountService.addOperation(null, null);
        } catch (UnknownAccountException e) {
            assertEquals(e.getMessage(), "Account :" + null + " is unknown");
        } catch (AccountBalanceInsufficientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddOperation_ParamAccountIbanNull() {
        Operation operation = new Operation();
        operation.setId(1);
        try {
            accountService.addOperation(null, operation);
        } catch (UnknownAccountException e) {
            assertEquals(e.getMessage(), "Account :" + null + " is unknown");
        } catch (AccountBalanceInsufficientException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testAddOperation_ParamAccountOperation() throws UnknownAccountException, AccountBalanceInsufficientException {
        String accountIban = "FR7630001007941234567890185";
        accountService.addOperation(accountIban, null);
        verifyNoInteractions(accountPersistencePort);
    }



    @Test
    public void testAddOperation_unknownAccount() {
        Operation operation = new Operation();
        operation.setId(1);
        String accountIban = "FR7630001007941234567890185";
        when(accountPersistencePort.get(accountIban)).thenReturn(Optional.empty());
        try {
            accountService.addOperation(accountIban, operation);
        } catch (UnknownAccountException e) {
            assertEquals(e.getMessage(), "Account :" + accountIban + " is unknown");
        } catch (AccountBalanceInsufficientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeposit() throws UnknownAccountException, AccountBalanceInsufficientException {
        Account account = createAccount();
        Operation operation = createOperation(TypeOperation.DEPOSIT);
        when(accountPersistencePort.get(account.getIban())).thenReturn(Optional.of(account));
        accountService.addOperation(account.getIban(), operation);
        assertNotNull(account.getOperations());
        assertEquals(operation, account.getOperations().get(0));
        assertEquals(BigDecimal.valueOf(510.00), account.getBalance());
    }

    @Test
    public void testWihdraw_accountBalanceInsufficient() throws UnknownAccountException {
        Account account = createAccount();
        Operation operation = createOperation(TypeOperation.WITHDRAWAL);
        when(accountPersistencePort.get(account.getIban())).thenReturn(Optional.of(account));
        try {
            accountService.addOperation(account.getIban(), operation);
        } catch (AccountBalanceInsufficientException e) {
            assertEquals(e.getMessage(), "The account balance is insufficient to perform this operation");
        }
    }

    @Test
    public void testWihdraw() throws UnknownAccountException, AccountBalanceInsufficientException {
        Account account = createAccount();
        account.setBalance(BigDecimal.valueOf(1000.00));
        Operation operation = createOperation(TypeOperation.WITHDRAWAL);
        when(accountPersistencePort.get(account.getIban())).thenReturn(Optional.of(account));
        accountService.addOperation(account.getIban(), operation);
        assertNotNull(account.getOperations());
        assertEquals(operation, account.getOperations().get(0));
        assertEquals(BigDecimal.valueOf(500.00), account.getBalance());
    }

    private Account createAccount() {
        Account account = new Account();
        account.setId(1);
        account.setBalance(BigDecimal.TEN);
        account.setCurrency("EUR");
        account.setIban("FR7630001007941234567890185");
        account.setLabel("Account1");
        return account;
    }

    private Operation createOperation(TypeOperation typeOperation)  {
        Operation operation = new Operation();
        operation.setId(1);
        operation.setAmount(BigDecimal.valueOf(500.00));
        operation.setDate(LocalDate.now());
        operation.setLabel("op1");
        operation.setTypeOperation(typeOperation);
        return operation;
    }
}
