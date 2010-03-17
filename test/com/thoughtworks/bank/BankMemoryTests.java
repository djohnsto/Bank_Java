package com.thoughtworks.bank;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Mar 16, 2010
 * Time: 1:24:36 PM
 * To change this template use File | Settings | File Templates.
 */
/*public class BankControllerTests {
    private BankAccountController bankAccountController;

    @Test
    public void CanCreateBankAccountController()
    {
        BankAccountController bankAccountController = new BankAccountController();
    }

    @Test
    public void CreateAccountResultsInHavingANewCustomer() throws Exception
    {
        bankAccountController = new BankAccountController();
        bankAccountController.CustomerRepository = new CustomerRepositoryInMemory();
        bankAccountController.AccountRepository = new AccountRepositoryInMemory();
        bankAccountController.AuditLogRepository = new AuditLogRepositoryInMemory();
        bankAccountController.Create("Joe", "Blow", 800);
        Customer firstCustomer = bankAccountController.CustomerRepository.GetAll().get(bankAccountController.CustomerRepository.GetAll().size()-1);
        Assert.assertEquals("Joe", firstCustomer.firstName);
        Assert.assertEquals("Blow", firstCustomer.lastName);
    }

    @Test
    public void CreateAccountResultsInHavingANewAccount()
    {
        BankAccountController bankAccountController = new BankAccountController();
        bankAccountController.CustomerRepository = new CustomerRepositoryInMemory();
        bankAccountController.AccountRepository = new AccountRepositoryInMemory();
        bankAccountController.AuditLogRepository = new AuditLogRepositoryInMemory();
        bankAccountController.Create("Joe", "Blow", 800);
        Account firstAccount = bankAccountController.AccountRepository.GetAll().get(bankAccountController.AccountRepository.GetAll().size()-1);
        Assert.assertEquals("Joe", firstAccount.holder.firstName);
        Assert.assertEquals("Blow", firstAccount.holder.lastName);
    }

    @Test
    public void DepositResultsInHavingMoney() throws Exception
    {
        BankAccountController bankAccountController = new BankAccountController();
        bankAccountController.CustomerRepository = new CustomerRepositoryInMemory();
        bankAccountController.AccountRepository = new AccountRepositoryInMemory();
        bankAccountController.AuditLogRepository = new AuditLogRepositoryInMemory();
        bankAccountController.Create("Joe", "Blow", 800);
        Customer firstCustomer = bankAccountController.CustomerRepository.GetAll().get(bankAccountController.CustomerRepository.GetAll().size()-1);
        bankAccountController.Deposit(firstCustomer.id, new BigDecimal(500));
        Account firstAccount = bankAccountController.AccountRepository.GetAll().get(bankAccountController.AccountRepository.GetAll().size()-1);
        Assert.assertEquals(new BigDecimal(500), firstAccount.Balance);
    }


    @Test
    public void WithdrawResultsInLosingMoney() throws Exception
    {
        BankAccountController bankAccountController = new BankAccountController();
        bankAccountController.CustomerRepository = new CustomerRepositoryInMemory();
        bankAccountController.AccountRepository = new AccountRepositoryInMemory();
        bankAccountController.AuditLogRepository = new AuditLogRepositoryInMemory();
        bankAccountController.Create("Joe", "Blow", 800);
        Customer firstCustomer = bankAccountController.CustomerRepository.GetAll().get(bankAccountController.CustomerRepository.GetAll().size()-1);
        bankAccountController.Deposit(firstCustomer.id, new BigDecimal(500));
        bankAccountController.Withdraw(firstCustomer.id, new BigDecimal(200));
        Account firstAccount = bankAccountController.AccountRepository.GetAll().get(bankAccountController.AccountRepository.GetAll().size()-1);
        Assert.assertEquals(new BigDecimal(300), firstAccount.Balance);
    }

    @Test
    public void LargeWithdrawResultsInAudit() throws Exception
    {
        BankAccountController bankAccountController = new BankAccountController();
        bankAccountController.CustomerRepository = new CustomerRepositoryInMemory();
        bankAccountController.AccountRepository = new AccountRepositoryInMemory();
        bankAccountController.AuditLogRepository = new AuditLogRepositoryInMemory();
        bankAccountController.Create("Joe", "Blow", 800);
        Customer firstCustomer = bankAccountController.CustomerRepository.GetAll().get(bankAccountController.CustomerRepository.GetAll().size()-1);
        bankAccountController.Deposit(firstCustomer.id, new BigDecimal(30000));
        bankAccountController.Withdraw(firstCustomer.id, new BigDecimal(11000));
        bankAccountController.Withdraw(firstCustomer.id, new BigDecimal(10000));
        List<Audit> auditLog = bankAccountController.AuditLogRepository.GetAll();
        Assert.assertEquals(2, auditLog.size());
    }
}
*/