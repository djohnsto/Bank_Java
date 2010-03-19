package com.thoughtworks.repositories.impl;

import com.thoughtworks.bank.Audit;
import com.thoughtworks.repositories.AuditLogRepository;

import java.util.ArrayList;
import java.util.List;
public class AuditLogRepositoryInMemory implements AuditLogRepository {
    private List<Audit> _auditLog = new ArrayList<Audit>();

    public void WriteEntries(List<Audit> auditLogToSave)
    {
        for (Audit anAuditLogToSave : auditLogToSave) {
            _auditLog.add(anAuditLogToSave);
        }
    }

    public List<Audit> GetAll()
    {
        return _auditLog;
    }
}
