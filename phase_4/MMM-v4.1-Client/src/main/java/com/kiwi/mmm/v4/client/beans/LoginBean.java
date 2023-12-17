package com.kiwi.mmm.v4.client.beans;


// importing our models
//import com.kiwi.monthlymoneymanager.models.Category;
//import com.kiwi.monthlymoneymanager.database.Database;

// importing exceptions
import com.kiwi.mmm.v4.client.models.Users;
import com.kiwi.mmm.v4.exceptions.DoesNotExistException;
import com.kiwi.mmm.v4.resources.PersistenceClient;
import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
/**
 *
 * @author tgrishkian, lfabbian
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable
{

    private static boolean      isPasswordMasked = true;
    private String              username = "";
    private String              password = "";
    private static Users        currentUser;
    private String              confirmPassword;

    
    
   public String userLogsIn() 
   {
        try {
            Users u = PersistenceClient.getInstance().checkPassword(username, password.hashCode());
            if (u != null) {
                currentUser = u;
                return "/UserPage/UserMainPage.xhtml?faces-redirect=true";
            }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        //messageError = "Username or Passeword incorrect";
        return "/MainPage/MainPage.xhtml?faces-redirect=true";
    }

    public String userLogsOut() 
    {
        currentUser = null;
        return "/MainPage/MainPage.xhtml?faces-redirect=true";
    }

    
    // ------------------------------------------------
    // ---------------- Getters and Setters -----------
    // ------------------------------------------------ 
    
    public static Users getUserLoggedIn() {
        return currentUser;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }

    public void setUsername(String newUsername) {
        username = newUsername;
    }

    public String getPassword() {
        return password;
    }
    
    public static void setPasswordMasking(boolean state) {
        isPasswordMasked = state;
    }
    
    public static boolean getPasswordMasking() {
        return isPasswordMasked;
    }

    public String getUsername() {
        return username;
    }
    
    public static void setCurrentUser(Users user) {
        currentUser = user;
    }
    

    // ------------------------------------------------
    // ---------------- UPDATE Function ---------------
    // ------------------------------------------------ 
    
    public void addDeposit() {
        try {
            if (getUserLoggedIn() != null) {
                double currentBalance = getUserLoggedIn().getBalance();
                double additionalIncome = getUserLoggedIn().getMonthlyIncome();
                
                getUserLoggedIn().setBalance(currentBalance + additionalIncome);

                PersistenceClient.getInstance().updateUser(getUserLoggedIn());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
