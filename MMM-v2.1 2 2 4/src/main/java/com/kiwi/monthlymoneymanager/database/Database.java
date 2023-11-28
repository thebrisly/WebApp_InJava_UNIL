/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.monthlymoneymanager.database;

import com.kiwi.monthlymoneymanager.models.User;
import com.kiwi.monthlymoneymanager.models.Category;
import com.kiwi.monthlymoneymanager.models.ContactForm;
import com.kiwi.monthlymoneymanager.models.Budget;
import com.kiwi.monthlymoneymanager.models.Operations;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lfabbian
 */


public class Database 
{
    
    // Database related to Users :
    private static ArrayList<User> users = new ArrayList<User> (); //empty database at the beginning, cause nobody signed up
            
    public static void addUser(User user) 
    {
        users.add(user);
    }
    
    public static ArrayList<User> getUsers() 
    {
        return users;
    }
    
    public static void updateUsername(String oldUsername, String newUsername) {
        for (User user : users) {
            if (user.getUsername().equals(oldUsername)) {
                user.setUsername(newUsername);
                break;
            }
        }
    }

    public static void updateName(String username, String newName) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setName(newName);
                break;
            }
        }
    }

    public static void updatePassword(String username, String newPassword) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setPassword(newPassword);
                break;
            }
        }
    }
    
    public static boolean usernameExists(String username) 
    {
        for (User user : users) 
        {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    
    // Database related to Categories :
    private static ArrayList<Category> categories = new ArrayList<Category> () { 
        {
            add(new Category("Housing"));
            add(new Category("Food"));
            add(new Category("Clothes"));
            add(new Category("Taxes"));
            add(new Category("Transportation"));
            add(new Category("Restaurants"));
            add(new Category("Savings"));
            add(new Category("Investment"));
            add(new Category("Health"));
            add(new Category("Entertainment"));
            add(new Category("Education"));
            add(new Category("Gifts"));
            add(new Category("Travel"));
        }
    };
    
    public static void addCategory(Category category) 
    {
        categories.add(category);
    }
    
    public static ArrayList<Category> getCategories() 
    {
        return categories;
    }
    
    // Database related to the Budget
    private static ArrayList<Budget> budgets = new ArrayList<> ();

    public static void addBudget(Budget budget) {
        budgets.add(budget);
    }

    public static ArrayList<Budget> getBudgets() {
        return budgets;
    }
    
    
    // Database related to Operations
    private static ArrayList<Operations> operations = new ArrayList<>();

    public static void addOperationToUser(User user, Operations operation) 
    {
        user.getOperations().add(operation);
        operations.add(operation);
    }

    public static List<Operations> getOperationsForUser(User user) {
        return user.getOperations();
    }

    public static List<Operations> getAllOperations() {
        return operations;
    }

    public static void removeOperationFromUser(User user, Operations operation) {
        user.getOperations().remove(operation);
        operations.remove(operation);
    }
    
    
    
    // Database related to ContactForm
    private static ArrayList<ContactForm> contactForms = new ArrayList<>();

    public static void saveContactForm(ContactForm form) {
        contactForms.add(form);
    }
   
}
