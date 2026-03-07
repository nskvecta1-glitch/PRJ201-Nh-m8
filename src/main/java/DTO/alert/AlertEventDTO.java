package DTO.alert;

import java.time.LocalDateTime;

public class AlertEventDTO {
    private int alertId;
    private int ruleId;
    private String refType;
    private int refId;
    private double riskScore;
    private LocalDateTime createdAt;
    private String status;

    public AlertEventDTO() {}
    public AlertEventDTO(int alertId, int ruleId, String refType, int refId, double riskScore, LocalDateTime createdAt, String status) {
        this.alertId = alertId; this.ruleId = ruleId; this.refType = refType;
        this.refId = refId; this.riskScore = riskScore; this.createdAt = createdAt; this.status = status;
    }
    public int getAlertId() { return alertId; }
    public void setAlertId(int alertId) { this.alertId = alertId; }
    public int getRuleId() { return ruleId; }
    public void setRuleId(int ruleId) { this.ruleId = ruleId; }
    public String getRefType() { return refType; }
    public void setRefType(String refType) { this.refType = refType; }
    public int getRefId() { return refId; }
    public void setRefId(int refId) { this.refId = refId; }
    public double getRiskScore() { return riskScore; }
    public void setRiskScore(double riskScore) { this.riskScore = riskScore; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
