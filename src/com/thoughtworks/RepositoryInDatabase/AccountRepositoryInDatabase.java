package com.thoughtworks.RepositoryInDatabase;

import com.thoughtworks.bank.Account;
import com.thoughtworks.bank.Customer;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class AccountRepositoryInDatabase {
    public Account GetAccountByCustomer(Customer customer) throws Exception
        {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "SA", "");
            Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Accounts WHERE _customerId = " + customer.id + ";");
            resultSet.last();
            int id = resultSet.getInt("_id");
            BigDecimal balance = resultSet.getBigDecimal("_balance");
            statement.close();
            connection.close();
            Account account = new Account(customer, id, balance);
            return account;
        }

        public void UpdateAccountBalance(Account account) throws Exception
        {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "SA", "");
            Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            statement.executeQuery("UPDATE Accounts SET _balance=" + account.Balance + " WHERE _id = " + account.id);
            statement.close();
            connection.close();
        }

        public Account CreateAccount(Customer customer) throws Exception
        {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "SA", "");
            Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            statement.executeQuery("INSERT INTO Accounts (_customerId,_balance) VALUES (" + customer.id + ", 0)");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Accounts WHERE _customerId = " + customer.id + "");
            resultSet.last();
            int id = resultSet.getInt("_id");
            statement.close();
            connection.close();
            Account account = new Account(customer, id, new BigDecimal(0));
            return account;
        }

        public List<Account> GetAll() throws Exception
        {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "SA", "");
            Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Accounts");
            List<Account> accounts = new ArrayList<Account>();


            for (int i = 1; resultSet.absolute(i); i++)
            {
                int customerId = resultSet.getInt("_customerId");
                BigDecimal balance = resultSet.getBigDecimal("_balance");
                int id = resultSet.getInt("_id");
                Customer customer = CustomerRepositoryInDatabase.GetCustomerById(customerId);
                Account account = new Account(customer, id, balance);
                accounts.add(account);
            }

            statement.close();
            connection.close();
            return accounts;
        }
    
}
