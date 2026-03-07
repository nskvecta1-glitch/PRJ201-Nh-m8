package DTO.devlivery;

import java.time.LocalDateTime;

public class ShipmentDTO {
    private int shipmentId;
    private int orderId;
    private LocalDateTime shipDate;
    private String deliveryStatus;
    private String route;

    public ShipmentDTO() {}

    public ShipmentDTO(int shipmentId, int orderId, LocalDateTime shipDate, String deliveryStatus, String route) {
        this.shipmentId = shipmentId;
        this.orderId = orderId;
        this.shipDate = shipDate;
        this.deliveryStatus = deliveryStatus;
        this.route = route;
    }

    public int getShipmentId() { return shipmentId; }
    public void setShipmentId(int shipmentId) { this.shipmentId = shipmentId; }
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public LocalDateTime getShipDate() { return shipDate; }
    public void setShipDate(LocalDateTime shipDate) { this.shipDate = shipDate; }
    public String getDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(String deliveryStatus) { this.deliveryStatus = deliveryStatus; }
    public String getRoute() { return route; }
    public void setRoute(String route) { this.route = route; }
}
