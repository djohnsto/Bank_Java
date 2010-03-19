package com.thoughtworks.repositories.impl;

import com.thoughtworks.bank.Customer;
import com.thoughtworks.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryInMemory implements CustomerRepository {
    private List<Customer> _customers = new ArrayList<Customer>();

        public Customer GetCustomerById(int id) throws Exception
        {
            for (Customer _customer : _customers) {
                if (id == _customer.id) {
                    return _customer;
                }
            }
            throw new Exception();
        }

        public Customer CreateCustomer(int ficoScore, String lastName, String firstName)
        {
            Customer customer = new Customer(1, firstName,lastName, ficoScore);
            _customers.add(customer);
            return customer;
        }
        public List<Customer> GetAll()
        {
            return _customers;
        }
}
