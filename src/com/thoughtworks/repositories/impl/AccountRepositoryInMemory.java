package com.thoughtworks.repositories.impl;

import com.thoughtworks.bank.Account;
import com.thoughtworks.bank.Customer;
import com.thoughtworks.repositories.AccountRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AccountRepositoryInMemory implements AccountRepository {
    private List<Account> _accounts = new ArrayList<Account>();


        public Account GetAccountByCustomer(Customer customer) throws Exception
        {
            for (Account _account : _accounts) {
                if (customer == _account.holder) {
                    return _account;
                }
            }
            throw new Exception();
        }

        public Account CreateAccount(Customer customer)
        {
            Account accountToSave = new Account(customer, new Random().nextInt(), new BigDecimal(0));
            _accounts.add(accountToSave);
            return accountToSave;
        }

        public List<Account> GetAll()
        {
            return _accounts;
        }
        public void UpdateAccountBalance(Account account){}
}
