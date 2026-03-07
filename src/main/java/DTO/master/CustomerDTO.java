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
public class CustomerDTO {
    private int customerID;
    private String customerName;
    private String phone;
    private String address;
    private String email;
    private LocalDateTime createdAt;

    public CustomerDTO(int customerID, String customerName, String phone, String address, String email, LocalDateTime createdAt) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.createdAt = createdAt;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    
}
