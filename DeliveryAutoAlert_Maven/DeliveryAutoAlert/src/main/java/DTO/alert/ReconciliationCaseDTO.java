package DTO.alert;

import java.time.LocalDateTime;

public class ReconciliationCaseDTO {
    private int caseId;
    private int alertId;
    private String caseStatus;
    private int assignedTo;
    private LocalDateTime openedAt;
    private LocalDateTime closedAt;
    private String resolutionNote;

    public ReconciliationCaseDTO() {}
    public ReconciliationCaseDTO(int caseId, int alertId, String caseStatus, int assignedTo, LocalDateTime openedAt, LocalDateTime closedAt, String resolutionNote) {
        this.caseId = caseId; this.alertId = alertId; this.caseStatus = caseStatus;
        this.assignedTo = assignedTo; this.openedAt = openedAt; this.closedAt = closedAt; this.resolutionNote = resolutionNote;
    }
    public int getCaseId() { return caseId; }
    public void setCaseId(int caseId) { this.caseId = caseId; }
    public int getAlertId() { return alertId; }
    public void setAlertId(int alertId) { this.alertId = alertId; }
    public String getCaseStatus() { return caseStatus; }
    public void setCaseStatus(String caseStatus) { this.caseStatus = caseStatus; }
    public int getAssignedTo() { return assignedTo; }
    public void setAssignedTo(int assignedTo) { this.assignedTo = assignedTo; }
    public LocalDateTime getOpenedAt() { return openedAt; }
    public void setOpenedAt(LocalDateTime openedAt) { this.openedAt = openedAt; }
    public LocalDateTime getClosedAt() { return closedAt; }
    public void setClosedAt(LocalDateTime closedAt) { this.closedAt = closedAt; }
    public String getResolutionNote() { return resolutionNote; }
    public void setResolutionNote(String resolutionNote) { this.resolutionNote = resolutionNote; }
}
