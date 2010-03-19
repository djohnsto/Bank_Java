package com.thoughtworks.bank;

import com.thoughtworks.controllers.BankAccountController;
import com.thoughtworks.repositories.impl.AccountRepositoryInMemory;
import com.thoughtworks.repositories.impl.AuditLogRepositoryInMemory;
import com.thoughtworks.repositories.impl.CustomerRepositoryInMemory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BankMemoryTests {
    private BankAccountController bankAccountController;

    @Before
        public void setUp() throws Exception{
            bankAccountController = new BankAccountController();
            bankAccountController.setCustomerRepository(new CustomerRepositoryInMemory());
            bankAccountController.setAccountRepository(new AccountRepositoryInMemory());
            bankAccountController.setAuditLogRepository(new AuditLogRepositoryInMemory());
        }

    @Test
    public void CreateAccountResultsInHavingANewCustomer() throws Exception
    {
        bankAccountController.Create("Joe", "Blow", 800);
        Customer firstCustomer = bankAccountController.CustomerRepository.GetAll().get(bankAccountController.CustomerRepository.GetAll().size()-1);
        Assert.assertEquals("Joe", firstCustomer.firstName);
        Assert.assertEquals("Blow", firstCustomer.lastName);
    }

    @Test
    public void CreateAccountResultsInHavingANewAccount() throws Exception
    {
        bankAccountController.Create("Joe", "Blow", 800);
        Account firstAccount = bankAccountController.AccountRepository.GetAll().get(bankAccountController.AccountRepository.GetAll().size()-1);
        Assert.assertEquals("Joe", firstAccount.holder.firstName);
        Assert.assertEquals("Blow", firstAccount.holder.lastName);
    }

    @Test
    public void DepositResultsInHavingMoney() throws Exception
    {
        bankAccountController.Create("Joe", "Blow", 800);
        Customer firstCustomer = bankAccountController.CustomerRepository.GetAll().get(bankAccountController.CustomerRepository.GetAll().size()-1);
        bankAccountController.Deposit(firstCustomer.id, new BigDecimal(500));
        Account firstAccount = bankAccountController.AccountRepository.GetAll().get(bankAccountController.AccountRepository.GetAll().size()-1);
        Assert.assertEquals(new BigDecimal(500), firstAccount.Balance);
    }


    @Test
    public void WithdrawResultsInLosingMoney() throws Exception
    {
        bankAccountController.Create("Joe", "Blow", 800);
        Customer firstCustomer = bankAccountController.CustomerRepository.GetAll().get(bankAccountController.CustomerRepository.GetAll().size()-1);
        bankAccountController.Deposit(firstCustomer.id, new BigDecimal(500));
        bankAccountController.Withdraw(firstCustomer.id, new BigDecimal(200));
        Account firstAccount = bankAccountController.AccountRepository.GetAll().get(bankAccountController.AccountRepository.GetAll().size()-1);
        Assert.assertEquals(new BigDecimal(300), firstAccount.Balance);
    }

    @Test
    public void LargeWithdrawResultsInAudit() throws Exception
    {
        bankAccountController.Create("Joe", "Blow", 800);
        Customer firstCustomer = bankAccountController.CustomerRepository.GetAll().get(bankAccountController.CustomerRepository.GetAll().size()-1);
        bankAccountController.Deposit(firstCustomer.id, new BigDecimal(30000));
        bankAccountController.Withdraw(firstCustomer.id, new BigDecimal(11000));
        bankAccountController.Withdraw(firstCustomer.id, new BigDecimal(10000));
        List<Audit> auditLog = bankAccountController.AuditLogRepository.GetAll();
        Assert.assertEquals(2, auditLog.size());
    }
}