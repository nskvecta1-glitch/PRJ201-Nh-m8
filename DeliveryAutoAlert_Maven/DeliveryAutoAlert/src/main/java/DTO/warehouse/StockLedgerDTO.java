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
public class StockLedgerDTO {
    private int ledgerId;
    private int productId;
    private int warehouseId;
    private int changeQty;
    private String refType;
    private int refId;
    private LocalDateTime createdAt;

    public StockLedgerDTO(int ledgerId, int productId, int warehouseId, int changeQty, String refType, int refId, LocalDateTime createdAt) {
        this.ledgerId = ledgerId;
        this.productId = productId;
        this.warehouseId = warehouseId;
        this.changeQty = changeQty;
        this.refType = refType;
        this.refId = refId;
        this.createdAt = createdAt;
    }

    public int getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(int ledgerId) {
        this.ledgerId = ledgerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getChangeQty() {
        return changeQty;
    }

    public void setChangeQty(int changeQty) {
        this.changeQty = changeQty;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public int getRefId() {
        return refId;
    }

    public void setRefId(int refId) {
        this.refId = refId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    
}
