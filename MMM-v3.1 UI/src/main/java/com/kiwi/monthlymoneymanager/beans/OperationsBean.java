package com.kiwi.monthlymoneymanager.beans;

import com.kiwi.monthlymoneymanager.models.Categories;
import com.kiwi.monthlymoneymanager.models.Operations;
import com.kiwi.monthlymoneymanager.models.Users;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author tgrishkian, lfabbian
 */


@Named(value = "operationBean")
@SessionScoped
public class OperationsBean implements Serializable 
{
    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;
    
    private final Map<String, Double> expenses = new HashMap<>();
    private String selectedCategory;
    private double expenseAmount;

    public List<Categories> getCategories() {
       // return Database.getCategories();
       return null;
    }
    
    public Map<String, Double> getExpenses() {
        return expenses;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }
    
    public List<Categories> getAllCategories() {
        Query query = em.createNamedQuery("Categories.findAll", Categories.class);
        List<Categories> categories = query.getResultList();
        return categories;
    }


    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public void addExpense() {
        if (selectedCategory != null && !selectedCategory.isEmpty()) {
            expenses.put(selectedCategory, expenseAmount);
            // Optionally, you can clear the selectedCategory and expenseAmount for the next entry
            selectedCategory = null;
            expenseAmount = 0.0;
        }
    }
    
    public String getCategory() {
    // Récupérer le nom de la catégorie à partir de la map
    if (expenses != null && !expenses.isEmpty()) {
        String categoryKey = selectedCategory;
        return categoryKey != null ? categoryKey + " " + (expenses.get(categoryKey) != null ? expenses.get(categoryKey) : "") : "";
    }
    return "";
}


}

