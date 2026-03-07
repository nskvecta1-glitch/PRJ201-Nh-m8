/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.warehouse;

import java.time.LocalDateTime;

/**
 *
 * @author Hoang Duc
 */
public class InboundDocDTO {
    private int inboundId;
    private int refOrderId;
    private int warehouseId;
    private LocalDateTime inboundDate;
    private String reason;

    public InboundDocDTO(int inboundId, int refOrderId, int warehouseId, LocalDateTime inboundDate, String reason) {
        this.inboundId = inboundId;
        this.refOrderId = refOrderId;
        this.warehouseId = warehouseId;
        this.inboundDate = inboundDate;
        this.reason = reason;
    }

    public int getInboundId() {
        return inboundId;
    }

    public void setInboundId(int inboundId) {
        this.inboundId = inboundId;
    }

    public int getRefOrderId() {
        return refOrderId;
    }

    public void setRefOrderId(int refOrderId) {
        this.refOrderId = refOrderId;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public LocalDateTime getInboundDate() {
        return inboundDate;
    }

    public void setInboundDate(LocalDateTime inboundDate) {
        this.inboundDate = inboundDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
}
