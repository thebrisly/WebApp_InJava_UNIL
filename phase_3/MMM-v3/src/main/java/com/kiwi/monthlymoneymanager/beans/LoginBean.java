package com.kiwi.monthlymoneymanager.beans;

// importing our models
//import com.kiwi.monthlymoneymanager.models.Category;
//import com.kiwi.monthlymoneymanager.database.Database;
import com.kiwi.monthlymoneymanager.models.Users;

// importing exceptions
import com.kiwi.monthlymoneymanager.exceptions.DoesNotExistException;
import com.kiwi.monthlymoneymanager.exceptions.WrongPasswordException;
import com.kiwi.monthlymoneymanager.models.Users;
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
@PersistenceContext(unitName = "soar_PU")
    private EntityManager em;

    private static boolean isPasswordMasked = true;
    private String username = "";
    private String password = "";
    private static Users currentUser;
    private String confirmPassword;

   public String userLogsIn() 
   {
        FacesContext context = FacesContext.getCurrentInstance();
        Users user;

        try {
            user = findByUsername();
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
   
   protected Users findByUsername() throws DoesNotExistException {
        Query query = em.createNamedQuery("Users.findByUsername", Users.class);
        List<Users> users = query.setParameter("username", username).getResultList();
        if (!users.isEmpty()) {
            return users.get(0);
        }
        throw new DoesNotExistException("The user " + username + " does not exist.");
    }


    public String userLogsOut() {
        currentUser = null;
        return "/MainPage/MainPage.xhtml?faces-redirect=true";
    }

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
    
    @Transactional
    public void addDeposit() 
    {
        try {
            if (getUserLoggedIn() != null) {
                getUserLoggedIn().setBalance(getUserLoggedIn().getBalance() + getUserLoggedIn().getMonthlyIncome());

                if (!em.contains(getUserLoggedIn())) {
                    em.merge(getUserLoggedIn());
                }

                em.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
