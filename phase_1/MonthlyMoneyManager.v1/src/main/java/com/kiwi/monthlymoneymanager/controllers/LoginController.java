package com.kiwi.monthlymoneymanager.controllers;

// importing our models
//import com.kiwi.monthlymoneymanager.models.Category;
import com.kiwi.monthlymoneymanager.database.Database;
import com.kiwi.monthlymoneymanager.models.User;

// importing exceptions
import com.kiwi.monthlymoneymanager.exceptions.DoesNotExistException;
import com.kiwi.monthlymoneymanager.exceptions.WrongPasswordException;

/**
 *
 * @author tgrishkian, lfabbian
 */

public class LoginController 
{

    private static boolean isPasswordMasked = true;
    private static String username = "";
    private static String password = "";
    private static User currentUser;

    public static boolean userLogsIn() 
    {
        try {
            User user = UserController.findByUsername(username);

            if (user != null && user.isPasswordCorrect(password)) {
                currentUser = user;
                return true;
            }
            else if (!user.isPasswordCorrect(password)) 
            {
                throw new WrongPasswordException("Error : Wrong password.");
            }
            return false;
        } 
        catch (DoesNotExistException ex) 
        {
            System.out.println(ex.getMessage());
            return false;
        }
        catch (WrongPasswordException ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static void userLogsOut() {
        currentUser = null;
    }

    public static User getUserLoggedIn() {
        return currentUser;
    }

    public static void setPassword(String newPassword) {
        password = newPassword;
    }

    public static void setUsername(String newUsername) {
        username = newUsername;
    }

    public static String getPassword() {
        return password;
    }
    
    public static void setPasswordMasking(boolean state) {
        isPasswordMasked = state;
    }
    
    public static boolean getPasswordMasking() {
        return isPasswordMasked;
    }

    public static String getUsername() {
        return username;
    }

}
