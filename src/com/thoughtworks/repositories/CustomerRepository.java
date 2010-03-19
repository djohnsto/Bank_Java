package com.thoughtworks.repositories;

import com.thoughtworks.bank.Customer;

import java.util.List;

public interface CustomerRepository{
    public Customer GetCustomerById(int id) throws Exception;
    public Customer CreateCustomer(int ficoScore, String lastName, String firstName) throws Exception;
    public List<Customer> GetAll() throws Exception;
}
