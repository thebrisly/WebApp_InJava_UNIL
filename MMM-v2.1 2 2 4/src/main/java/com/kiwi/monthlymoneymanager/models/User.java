package com.kiwi.monthlymoneymanager.models;

import com.kiwi.monthlymoneymanager.models.Category;
import com.kiwi.monthlymoneymanager.models.Budget;
import com.kiwi.monthlymoneymanager.models.Operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @authors lsierro, lfabbian
 */

public class User 
{

    private String      name;
    private String      username;
    private String      email;
    public int          password;
    private double      monthlyIncome;
    private Budget      budget;
    private double      balance;
    private double      selectedAmount;
    private List<Operations> operations;
    private final ArrayList<Category> expenseCategories = new ArrayList<>();

    public User(String name, String username, String email, String selectedCategory, String password, double income, Budget budget, List<Operations> operationsList) 
    {
        this.name =     name;
        this.username = username;
        this.email =    email;
        this.password = password.hashCode();
        this.monthlyIncome = 0; // at the beginning there is no income and no budget
        this.budget = budget;
        this.operations = operationsList;
        this.balance = 0;
    }
    
    public ArrayList<Category> getExpenseCategories() {
        return expenseCategories;
    }

    public void addExpenseCategory(Category category) {
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
    
    public double getIncome() {
        return monthlyIncome;
    }
    
    public Budget getBudget() {
        return budget;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password.hashCode();
    }
    
    public void setIncome(double newIncome) {
        this.monthlyIncome = newIncome;
    }
    
    public void setBudget(Budget newBudget) {
        this.budget = newBudget;
    }
    
    public void setBalance(double newBalance) {
        this.balance = newBalance;
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

            Operations operation = new Operations(categoryAmountMap);
            this.operations.add(operation);
        }
    }
    
    public double calculateTotalExpenses() {
        double total = 0.0;

        if (operations != null) {
            for (Operations operation : operations) {
                for (Double amount : operation.getCategoryAmountMap().values()) {
                    total += amount;
                }
            }
        }

        return total;
    }

    public double calculateRemainingBalance() 
    {
        return getBalance() - calculateTotalExpenses();
    }
    
    public Map<String, Double> calculateExpensesByCategory() {
        Map<String, Double> expensesByCategory = new HashMap<>();

        for (Operations operation : this.getOperations()) {
            String category = operation.getCategory();
            Double amount = operation.getAmount();

            System.out.println("Operation - Category: " + category + ", Amount: " + amount);

            expensesByCategory.merge(category, amount, Double::sum);
        }

        System.out.println("Calculated Expenses by Category: " + expensesByCategory);

        return expensesByCategory;
    }
}