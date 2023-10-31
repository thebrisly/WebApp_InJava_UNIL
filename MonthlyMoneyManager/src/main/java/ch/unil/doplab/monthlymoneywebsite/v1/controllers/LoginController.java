package ch.unil.doplab.monthlymoneywebsite.v1.controllers;

import static ch.unil.doplab.monthlymoneywebsite.v1.controllers.UserController.findByUsername;
//import ch.unil.doplab.monthlymoneywebsite.v1.exceptions.DoesNotExistException;
import ch.unil.doplab.monthlymoneywebsite.v1.models.User;

public class LoginController {

    private static String username = "";
    private static String password = "";
    private static User currentUser;

    public static void userLogsIn() {
        try {
            User user = findByUsername(username);
            if (user != null && user.isPasswordCorrect(password)) {
                currentUser = user;
            }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void userLogsout() {
        currentUser = null;
    }

    public static User getUserLoggedIn() {
        return currentUser;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUsername() {
        return username;
    }

    public static void setCurrentUser(User currentUser) {
        LoginController.currentUser = currentUser;
    }

    public static void setPassword(String password) {
        LoginController.password = password;
    }

    public static void setUsername(String username) {
        LoginController.username = username;
    }

}
