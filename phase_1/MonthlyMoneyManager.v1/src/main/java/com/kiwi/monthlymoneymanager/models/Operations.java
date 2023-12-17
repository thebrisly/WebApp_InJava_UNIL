/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.monthlymoneymanager.models;

/**
 *
 * @author lsierro, lfabbian
 */


public class Operations {
    private int id;
    private Category category;
    private int amount;
    private String description;

    public Operations(int id, Category category, int amount, String description) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    // Getter and Setter methods for each property
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction [ID: " + id + ", Category: " + category.getName() + ", Amount: " + amount + ", Description: " + description + "]";
    }
}