package com.thoughtworks.bank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Bank {
        String bankName;
        int minimumCreditScore;
        List<Account> accounts = new ArrayList<Account>();

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


