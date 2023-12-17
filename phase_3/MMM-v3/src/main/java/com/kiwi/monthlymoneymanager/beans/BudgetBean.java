package com.kiwi.monthlymoneymanager.beans;

//import com.kiwi.monthlymoneymanager.models.Budget;

import com.kiwi.monthlymoneymanager.models.Categories;
import com.kiwi.monthlymoneymanager.models.Users;
import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tgrishkian, lfabbian
 */
/*@Named(value = "budgetBean")
@SessionScoped
public class BudgetBean implements Serializable 
{
    public void displayCategoryNames() 
    {
        ArrayList<Categories> allCategories = Database.getCategories();
        System.out.print("Possible categories: ");
        for (int i = 0; i < allCategories.size(); i++) {
            System.out.print(allCategories.get(i).getName());
            if (i < allCategories.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public Categories getCategoryByName(String categoryName) {
        for (Categories category : Database.getCategories()) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return null; // returns null if nothing is found
    }
    
    public void setBudgetForCategory(Budget budget, Category category, double amount) 
    {
        Map<Category, Double> categoryAmounts = budget.getCategoryAmounts();
        categoryAmounts.put(category, amount);
        budget.setCategoryAmounts(categoryAmounts);
    }
    
    public Budget initBudget() 
    {
        Map<Category, Double> initialCategory = new HashMap<>();
        for (Category category : Database.getCategories()) {
            
            initialCategory.put(category, 0.0); // Use 0.0 instead of 0 for a double value
        }
        String initialSelectedCategory = "";
        double initialSelectedAmount = 0.0;
        Budget budget = new Budget(initialCategory, initialSelectedCategory, initialSelectedAmount);
        return budget;
    }

    public Map<Category, Double> getCategoryAmounts(Budget budget) {
        return budget.getCategoryAmounts();
    }

    public void setCategoryAmounts(Budget budget, Map<Category, Double> categoryAmounts) {
        budget.setCategoryAmounts(categoryAmounts);
    }
}
*/