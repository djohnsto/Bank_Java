package com.thoughtworks.bank;

import com.thoughtworks.bank.Account.AccountOverdrawnException;
import com.thoughtworks.bank.Bank.BadCreditException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccountTest {

        @Test
        public void CreatingCustomerMatchesFirstName()
        {
            Customer customer = new Customer(1,"Milton", "Waddams", 720);
            Assert.assertEquals("Milton",customer.firstName);
        }

        @Test
        public void CreatingCustomerMatchesLastName()
        {
            Customer customer = new Customer(1,"Milton", "Waddams", 720);
            Assert.assertEquals("Waddams",customer.lastName);
        }

        @Test
        public void CreatingCustomerMatchesFicoScore()
        {
            Customer customer = new Customer(1,"Milton", "Waddams", 720);
            Assert.assertEquals(720,customer.ficoScore);
        }


        @Test
        public void GoodFicoScoreCreditCheck()
        {
            Customer customer = new Customer(1, "Milton", "Waddams", 720);
            Bank bank = new Bank("Initech Bank", 680);
            Assert.assertEquals(true, bank.CheckCredit(customer.ficoScore));
        }

        @Test
        public void BadFicoScoreCreditCheck()
        {
            Customer customer = new Customer(1, "Peter", "Gibbons", 600);
            Bank bank = new Bank("Initech Bank", 680);
            Assert.assertEquals(false, bank.CheckCredit(customer.ficoScore));
        }

       @Test
        public void OpeningAccountWithBadCreditThrowsException() throws BadCreditException
        {
            Customer customer = new Customer(1, "Peter", "Gibbons", 600);
            Bank bank = new Bank("Initech Bank", 680);
            try{
                bank.OpenAccount(customer);
                Assert.fail("Should have Bad Credit Exception");
            }
            catch(BadCreditException expected){}
        }

        @Test
        public void OpeningAccountWithGoodCreditResultsInAccount() throws BadCreditException
        {
            Customer customer = new Customer(1, "Milton", "Waddams", 720);
            Bank bank = new Bank("Initech Bank", 680);
            Account newAccount = bank.OpenAccount(customer);
            Assert.assertEquals(customer, newAccount.holder);
        }


        @Test
        public void DepositCashIntoAccountWorks() throws BadCreditException
        {
            Customer customer = new Customer(1, "Milton", "Waddams", 720);
            Bank bank = new Bank("Initech Bank", 680);
            Account account = bank.OpenAccount(customer);
            account.Deposit(new BigDecimal(42));
            account.Deposit(new BigDecimal(37));
            Assert.assertEquals(new BigDecimal(79), account.Balance);
        }

        @Test
        public void WithdrawCashFromAccountWorks() throws BadCreditException, AccountOverdrawnException
        {
            Customer customer = new Customer(1, "Milton", "Waddams", 720);
            Bank bank = new Bank("Initech Bank", 680);
            Account account = bank.OpenAccount(customer);
            List<Audit> auditlog = new ArrayList();
            account.Deposit(new BigDecimal(50));
            account.Withdraw(new BigDecimal(10), auditlog);
            Assert.assertEquals(new BigDecimal(40), account.Balance);
        }

        @Test
        public void OverdrawingAccountThrowsException() throws BadCreditException, AccountOverdrawnException
        {
            Customer customer = new Customer(1, "Milton", "Waddams", 720);
            Bank bank = new Bank("Initech Bank", 680);
            Account account = bank.OpenAccount(customer);
            List<Audit> auditlog = new ArrayList();
            try{
                account.Withdraw(new BigDecimal(10), auditlog);
                Assert.fail("Should have Account Overdrawn Exception");
            }
            catch(AccountOverdrawnException expected){}
        }

        @Test
        public void WithdrawingOver10KCreatesAnAuditEntry() throws BadCreditException, AccountOverdrawnException
        {
            Customer customer = new Customer(1, "Milton", "Waddams", 720);
            Bank bank = new Bank("Initech Bank", 680);
            Account account = bank.OpenAccount(customer);
            List<Audit> auditlog = new ArrayList();
            account.Deposit(new BigDecimal(10000));
            account.Withdraw(new BigDecimal(10000), auditlog);
            Assert.assertEquals(1, auditlog.size());

        } 
}