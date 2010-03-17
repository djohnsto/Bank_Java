package com.thoughtworks.RepositoryInDatabase;

import com.thoughtworks.bank.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Mar 17, 2010
 * Time: 9:52:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class CustomerRepositoryInDatabase {
        public static Customer GetCustomerById(int id) throws Exception
        {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "SA", "");
            Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Customer WHERE _id = " + id);
            resultSet.absolute(1);
            int ficoScore = resultSet.getInt("_ficoScore");
            String firstName = resultSet.getString("_firstName");
            String lastName = resultSet.getString("_lastName");
            statement.close();
            connection.close();
            return new Customer(id, firstName, lastName, ficoScore);
        }

        public Customer CreateCustomer(int ficoScore, String lastName, String firstName) throws Exception
        {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "SA", "");
            Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            statement.executeQuery("INSERT INTO Customer (_ficoScore,_lastName,_firstName) VALUES (" + ficoScore + ", \'" +
                lastName + "\', \'" + firstName + "\')");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Customer WHERE _lastName = \'" + lastName + "\'");
            resultSet.last();
            int id = resultSet.getInt("_id");
            statement.close();
            connection.close();
            Customer customer = new Customer(id, firstName, lastName, ficoScore);
            return customer;
        }

        public List<Customer> GetAll() throws Exception
        {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "SA", "");
            Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Customer");
            List<Customer> customers = new ArrayList();
            for (int i = 1; resultSet.absolute(i);i++ )
            {
                int ficoScore = resultSet.getInt("_ficoScore");
                String firstName = resultSet.getString("_firstName");
                String lastName = resultSet.getString("_lastName");
                int id = resultSet.getInt("_id");
                Customer customer = new Customer(id, firstName, lastName, ficoScore);
                customers.add(customer);
            }
            statement.close();
            connection.close();
            return customers;
        }
}
