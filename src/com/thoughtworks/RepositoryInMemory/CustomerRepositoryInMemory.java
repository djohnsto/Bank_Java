package src.com.thoughtworks.RepositoryInMemory;

import src.com.thoughtworks.bank.Customer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Mar 16, 2010
 * Time: 2:02:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomerRepositoryInMemory {
    public List<Customer> _customers = new ArrayList();

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
