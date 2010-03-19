package com.thoughtworks.bank;

import com.thoughtworks.controllers.BankAccountController;
import com.thoughtworks.repositories.impl.AccountRepositoryInDatabase;
import com.thoughtworks.repositories.impl.AuditLogRepositoryInDatabase;
import com.thoughtworks.repositories.impl.CustomerRepositoryInDatabase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class BankDatabaseTests {
        private BankAccountController bankAccountController;

        @Before
        public void setUp() throws Exception{
            bankAccountController = new BankAccountController();
            bankAccountController.setCustomerRepository(new CustomerRepositoryInDatabase());
            bankAccountController.setAccountRepository(new AccountRepositoryInDatabase());
            bankAccountController.setAuditLogRepository(new AuditLogRepositoryInDatabase());

            Class.forName("org.hsqldb.jdbcDriver");
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "SA", "");
            Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            try{
                statement.executeQuery("SELECT * FROM Accounts");
            }
            catch (Exception exception) {
                statement.executeQuery("CREATE TABLE Customer (_id INT IDENTITY, _ficoScore INT, _lastName VARCHAR(255), _firstName VARCHAR(255));");
                statement.executeQuery("CREATE TABLE Accounts (_id INT IDENTITY, _customerId INT, _balance DECIMAL);");
                statement.executeQuery("CREATE TABLE AuditLog (_accountId INT, _amount DECIMAL, _timeStamp TIMESTAMP);");
            }
            statement.close();
            connection.close();
        }

        @Test
        public void InsertNewCustomer() throws Exception
        {
            bankAccountController.Create("Joe3", "Blow3", 800);
            Customer firstCustomer = bankAccountController.CustomerRepository.GetAll().get(bankAccountController.CustomerRepository.GetAll().size()-1);
            Assert.assertEquals("Joe3", firstCustomer.firstName);
            Assert.assertEquals("Blow3", firstCustomer.lastName);
        }

        @Test
        public void InsertNewCustomerResultsInAccount() throws Exception
        {
            bankAccountController.Create("Joe2", "Blow2", 800);
            Account firstAccount = bankAccountController.AccountRepository.GetAll().get(bankAccountController.AccountRepository.GetAll().size()-1);
            Assert.assertEquals(new BigDecimal(0), firstAccount.Balance);
        }

        @Test
        public void DepositMoneyIntoAccount() throws Exception
        {
            bankAccountController.Create("Joe2", "Blow2", 800);
            Customer firstCustomer = bankAccountController.CustomerRepository.GetAll().get(bankAccountController.CustomerRepository.GetAll().size()-1);
            bankAccountController.Deposit(firstCustomer.id, new BigDecimal(600));
            Account firstAccount = bankAccountController.AccountRepository.GetAll().get(bankAccountController.AccountRepository.GetAll().size()-1);
            Assert.assertEquals(new BigDecimal(600), firstAccount.Balance);
        }

        @Test
        public void WithdrawalMoneyFromAccount() throws Exception
        {
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
            bankAccountController.Create("Joe", "Blow", 800);
            Customer firstCustomer = bankAccountController.CustomerRepository.GetAll().get(bankAccountController.CustomerRepository.GetAll().size()-1);
            bankAccountController.Deposit(firstCustomer.id, new BigDecimal(30000));
            bankAccountController.Withdraw(firstCustomer.id, new BigDecimal(11000));
            bankAccountController.Withdraw(firstCustomer.id, new BigDecimal(10000));
            List<Audit> auditLog = bankAccountController.AuditLogRepository.GetAll();
            Assert.assertEquals(2, auditLog.size());
        }
}
