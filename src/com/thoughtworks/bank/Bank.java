package com.thoughtworks.bank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Mar 15, 2010
 * Time: 5:18:52 PM
 * To change this template use File | Settings | File Templates.
 */

public class Bank {
        public String bankName;
        public int minimumCreditScore;
        public List accounts = new ArrayList();

        public Bank(String BankName, int MinimumCreditScore)
        {
            bankName = BankName;
            minimumCreditScore = MinimumCreditScore;
        }

        public boolean CheckCredit(int ficoScore)
        {
            return ficoScore >= minimumCreditScore;
        }

        public Account OpenAccount(Customer customer) throws BadCreditException
        {
            if(!CheckCredit(customer.ficoScore))
            {
                throw new BadCreditException();
            }

            Account account = new Account(customer, new Random().nextInt(), new BigDecimal(0));
            accounts.add(account);
            return account;
        }

        public Account OpenAccount(Account account)
        {
            accounts.add(account);
            return account;
        }

    public class BadCreditException extends Exception{}
}


