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
public class OutboundDocDTO {
    private int outboundId;
    private int refOrderId;
    private int warehouseId;
    private LocalDateTime outboundDate;
    private String status;

    public OutboundDocDTO(int outboundId, int refOrderId, int warehouseId, LocalDateTime outboundDate, String status) {
        this.outboundId = outboundId;
        this.refOrderId = refOrderId;
        this.warehouseId = warehouseId;
        this.outboundDate = outboundDate;
        this.status = status;
    }
    
    

    public int getOutboundId() {
        return outboundId;
    }

    public void setOutboundId(int outboundId) {
        this.outboundId = outboundId;
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

    public LocalDateTime getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(LocalDateTime outboundDate) {
        this.outboundDate = outboundDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
