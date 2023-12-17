package com.kiwi.mmm.v4.client.models;

import com.kiwi.mmm.v4.client.models.Categories;
import com.kiwi.mmm.v4.client.models.Operations;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author laura
 */
public class Users {
    private int                         userId;
    private String                      name;
    private String                      username;
    private String                      email;
    private String                      pictureProfile;
    public int                          password;
    private double                      monthlyIncome;
    //private Budget                    budget;
    private double                      balance;
    private double                      selectedAmount;
    private List<Operations>            operations;
    private final ArrayList<Categories> expenseCategories = new ArrayList<>();

    public Users() {}
    
    public Users(int userId, String name, String username, String email, String selectedCategory, String password, double income, List<Operations> operationsList) 
    {
        this.userId =                   userId;
        this.name =                     name;
        this.username =                 username;
        this.email =                    email;
        this.password =                 password.hashCode();
        this.monthlyIncome =            0; // at the beginning there is no income and no budget
        //this.budget =                 budget;
        this.operations =               operationsList;
        this.balance =                  0;
    }
    
    public ArrayList<Categories> getExpenseCategories() {
        return expenseCategories;
    }

    public void addExpenseCategory(Categories category) {
        expenseCategories.add(category);
    }

    public String getUsername() {
        return username;
    }
    
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPassword() {
        return password;
    }
    
    public int getUserId(){
        return userId;
    }
    
    public double getSelectedAmount(){
        return selectedAmount;
    }
    
    public double getMonthlyIncome() {
        return monthlyIncome;
    }
    
    
    public double getBalance() {
        return balance;
    }
    
    public List<Operations> getOperations() {
        return operations;
    }
    

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
    
    public void setPictureProfile(String pictureProfile) {
        this.pictureProfile = pictureProfile;
    }
    
    public String getPictureProfile() {
        return pictureProfile;
    }
            

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setSelectedAmount(double amount) {
        this.selectedAmount = amount;
    }

    public void setPassword(String password) {
        this.password = password.hashCode();
    }
    
    public void setIncome(double newIncome) {
        this.monthlyIncome = newIncome;
    }
   
    
    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }
    
    public void getUserId(int userId) {
        this.userId = userId;
    }
    
    public void setOperations(List<Operations> operations) {
        this.operations = operations;
    }

    public boolean isPasswordCorrect(String password) {
        return password.hashCode() == this.password;
    }
    
    public void addOperation(String category, double amount) 
    {
        if (category != null && !category.isEmpty() && amount > 0) {
            Map<String, Double> categoryAmountMap = new HashMap<>();
            categoryAmountMap.put(category, amount);

        }
    }
   
    public List<Map<String, Double>> getFinanceSummary() {
            double totalBudget = 0; // Example total budget
            double totalExpenses = 0; // Example total expenses
            double totalDifferences = totalBudget - totalExpenses; // Calculate the difference

            Map<String, Double> summary = new HashMap<>();
            summary.put("totalBudget", totalBudget);
            summary.put("totalExpenses", totalExpenses);
            summary.put("totalDifferences", totalDifferences);

            List<Map<String, Double>> summaryList = new ArrayList<>();
            summaryList.add(summary);

            return summaryList;
    } 

}