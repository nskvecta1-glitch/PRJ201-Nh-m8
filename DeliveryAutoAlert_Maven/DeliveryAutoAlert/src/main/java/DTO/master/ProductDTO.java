/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.master;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Hoang Duc
 */
public class ProductDTO {
    private int productId;
    private String sku;
    private String productName;
    private BigDecimal price;
    private LocalDateTime createdAt;

    public ProductDTO(int productId, String sku, String productName, BigDecimal price, LocalDateTime createdAt) {
        this.productId = productId;
        this.sku = sku;
        this.productName = productName;
        this.price = price;
        this.createdAt = createdAt;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    
}
