/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.monthlymoneymanager.models;

import java.util.List;
import java.util.Map;



/**
 *
 * @authors lsierro, lfabbian
 */

public class Budget 
{
    private String username; // Unique username of the user
    private Map<Category, Integer> categoryAmounts; // Mapping of categories to their associated amounts

    public Budget(String username, Map<Category, Integer> categoryAmounts) 
    {
        this.username = username;
        this.categoryAmounts = categoryAmounts;
    }

    // Getter and Setter methods for each property
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<Category, Integer> getCategoryAmounts() {
        return categoryAmounts;
    }

    public void setCategoryAmounts(Map<Category, Integer> categoryAmounts) {
        this.categoryAmounts = categoryAmounts;
    }

    @Override
    public String toString() {
        StringBuilder budgetString = new StringBuilder("Budget for User: " + username + "\n");
        for (Category category : categoryAmounts.keySet()) {
            int amount = categoryAmounts.get(category);
            budgetString.append(category.getName()).append(": ").append(amount).append("\n");
        }
        return budgetString.toString();
    }
}