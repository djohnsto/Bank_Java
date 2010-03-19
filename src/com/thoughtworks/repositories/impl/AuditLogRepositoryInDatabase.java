package com.thoughtworks.repositories.impl;

import com.thoughtworks.bank.Audit;
import com.thoughtworks.repositories.AuditLogRepository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class AuditLogRepositoryInDatabase implements AuditLogRepository {
    public void WriteEntries(List<Audit> auditLogToSave) throws Exception
    {
        Class.forName("org.hsqldb.jdbcDriver");
        Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "SA", "");
        Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        statement.executeQuery("INSERT INTO AuditLog (_accountID,_amount,_timeStamp) VALUES (" + auditLogToSave.get(auditLogToSave.size()-1).id + ", \'" +
            auditLogToSave.get(auditLogToSave.size()-1).amount + "\', \'" + new Timestamp(Calendar.getInstance().getTime().getTime()) + "\')");
        statement.close();
        connection.close();
    }

    public List<Audit> GetAll() throws Exception
    {
        Class.forName("org.hsqldb.jdbcDriver");
        Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "SA", "");
        Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM AuditLog");
        List<Audit> auditLogs = new ArrayList<Audit>();
        for (int i = 1; resultSet.absolute(i); i++)
        {
            BigDecimal amount = resultSet.getBigDecimal("_amount");
            int accountId = resultSet.getInt("_accountId");
            Audit audit = new Audit(accountId, amount);
            auditLogs.add(audit);
        }

        statement.close();
        connection.close();
        return auditLogs;
    }
}
