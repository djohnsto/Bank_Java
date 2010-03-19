package com.thoughtworks.controllers;

import com.thoughtworks.repositories.AccountRepository;
import com.thoughtworks.repositories.AuditLogRepository;
import com.thoughtworks.repositories.CustomerRepository;
import com.thoughtworks.bank.Account;
import com.thoughtworks.bank.Audit;
import com.thoughtworks.bank.Bank;
import com.thoughtworks.bank.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
public class BankAccountController {

    public void setCustomerRepository(CustomerRepository customerRepository) {
        CustomerRepository = customerRepository;
    }
    public void setAccountRepository(AccountRepository accountRepository) {
        AccountRepository = accountRepository;
    }
    public void setAuditLogRepository(AuditLogRepository auditLogRepository) {
        AuditLogRepository = auditLogRepository;
    }

    public CustomerRepository CustomerRepository;
    public AccountRepository AccountRepository;
    public AuditLogRepository AuditLogRepository;

    public void Create(String firstName, String lastName, int ficoScore) throws Exception
    {
        Bank bank = new Bank("BankName", 720);
        boolean creditPassed = bank.CheckCredit(ficoScore);
        if (creditPassed)
        {
            Customer newCustomer = CustomerRepository.CreateCustomer(ficoScore, lastName, firstName);
            Account newAccount = AccountRepository.CreateAccount(newCustomer);
            bank.OpenAccount(newAccount);
        }
    }

    public void Deposit(int id, BigDecimal amount) throws Exception
    {
        Customer customer = CustomerRepository.GetCustomerById(id);
        Account account = AccountRepository.GetAccountByCustomer(customer);
        account.Deposit(amount);
        AccountRepository.UpdateAccountBalance(account);
    }

    public void Withdraw(int id, BigDecimal amount) throws Exception
    {
        List<Audit> auditLog = new ArrayList<Audit>();
        Customer customer = CustomerRepository.GetCustomerById(id);
        Account account = AccountRepository.GetAccountByCustomer(customer);
        account.Withdraw(amount, auditLog);
        AccountRepository.UpdateAccountBalance(account);
        if(auditLog.size()!=0){
            AuditLogRepository.WriteEntries(auditLog);
        }
    }
    
}
