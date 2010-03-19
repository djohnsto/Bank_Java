package com.thoughtworks.repositories;

import com.thoughtworks.bank.Account;
import com.thoughtworks.bank.Customer;

import java.util.List;

public interface AccountRepository{
    public Account GetAccountByCustomer(Customer customer) throws Exception;
    public void UpdateAccountBalance(Account account) throws Exception;
    public Account CreateAccount(Customer customer) throws Exception;
    public List<Account> GetAll() throws Exception;
}
