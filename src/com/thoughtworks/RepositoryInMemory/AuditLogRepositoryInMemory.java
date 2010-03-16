package src.com.thoughtworks.RepositoryInMemory;

import src.com.thoughtworks.bank.Audit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Mar 16, 2010
 * Time: 2:02:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class AuditLogRepositoryInMemory {
    public List<Audit> _auditLog = new ArrayList();

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
