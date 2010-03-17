package com.thoughtworks.bankcontroller;

import com.thoughtworks.RepositoryInDatabase.AccountRepositoryInDatabase;
import com.thoughtworks.RepositoryInDatabase.AuditLogRepositoryInDatabase;
import com.thoughtworks.RepositoryInDatabase.CustomerRepositoryInDatabase;
import com.thoughtworks.bank.Account;
import com.thoughtworks.bank.Audit;
import com.thoughtworks.bank.Bank;
import com.thoughtworks.bank.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
public class BankAccountController {

    public CustomerRepositoryInDatabase CustomerRepository;
    public AccountRepositoryInDatabase AccountRepository;
    public AuditLogRepositoryInDatabase AuditLogRepository;

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
