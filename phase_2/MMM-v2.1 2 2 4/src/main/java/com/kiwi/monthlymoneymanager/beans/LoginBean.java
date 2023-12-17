package com.kiwi.monthlymoneymanager.beans;

// importing our models
//import com.kiwi.monthlymoneymanager.models.Category;
import com.kiwi.monthlymoneymanager.database.Database;
import com.kiwi.monthlymoneymanager.models.User;

// importing exceptions
import com.kiwi.monthlymoneymanager.exceptions.DoesNotExistException;
import com.kiwi.monthlymoneymanager.exceptions.WrongPasswordException;
import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
/**
 *
 * @author tgrishkian, lfabbian
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable
{

    private static boolean isPasswordMasked = true;
    private String username = "";
    private String password = "";
    private static User currentUser;
    private String confirmPassword;

   public String userLogsIn() 
   {
        FacesContext context = FacesContext.getCurrentInstance();
        User user;

        try {
            user = UserBean.findByUsername(username);
        } catch (DoesNotExistException ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User not found.", null));
            return "/MainPage/LoginPage.xhtml?faces-redirect=true";
        }

        if (user != null) {
            if (user.isPasswordCorrect(password)) {
                currentUser = user;
                return "/UserPage/UserMainPage.xhtml?faces-redirect=true";
            } else {
                // Mot de passe incorrect
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect password.", null));
                return "/MainPage/LoginPage.xhtml?faces-redirect=true";
            }
        } else {
            // Utilisateur non trouvé
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User not found.", null));
            return "/MainPage/LoginPage.xhtml?faces-redirect=true";
        }
    }


    public String userLogsOut() {
        currentUser = null;
        return "/MainPage/MainPage.xhtml?faces-redirect=true";
    }

    public static User getUserLoggedIn() {
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
    
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    
}
