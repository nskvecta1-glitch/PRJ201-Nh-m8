/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.devlivery;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Hoang Duc
 */
public class DeliveryOrderDTO {
    private int orderId;
    private String orderCode;
    private int customerId;
    private int warehouseId;
    private LocalDateTime orderDate;
    private String status;
    private boolean hasCod;
    private BigDecimal codAmount;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;

    public DeliveryOrderDTO(int orderId, String orderCode, int customerId, int warehouseId, LocalDateTime orderDate, String status, boolean hasCod, BigDecimal codAmount, BigDecimal totalAmount, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.orderCode = orderCode;
        this.customerId = customerId;
        this.warehouseId = warehouseId;
        this.orderDate = orderDate;
        this.status = status;
        this.hasCod = hasCod;
        this.codAmount = codAmount;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isHasCod() {
        return hasCod;
    }

    public void setHasCod(boolean hasCod) {
        this.hasCod = hasCod;
    }

    public BigDecimal getCodAmount() {
        return codAmount;
    }

    public void setCodAmount(BigDecimal codAmount) {
        this.codAmount = codAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    
}
