package src.com.thoughtworks.bankcontroller;

import src.com.thoughtworks.RepositoryInDatabase.AccountRepositoryInDatabase;
import src.com.thoughtworks.RepositoryInDatabase.AuditLogRepositoryInDatabase;
import src.com.thoughtworks.RepositoryInDatabase.CustomerRepositoryInDatabase;
import src.com.thoughtworks.bank.Account;
import src.com.thoughtworks.bank.Audit;
import src.com.thoughtworks.bank.Bank;
import src.com.thoughtworks.bank.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Mar 16, 2010
 * Time: 1:28:19 PM
 * To change this template use File | Settings | File Templates.
 */
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
        Bank bank = new Bank("BankName", 720);
        Customer customer = CustomerRepository.GetCustomerById(id);
        Account account = AccountRepository.GetAccountByCustomer(customer);
        account.Deposit(amount);
        AccountRepository.UpdateAccountBalance(account);
    }

    public void Withdraw(int id, BigDecimal amount) throws Exception
    {
        Bank bank = new Bank("BankName", 720);
        List<Audit> auditLog = new ArrayList();
        Customer customer = CustomerRepository.GetCustomerById(id);
        Account account = AccountRepository.GetAccountByCustomer(customer);
        account.Withdraw(amount, auditLog);
        AccountRepository.UpdateAccountBalance(account);
        if(auditLog.size()!=0){
            AuditLogRepository.WriteEntries(auditLog);
        }
    }
    
}
