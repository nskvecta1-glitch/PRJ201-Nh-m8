/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.master;

import java.time.LocalDateTime;

/**
 *
 * @author Hoang Duc
 */
public class WarehouseDTO {
    private int warehouseId;
    private String warehouseName;
    private String location;
    private LocalDateTime createdAt;

    public WarehouseDTO(int warehouseId, String warehouseName, String location, LocalDateTime createdAt) {
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.location = location;
        this.createdAt = createdAt;
    }

    
    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    
}
