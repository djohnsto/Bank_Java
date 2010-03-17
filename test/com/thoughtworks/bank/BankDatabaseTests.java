package com.thoughtworks.bank;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.com.thoughtworks.RepositoryInDatabase.AccountRepositoryInDatabase;
import src.com.thoughtworks.RepositoryInDatabase.AuditLogRepositoryInDatabase;
import src.com.thoughtworks.RepositoryInDatabase.CustomerRepositoryInDatabase;
import src.com.thoughtworks.bank.Account;
import src.com.thoughtworks.bank.Audit;
import src.com.thoughtworks.bank.Customer;
import src.com.thoughtworks.bankcontroller.BankAccountController;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Mar 17, 2010
 * Time: 9:47:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class BankDatabaseTests {
        private Connection connection;
        private Statement statement;
        private BankAccountController bankAccountController;
    
        @Before
        public void setUp() throws Exception{
            Class.forName("org.hsqldb.jdbcDriver");
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "SA", "");
            Statement statement= connection.createStatement();
            //statement.executeQuery("CREATE TABLE Customer (_id INT IDENTITY, _ficoScore INT, _lastName VARCHAR(255), _firstName VARCHAR(255));");
            //statement.executeQuery("CREATE TABLE Accounts (_id INT IDENTITY, _customerId INT, _balance DECIMAL);");
            //statement.executeQuery("CREATE TABLE AuditLog (_accountId INT, _amount DECIMAL, _timeStamp TIMESTAMP);");
            statement.close();
            connection.close();
        }

        @Test
        public void InsertNewCustomer() throws Exception
        {
            bankAccountController = new BankAccountController();
            bankAccountController.CustomerRepository = new CustomerRepositoryInDatabase();
            bankAccountController.AccountRepository = new AccountRepositoryInDatabase();
            bankAccountController.AuditLogRepository = new AuditLogRepositoryInDatabase();
            bankAccountController.Create("Joe3", "Blow3", 800);
            Customer firstCustomer = bankAccountController.CustomerRepository.GetAll().get(bankAccountController.CustomerRepository.GetAll().size()-1);
            Assert.assertEquals("Joe3", firstCustomer.firstName);
            Assert.assertEquals("Blow3", firstCustomer.lastName);
        }

        @Test
        public void InsertNewCustomerResultsInAccount() throws Exception
        {
            bankAccountController = new BankAccountController();
            bankAccountController.CustomerRepository = new CustomerRepositoryInDatabase();
            bankAccountController.AccountRepository = new AccountRepositoryInDatabase();
            bankAccountController.AuditLogRepository = new AuditLogRepositoryInDatabase();
            bankAccountController.Create("Joe2", "Blow2", 800);
            Account firstAccount = bankAccountController.AccountRepository.GetAll().get(bankAccountController.AccountRepository.GetAll().size()-1);
            Assert.assertEquals(new BigDecimal(0), firstAccount.Balance);
        }

        @Test
        public void DepositMoneyIntoAccount() throws Exception
        {
            bankAccountController = new BankAccountController();
            bankAccountController.CustomerRepository = new CustomerRepositoryInDatabase();
            bankAccountController.AccountRepository = new AccountRepositoryInDatabase();
            bankAccountController.AuditLogRepository = new AuditLogRepositoryInDatabase();
            bankAccountController.Create("Joe2", "Blow2", 800);
            Customer firstCustomer = bankAccountController.CustomerRepository.GetAll().get(bankAccountController.CustomerRepository.GetAll().size()-1);
            bankAccountController.Deposit(firstCustomer.id, new BigDecimal(600));
            Account firstAccount = bankAccountController.AccountRepository.GetAll().get(bankAccountController.AccountRepository.GetAll().size()-1);
            Assert.assertEquals(new BigDecimal(600), firstAccount.Balance);
        }

        @Test
        public void WithdrawlMoneyFromAccount() throws Exception
        {
            bankAccountController = new BankAccountController();
            bankAccountController.CustomerRepository = new CustomerRepositoryInDatabase();
            bankAccountController.AccountRepository = new AccountRepositoryInDatabase();
            bankAccountController.AuditLogRepository = new AuditLogRepositoryInDatabase();
            bankAccountController.Create("Joe2", "Blow2", 800);
            Customer firstCustomer = bankAccountController.CustomerRepository.GetAll().get(bankAccountController.CustomerRepository.GetAll().size()-1);
            bankAccountController.Deposit(firstCustomer.id, new BigDecimal(14000));
            bankAccountController.Withdraw(firstCustomer.id, new BigDecimal(500));
            Account firstAccount = bankAccountController.AccountRepository.GetAll().get(bankAccountController.AccountRepository.GetAll().size()-1);
            Assert.assertEquals(new BigDecimal(13500), firstAccount.Balance);
        }

        @Test
        public void LargeWithdrawResultsInAudit() throws Exception
        {
            bankAccountController = new BankAccountController();
            bankAccountController.CustomerRepository = new CustomerRepositoryInDatabase();
            bankAccountController.AccountRepository = new AccountRepositoryInDatabase();
            bankAccountController.AuditLogRepository = new AuditLogRepositoryInDatabase();
            bankAccountController.Create("Joe", "Blow", 800);
            Customer firstCustomer = bankAccountController.CustomerRepository.GetAll().get(bankAccountController.CustomerRepository.GetAll().size()-1);
            bankAccountController.Deposit(firstCustomer.id, new BigDecimal(30000));
            bankAccountController.Withdraw(firstCustomer.id, new BigDecimal(11000));
            bankAccountController.Withdraw(firstCustomer.id, new BigDecimal(10000));
            List<Audit> auditLog = bankAccountController.AuditLogRepository.GetAll();
            Assert.assertEquals(2, auditLog.size());
        }
}
