package service;
import DAO.AlertRuleDAO;
import DAO.AlertEventDAO;
import DAO.ReconciliationCaseDAO;
import DAO.AlertActionDAO;
import DTO.alert.*;
import java.util.List;
public class AlertService {
    private final AlertRuleDAO ruleDAO = new AlertRuleDAO();
    private final AlertEventDAO eventDAO = new AlertEventDAO();
    private final ReconciliationCaseDAO caseDAO = new ReconciliationCaseDAO();
    private final AlertActionDAO actionDAO = new AlertActionDAO();
    public List<AlertRuleDTO> getAllRules() throws Exception { return ruleDAO.getAll(); }
    public void saveRule(AlertRuleDTO r) throws Exception { ruleDAO.insert(r); }
    public List<AlertEventDTO> getAllEvents() throws Exception { return eventDAO.getAll(); }
    public AlertEventDTO getEventById(int id) throws Exception { return eventDAO.getById(id); }
    public List<AlertEventDTO> getEventsByStatus(String status) throws Exception { return eventDAO.getByStatus(status); }
    public void saveEvent(AlertEventDTO e) throws Exception { eventDAO.insert(e); }
    public void updateEventStatus(int alertId, String status) throws Exception { eventDAO.updateStatus(alertId, status); }
    public List<ReconciliationCaseDTO> getAllCases() throws Exception { return caseDAO.getAll(); }
    public ReconciliationCaseDTO getCaseById(int id) throws Exception { return caseDAO.getById(id); }
    public void saveCase(ReconciliationCaseDTO c) throws Exception {
        if (c.getCaseId() == 0) caseDAO.insert(c); else caseDAO.update(c);
    }
    public List<AlertActionDTO> getActionsByCase(int caseId) throws Exception { return actionDAO.getByCaseId(caseId); }
    public void addAction(AlertActionDTO a) throws Exception { actionDAO.insert(a); }
}
