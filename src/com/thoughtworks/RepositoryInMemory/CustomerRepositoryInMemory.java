package com.thoughtworks.RepositoryInMemory;

import com.thoughtworks.bank.Customer;
import java.util.ArrayList;
import java.util.List;
public class CustomerRepositoryInMemory {
    public List<Customer> _customers = new ArrayList<Customer>();

        public Customer GetCustomerById(int id) throws Exception
        {
            for(int i=0; i<_customers.size(); i++){
                if(id==_customers.get(i).id){
                    return _customers.get(i);
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
