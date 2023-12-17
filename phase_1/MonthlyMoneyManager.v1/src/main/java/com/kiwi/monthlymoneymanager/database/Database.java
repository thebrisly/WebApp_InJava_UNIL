/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.monthlymoneymanager.database;

import java.util.ArrayList;
import com.kiwi.monthlymoneymanager.models.User;
import com.kiwi.monthlymoneymanager.models.Category;
import com.kiwi.monthlymoneymanager.models.ContactForm;

/**
 *
 * @author lfabbian
 */


public class Database 
{
    
    // Database related to Users :
    private static ArrayList<User> users = new ArrayList<User> ();
            
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

    
    
    // Database related to Categories :
    private static ArrayList<Category> categories = new ArrayList<Category> () { 
        {
            add(new Category("test"));
        }
    };
    
    
    // Database related to ContactForm
    private static ArrayList<ContactForm> contactForms = new ArrayList<>();

    public static void saveContactForm(ContactForm form) {
        contactForms.add(form);
    }
   
}


/* public class MockDatabase {

    private static ArrayList<User> users = new ArrayList<User>() {
        {
            add(new User("lisa", "lisa", "simpson", "lisa@simpson.com", "1234"));
            add(new User("homer", "homer", "simpson", "homer@simpson.com", "1234"));
            add(new User("marge", "marge", "simpson", "marge@simpson.com", "1234"));
            add(new User("bart", "bart", "simpson", "bart@simpson.com", "1234"));
        }
    };
    private static ArrayList<Food> foods = new ArrayList<Food>() {
        {
            add(new Food("Pasta", 15.0, new ArrayList<String>() {
                {
                    add("spaghetti");
                    add("pesto sauce");
                    add("parmigiano");
                }
            }));
            add(new Food("Chicken Curry", 18.0, new ArrayList<String>() {
                {
                    add("chicken");
                    add("curry sauce");
                }
            }));
            add(new Food("Pizza", 12.0, new ArrayList<String>() {
                {
                    add("dough");
                    add("tomato sauce");
                    add("mozarella");
                }
            }));
        }
    };

    public static void addAUser(User user) {
        users.add(user);
    }

    public static void addFood(Food food) {
        foods.add(food);
    }

    public static void removeAUser(User user) {
        users.remove(user);
    }

    public static void removeFood(Food food) {

    }

    public static ArrayList<Food> getFoods() {
        return foods;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

}
*/