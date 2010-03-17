package com.thoughtworks.bank;
import java.math.BigDecimal;
public class Audit {
    public BigDecimal amount;
    public int id;

    public Audit(int Id, BigDecimal Amount)
    {
        id = Id;
        amount = Amount;
    }
}
