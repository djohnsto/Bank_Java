package src.com.thoughtworks.bank;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Mar 15, 2010
 * Time: 4:47:56 PM
 * To change this template use File | Settings | File Templates.
 */

public class Account {
        public Customer holder;
        public int id;
        public BigDecimal Balance;

        public Account(Customer customer, int Id, BigDecimal balance)
        {
            holder = customer;
            id = Id;
            Balance = balance;
        }


        public void Deposit(BigDecimal amount)
        {
            Balance=Balance.add(amount);
        }

        public void Withdraw(BigDecimal amount, List<Audit> auditlog) throws AccountOverdrawnException
        {
            Balance=Balance.subtract(amount);
            if(Balance.intValue() < 0)
            {
                throw new AccountOverdrawnException();
            }

            if(amount.intValue()>=10000)
            {
                Audit audit = new Audit(id, amount);
                auditlog.add(audit);
            }
        }
        public class AccountOverdrawnException extends Exception{}
    }
