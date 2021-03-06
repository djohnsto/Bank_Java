package com.thoughtworks.bank;

import java.math.BigDecimal;
import java.util.List;
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

        public void Withdraw(BigDecimal amount, List<Audit> auditLog) throws AccountOverdrawnException
        {
            Balance=Balance.subtract(amount);
            if(Balance.intValue() < 0)
            {
                throw new AccountOverdrawnException();
            }

            if(amount.intValue()>=10000)
            {
                Audit audit = new Audit(id, amount);
                auditLog.add(audit);
            }
        }
        public class AccountOverdrawnException extends Exception{}
    }
