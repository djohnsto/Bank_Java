package com.thoughtworks.repositories;
import com.thoughtworks.bank.Audit;

import java.util.List;

public interface AuditLogRepository {
    public void WriteEntries(List<Audit> auditLogToSave) throws Exception;
    public List<Audit> GetAll() throws Exception;
}