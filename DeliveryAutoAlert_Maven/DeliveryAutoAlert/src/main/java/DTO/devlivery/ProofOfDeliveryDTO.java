/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.devlivery;

import java.time.LocalDateTime;

/**
 *
 * @author Hoang Duc
 */
public class ProofOfDeliveryDTO {
    private int podId;
    private int shipmentId;
    private LocalDateTime deliveredAt;
    private String receiverName;
    private String podImageUrl;

    public ProofOfDeliveryDTO(int podId, int shipmentId, LocalDateTime deliveredAt, String receiverName, String podImageUrl) {
        this.podId = podId;
        this.shipmentId = shipmentId;
        this.deliveredAt = deliveredAt;
        this.receiverName = receiverName;
        this.podImageUrl = podImageUrl;
    }

    public int getPodId() {
        return podId;
    }

    public void setPodId(int podId) {
        this.podId = podId;
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getPodImageUrl() {
        return podImageUrl;
    }

    public void setPodImageUrl(String podImageUrl) {
        this.podImageUrl = podImageUrl;
    }
    
    
}
