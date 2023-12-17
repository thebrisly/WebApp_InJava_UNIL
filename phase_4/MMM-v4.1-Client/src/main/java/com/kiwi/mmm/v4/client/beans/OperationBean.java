/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.mmm.v4.client.beans;

import com.kiwi.mmm.v4.client.models.Categories;
import com.kiwi.mmm.v4.client.models.Operations;
import com.kiwi.mmm.v4.client.models.Users;
import com.kiwi.mmm.v4.resources.PersistenceClient;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author laura
 */
@Named(value = "operationBean")
@SessionScoped
public class OperationBean implements Serializable 
{
    private String selectedCategory;
    private double expenseAmount;
    
    
    // ------------------------------------------------
    // ---------------- Getters and Setters -----------
    // ------------------------------------------------    

    public String getSelectedCategory() {
        return selectedCategory;
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


    public List<Operations> getAllOperations() {
        return PersistenceClient.getInstance().getAllOperations();
    }
    
    public List<Operations> getAllUserOperations(int userId) {
        return PersistenceClient.getInstance().getAllUserOperations(userId);
    }
    
    
    public Categories getACategory(){
        Categories category;
        for (Categories category1 : getAllCategories()){
            if (category1.getName() == null ? selectedCategory == null : category1.getName().equals(selectedCategory)){
                category = category1;
                return category;
            }
        }
        return null;
    }
                   
    public List<Categories> getAllCategories() {
        return PersistenceClient.getInstance().getAllCategories();
    }


    
    // ------------------------------------------------
    // ---------------- CREATE Function ---------------
    // ------------------------------------------------      
    
    public void addOperation(Users user){
        Operations operation = new Operations();
        
        Categories category;
        for (Categories category1 : getAllCategories()){
            if (category1.getName() == null ? selectedCategory == null : category1.getName().equals(selectedCategory)){
                category = category1;
                operation.setCategory(category);
                operation.setAmount(expenseAmount);
                operation.setUser(user);
                operation.setDate(new Date());
                
                System.out.println("Selected Category: " + selectedCategory);
                System.out.println("Expense Amount: " + expenseAmount);
                
                PersistenceClient.getInstance().createOperation(operation);
            }
        }    
    }


    // ------------------------------------------------
    // ---------------- DELETE Function ---------------
    // ------------------------------------------------  
    
    public void deleteOperation(Operations operation) {
        PersistenceClient.getInstance().deleteOperation(operation);
    }
}
