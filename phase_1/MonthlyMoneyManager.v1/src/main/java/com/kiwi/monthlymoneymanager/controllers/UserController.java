package com.kiwi.monthlymoneymanager.controllers;

import com.kiwi.monthlymoneymanager.models.User;
import com.kiwi.monthlymoneymanager.database.Database;

import com.kiwi.monthlymoneymanager.exceptions.DoesNotExistException;
import com.kiwi.monthlymoneymanager.exceptions.AlreadyExistsException;
import com.kiwi.monthlymoneymanager.exceptions.WrongPasswordException;

/**
 *
 * @author tgrishkian, lfabbian
 */

public class UserController {

    private static String username;
    private static String email;
    private static String password;
    private static String name;
    //private static double amount;

    // creation of a new user and addittion of user to the database
    public static void createUser() throws DoesNotExistException {
        try {
            // Check if email or username do already exists in database
            if (!emailExists() && !usernameExists()) {
                // Addition of a new user with the provided details to the database
                Database.addUser(new User(name, username, email, password));
            } else {
                // If user or email exist, custom exception thrown.
                throw new AlreadyExistsException("Username or Email already exists.");
            }
        } catch (AlreadyExistsException ex) {
            // Exception message.
            System.out.println(ex.getMessage());
        }
    }

    /*public static void updateBudgetBalance(double amount) {
    // Addition of the amount to the static availableBudget field
    availableBudget += amount;
    // Update the budget 
    MockDatabase.updateBudget(username, availableBudget);
    }*/

    public static void changeUsername(String newUsername) {
        String oldUsername = getUsername(); // Obtenez l'ancien nom d'utilisateur
        User currentUser = LoginController.getUserLoggedIn();
        currentUser.setUsername(newUsername);
        // Mettez à jour le nom d'utilisateur dans la base de données
        Database.updateUsername(oldUsername, newUsername);
        System.out.println("Username updated successfully.");
    }

    public static void changeName(String newName) {
        String username = getUsername(); // Obtenez le nom d'utilisateur de l'utilisateur connecté
        User currentUser = LoginController.getUserLoggedIn();
        currentUser.setName(newName);
        // Mettez à jour le nom dans la base de données
        Database.updateName(username, newName);
        System.out.println("Name updated successfully.");
    }

    public static void changePassword(String currentPassword, String newPassword) {
        try {
            String username = getUsername(); // Obtenez le nom d'utilisateur de l'utilisateur connecté
            User currentUser = LoginController.getUserLoggedIn();
            if (currentUser.isPasswordCorrect(currentPassword)) {
                currentUser.setPassword(newPassword);
                // Mettez à jour le mot de passe dans la base de données
                Database.updatePassword(username, newPassword);
                System.out.println("Password changed successfully.");
            } else {
                throw new WrongPasswordException("Error: Current password is incorrect.");
            }
        } catch (WrongPasswordException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    protected static User findByUsername(String username) throws DoesNotExistException {
        for (User user : Database.getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new DoesNotExistException("The user " + username + " does not exist.");
    }

   
    protected static boolean emailExists() throws AlreadyExistsException {
        for (User user : Database.getUsers()) {
            if (user.getEmail().equals(email)) {
                throw new AlreadyExistsException("The email " + email + " already in use.");
            }
        }
        return false;
    }

    protected static boolean usernameExists() throws DoesNotExistException {
        for (User user : Database.getUsers()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    
    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        UserController.email = email;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserController.username = username;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        UserController.name= name;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserController.password = password;
    }
    
}


/*public class UserController 
{

    private static String username = "";
    private static String name = "";
    private static String email = "";
    private static String password = "";

    public static void createUser() 
    {
        try {
            if (!emailExists() && !usernameExists()) {
                Database.addAUser(new User(name, username, email, password));
            }
        } catch (AlreadyExistsException | DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void depositMoney() {
        LoginController.getUserLoggedIn().increaseBalance(amount);
    }

    public static void completeShopping() {
        try {
            LoginController.getUserLoggedIn().completeShopping();
        } catch (InsufficientBalanceException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected static User findByUsername(String username) throws DoesNotExistException {
        for (User user : Database.getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new DoesNotExistException("The user " + username + " does not exist.");
    }

    protected static boolean emailExists() throws AlreadyExistsException {
        for (User user : Database.getUsers()) {
            if (user.getEmail().equals(email)) {
                throw new AlreadyExistsException("The email " + email + " already in use.");
            }
        }
        return false;
    }

    protected static boolean usernameExists() throws DoesNotExistException {
        for (User user : Database.getUsers()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static double getAmount() {
        return amount;
    }

    public static String getEmail() {
        return email;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUsername() {
        return username;
    }

    public static void setAmount(double amount) {
        UserController.amount = amount;
    }

    public static void setEmail(String email) {
        UserController.email = email;
    }

    public static void setFirstName(String firstName) {
        UserController.firstName = firstName;
    }

    public static void setLastName(String lastName) {
        UserController.lastName = lastName;
    }

    public static void setPassword(String password) {
        UserController.password = password;
    }

    public static void setUsername(String username) {
        UserController.username = username;
    }

}
*/