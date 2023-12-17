/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.mmm.v4.client.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author laura
 */
public class Operations {
    private Integer         operationId;
    private Users           user;
    private Date            date;
    private Categories      category;
    private double          amount;

    // Constructors, getters, setters, hashCode, equals, and toString methods

    // ------------------------------------------------
    // ---------------- Constructors ------------------
    // ------------------------------------------------
    
    public Operations() {
    }

    public Operations(Integer operationId) {
        this.operationId = operationId;
    }

    public Operations(Integer operationId, Users user, Date date, Categories category, double amount) {
        this.operationId =  operationId;
        this.user =         user;
        this.date =         date;
        this.category =     category;
        this.amount =       amount;
    }

    // ------------------------------------------------
    // ---------------- Getters and Setters -----------
    // ------------------------------------------------
    
    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
