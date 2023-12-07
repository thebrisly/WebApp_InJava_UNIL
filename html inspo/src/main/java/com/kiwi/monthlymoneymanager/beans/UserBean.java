package com.kiwi.monthlymoneymanager.beans;

import com.kiwi.monthlymoneymanager.models.User;
import com.kiwi.monthlymoneymanager.models.Budget;
import com.kiwi.monthlymoneymanager.models.Category;

import com.kiwi.monthlymoneymanager.database.Database;

import com.kiwi.monthlymoneymanager.exceptions.DoesNotExistException;
import com.kiwi.monthlymoneymanager.exceptions.AlreadyExistsException;
import com.kiwi.monthlymoneymanager.exceptions.WrongPasswordException;
import com.kiwi.monthlymoneymanager.exceptions.PasswordMismatchException;
import com.kiwi.monthlymoneymanager.models.Operations;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
/**
 *
 * @author tgrishkian, lfabbian
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    private String username;
    private String email;
    private String password;
    private String name;
    private String selectedCategory;
    private double selectedAmount;
    private double income;
    private double balance;
    private Budget budget;
    private List<Operations> operations;
    private String confirmPassword;
    
    private String newPassword;
    private String newUsername;
    private String newEmail;
    
    //private static double amount;

    // creation of a new user and addittion of user to the database
    public String createUser() throws DoesNotExistException {
        try {
            if (!usernameExists()) {
                if (password.equals(confirmPassword)) {
                    // Les mots de passe correspondent, vous pouvez créer l'utilisateur
                    User newUser = new User(name, username, email, selectedCategory, password, 0.0, null, null);
                    
                    Budget initialBudget = initBudget();
                    newUser.setBudget(initialBudget);

                    List<Operations> initialOperations = initOperations();
                    newUser.setOperations(initialOperations);
                    
                    Database.addUser(newUser);
                    
                    LoginBean.setCurrentUser(newUser);
                    
                    return "/UserPage/UserMainPage.xhtml?faces-redirect=true";
                } else {
                    throw new PasswordMismatchException("Passwords do not match.");
                }
            } else {
                throw new AlreadyExistsException("Username already exists.");
            }
        } catch (AlreadyExistsException | PasswordMismatchException ex) {
            FacesContext.getCurrentInstance().addMessage("userForm:confirmPassword",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
            return null; // Reste sur la même page
        }
    }

    public void changeUsername() 
    {
        String oldUsername = getUsername();
        User currentUser = LoginBean.getUserLoggedIn();
        currentUser.setUsername(newUsername);
        Database.updateUsername(oldUsername, newUsername);
        System.out.println("Username updated successfully.");
    }

    public void changeName(String newName) 
    {
        String username = getUsername();
        User currentUser = LoginBean.getUserLoggedIn();
        currentUser.setName(newName);
        Database.updateName(username, newName);
        System.out.println("Name updated successfully.");
    }

    public void changePassword() 
    {
        User currentUser = LoginBean.getUserLoggedIn();
        currentUser.setPassword(newPassword);
        Database.updatePassword(currentUser.getUsername(), newPassword);
        System.out.println("Password changed successfully.");
    }
    public void changeEmail() {
        User currentUser = LoginBean.getUserLoggedIn();
        currentUser.setEmail(newEmail);
        Database.updateEmail(currentUser.getUsername(), newEmail);
        System.out.println("Email updated successfully.");
    }
    
    protected static User findByUsername(String username) throws DoesNotExistException 
    {
        for (User currentUser : Database.getUsers()) {
            if (currentUser.getUsername().equals(username)) {
                return currentUser;
            }
        }
        throw new DoesNotExistException("The user " + username + " does not exist.");
    }

   
    protected boolean emailExists() throws AlreadyExistsException 
    {
        for (User currentUser : Database.getUsers()) {
            if (currentUser.getEmail().equals(email)) {
                throw new AlreadyExistsException("The email " + email + " already in use.");
            }
        }
        return false;
    }

    protected boolean usernameExists() throws DoesNotExistException 
    {
        for (User currentUser : Database.getUsers()) {
            if (currentUser.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

 // variables getters   
    public String getEmail() {
        return email;
    }
  
    public String getUsername() {
        return username;
    }
    
    public String getName() {
        return name;
    }
    
    public double getIncome() {
        return income;
    }
    
    public Budget getBudget() {
        return budget;
    }
    
    public String getPassword() {
        return password;
    }
    
    public List<Operations> getOperations() {
        return operations;
    }
     
    public List<Operations> getLast3Operations(){
        switch(operations.size()){
            case 0:
            case 1:
            case 2:
            case 3:
                return operations;
            default:
                return operations.subList(operations.size()-3, operations.size());
        }
    }
// settings of variables    

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setIncome(double newIncome) {
        this.income = newIncome;
    } 
    
    public  void setUsername(String username) {
        this.username = username;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setBudget(Budget budget) {
        this.budget = budget;
    }
    
    public List<Operations> initOperations() {
        return new ArrayList<>();
    }

    
    public void setOperations(List<Operations> userOperations) {
        this.operations = userOperations;
    }
    
    public Budget initBudget() 
    {
        Map<Category, Double> initialCategory = new HashMap<>();
        for (Category category : Database.getCategories()) {
            initialCategory.put(category, 0.0); // Use 0.0 instead of 0 for a double value
        }
        Budget budget = new Budget(initialCategory, selectedCategory, selectedAmount);
        return budget;
    }
    
    public void setBudgetForCategory(Category category, double amount) 
    {
        Budget userBudget = getBudget();
        if (userBudget != null) {
            userBudget.setBudgetForCategory(userBudget, category, amount);
        }
    }
    
    public void setBudgetForCategoryByName(String categoryName, double amount) {
        Budget userBudget = getBudget();
        if (userBudget != null) {
            userBudget.setBudgetForCategoryByName(categoryName, amount);
        }
    }
    
    public void addOperation(String category, double amount) {
        if (category != null && !category.isEmpty() && amount > 0) {
            Map<String, Double> categoryAmountMap = new HashMap<>();
            categoryAmountMap.put(category, amount);

            Operations operation = new Operations(categoryAmountMap);
            this.operations.add(operation);
        }
    }


    public List<Operations> getUserOperations() {
        return Database.getOperationsForUser(LoginBean.getUserLoggedIn());
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewUsername() {
        return newUsername;
    }
    public String getNewEmail() {
        return newEmail;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }
    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
    public double getSelectedAmount() {
        return selectedAmount;
    }

    public void setSelectedAmount(double selectedAmount) {
        this.selectedAmount = selectedAmount;
    }

}
