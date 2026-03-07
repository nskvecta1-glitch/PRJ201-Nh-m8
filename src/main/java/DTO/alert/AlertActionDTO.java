package DTO.alert;

import java.time.LocalDateTime;

public class AlertActionDTO {
    private int actionId;
    private int caseId;
    private int actionBy;
    private String actionNote;
    private LocalDateTime actionTime;

    public AlertActionDTO() {}
    public AlertActionDTO(int actionId, int caseId, int actionBy, String actionNote, LocalDateTime actionTime) {
        this.actionId = actionId; this.caseId = caseId; this.actionBy = actionBy;
        this.actionNote = actionNote; this.actionTime = actionTime;
    }
    public int getActionId() { return actionId; }
    public void setActionId(int actionId) { this.actionId = actionId; }
    public int getCaseId() { return caseId; }
    public void setCaseId(int caseId) { this.caseId = caseId; }
    public int getActionBy() { return actionBy; }
    public void setActionBy(int actionBy) { this.actionBy = actionBy; }
    public String getActionNote() { return actionNote; }
    public void setActionNote(String actionNote) { this.actionNote = actionNote; }
    public LocalDateTime getActionTime() { return actionTime; }
    public void setActionTime(LocalDateTime actionTime) { this.actionTime = actionTime; }
}
