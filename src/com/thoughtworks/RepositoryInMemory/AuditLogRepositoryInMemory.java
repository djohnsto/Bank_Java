package com.thoughtworks.RepositoryInMemory;

import com.thoughtworks.bank.Audit;

import java.util.ArrayList;
import java.util.List;
public class AuditLogRepositoryInMemory {
    public List<Audit> _auditLog = new ArrayList<Audit>();

    public void WriteEntries(List<Audit> auditLogToSave)
    {
        for(int i=0; i<auditLogToSave.size(); i++){
            _auditLog.add(auditLogToSave.get(i));
        }
    }

    public List<Audit> GetAll()
    {
        return _auditLog;
    }
}
