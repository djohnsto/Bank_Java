package src.com.thoughtworks.RepositoryInMemory;

import src.com.thoughtworks.bank.Account;
import src.com.thoughtworks.bank.Customer;
import java.util.Random;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Mar 16, 2010
 * Time: 2:02:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountRepositoryInMemory {
    public List<Account> _accounts = new ArrayList();


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
        public void UpdateAccountBalance(Account account){
            return;
        }
}
