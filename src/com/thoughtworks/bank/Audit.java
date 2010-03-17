package com.thoughtworks.bank;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Mar 16, 2010
 * Time: 1:06:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Audit {
    public BigDecimal amount;
    public int id;

    public Audit(int Id, BigDecimal Amount)
    {
        id = Id;
        amount = Amount;
    }
}
