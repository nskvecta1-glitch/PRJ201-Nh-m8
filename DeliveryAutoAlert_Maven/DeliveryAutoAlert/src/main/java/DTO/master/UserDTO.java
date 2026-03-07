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
public class UserDTO {
    private int userID;
    private String userName;
    private String passwordhash;
    private String fullname;
    private String email;
    private int roleID;
    private boolean isActive;
    private LocalDateTime createdAt;

    public UserDTO(int userID, String userName, String passwordhash, String fullname, String email, int roleID, boolean isActive, LocalDateTime createdAt) {
        this.userID = userID;
        this.userName = userName;
        this.passwordhash = passwordhash;
        this.fullname = fullname;
        this.email = email;
        this.roleID = roleID;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
}
