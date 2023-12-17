/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.monthlymoneymanager.models;

import com.kiwi.monthlymoneymanager.beans.UserBean;
//import com.kiwi.monthlymoneymanager.database.Database;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 *
 * @authors lsierro, lfabbian
 */

public class XOLDBudget 
{
    private String username; // Unique username of the user
    private Map<Categories, Double> categoryAmounts; // Mapping of categories to their associated amounts
    private String      selectedCategory;
    private double      selectedAmount;
    
    public XOLDBudget(Map<Categories, Double> categoryAmounts, String selectedCategory, double selectedAmount) 
    {
        this.categoryAmounts = categoryAmounts;
        this.selectedCategory = selectedCategory;
        this.selectedAmount = selectedAmount;
    }
    
    // Getter and Setter methods for each property
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }
    public String getSelectedCategory() {
        return selectedCategory;
    }
    public double getSelectedAmount() {
    return selectedAmount;
    }

    public void setSelectedAmount(double selectedAmount) {
        this.selectedAmount = selectedAmount;
    }
    public Map<Categories, Double> getCategoryAmounts() {
        return categoryAmounts;
    }
    public List<String> getAllCategoryNames() {
        List<String> categoryNames;
        categoryNames = new ArrayList<>();
        for (Categories category : categoryAmounts.keySet()) {
            categoryNames.add(category.getName());
        }
        return categoryNames;
    }
    public void setCategoryAmounts(Map<Categories, Double> categoryAmounts) {
        this.categoryAmounts = categoryAmounts;
    }
    

    public void setBudgetForCategoryByName(String categoryName, double amount) {
        for (Categories category : categoryAmounts.keySet()) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                categoryAmounts.put(category, amount);
                return;
            }
        }
    }
    public void setBudgetForCategory(XOLDBudget userBudget, Categories category, double amount) {
        if (userBudget != null && category != null) {
            userBudget.getCategoryAmounts().put(category, amount);
        }
    }
}