/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.monthlymoneymanager.models;

import com.kiwi.monthlymoneymanager.database.Database;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lsierro, lfabbian
 */


import java.util.List;
import java.util.Map;

public class Operations {
    private Map<String, Double> categoryAmountMap;

    public Operations(Map<String, Double> categoryAmountMap) {
        this.categoryAmountMap = categoryAmountMap;
    }

    public Map<String, Double> getCategoryAmountMap() {
        return categoryAmountMap;
    }

    public String getCategory() {
        if (categoryAmountMap != null && !categoryAmountMap.isEmpty()) {
            String categoryKey = categoryAmountMap.keySet().iterator().next();
            return categoryKey;
        }
        return "";
    }



    private Category getCategoryByName(String categoryName, List<Category> categories) {
        for (Category category : categories) {
            System.out.println("Comparing: " + categoryName + " with " + category.getName());
            if (category.getName().equals(categoryName)) {
                System.out.println("Category found!");
                return category;
            }
        }
        System.out.println("Category not found!");
        return null; // Retourne null si la catégorie n'est pas trouvée
    }


    public Double getAmount() 
    {
        if (categoryAmountMap != null && !categoryAmountMap.isEmpty()) 
        {
            return categoryAmountMap.values().iterator().next();
        }
        return null;
    }

 
}
