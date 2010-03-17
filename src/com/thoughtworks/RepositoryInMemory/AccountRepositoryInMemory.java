package com.thoughtworks.RepositoryInMemory;

import com.thoughtworks.bank.Account;
import com.thoughtworks.bank.Customer;
import java.util.Random;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryInMemory {
    public List<Account> _accounts = new ArrayList<Account>();


        public Account GetAccountByCustomer(Customer customer) throws Exception
        {
            for(int i=0; i<_accounts.size(); i++){
                if(customer==_accounts.get(i).holder){
                    return _accounts.get(i);
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
