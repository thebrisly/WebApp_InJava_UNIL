package com.kiwi.monthlymoneymanager.beans;

import com.kiwi.monthlymoneymanager.models.Users;
import com.kiwi.monthlymoneymanager.models.Categories;


import com.kiwi.monthlymoneymanager.exceptions.DoesNotExistException;
import com.kiwi.monthlymoneymanager.exceptions.AlreadyExistsException;
import com.kiwi.monthlymoneymanager.exceptions.IncompleteUserInformationException;
import com.kiwi.monthlymoneymanager.exceptions.InvalidEmailDomainException;
import com.kiwi.monthlymoneymanager.exceptions.WrongPasswordException;
import com.kiwi.monthlymoneymanager.exceptions.PasswordMismatchException;
import com.kiwi.monthlymoneymanager.models.Operations;
import com.kiwi.monthlymoneymanager.models.Users;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.stream.Collectors;
/**
 *
 * @author tgrishkian, lfabbian
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;
    
    private String username;
    private String email;
    private String password;
    private String name;
    private String selectedCategory;
    private double selectedAmount;
    private double income;
    private double balance;
    //private Budget budget;
    private List<Operations> operations;
    private String confirmPassword;
    
    private String newPassword;
    private String newUsername;
    private String newEmail;
    private String newName;
    
    @Transactional
    public String createUser() throws DoesNotExistException, IncompleteUserInformationException, InvalidEmailDomainException {
        try {
            if (isAnyFieldEmpty()) {
                throw new IncompleteUserInformationException("All fields must be filled out.");
            }
            if (!usernameExists()) {
                if (password != null && password.equals(confirmPassword)) {
                    // Les mots de passe correspondent, vous pouvez créer l'utilisateur
                    if (!email.contains("@")) {
                        throw new InvalidEmailDomainException("Email must end with a valid domain name");
                    } 
                    Users newUser = new Users();
                    newUser.setName(name);
                    newUser.setPassword(password.hashCode());
                    newUser.setUsername(username);
                    newUser.setEmail(email);
                    newUser.setMonthlyIncome(0.0);
                    newUser.setBalance(0.0);
                    newUser.setPictureProfile("default-pp.jpeg");

                    //newUser.setBudget(0.0);

                    List<Operations> initialOperations = initOperations();
                    newUser.setCategoriesCollection1(initialOperations.stream()
                        .map(Operations::getCategory)
                        .collect(Collectors.toList()));

                    em.persist(newUser); // Persist the user entity

                    LoginBean.setCurrentUser(newUser);
                    return "/UserPage/UserMainPage.xhtml?faces-redirect=true";
                } else {
                    throw new PasswordMismatchException("Passwords do not match.");
                }
            } else {
                throw new AlreadyExistsException("Username already exists.");
            }
        } catch (AlreadyExistsException | PasswordMismatchException | InvalidEmailDomainException | IncompleteUserInformationException ex) {
            FacesContext.getCurrentInstance().addMessage("userForm:confirmPassword",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
            return null; // Stay on the same page
        }
    }

    @Transactional
    public void changeUsername() 
    {
        Users currentUser = LoginBean.getUserLoggedIn();
        currentUser.setUsername(newUsername);
        
        if (!em.contains(currentUser)) 
        {
            em.merge(currentUser);
        }
        em.flush();
        System.out.println("Username updated successfully.");
    }   

    @Transactional
    public void changeName() 
    {
        Users currentUser = LoginBean.getUserLoggedIn();
        currentUser.setName(newName);
        
        if (!em.contains(currentUser)) 
        {
            em.merge(currentUser);
        }
        em.flush();
        System.out.println("Name updated successfully.");
    }

    @Transactional
    public void changePassword() 
    {
        Users currentUser = LoginBean.getUserLoggedIn();
        currentUser.setPassword(newPassword.hashCode());
        
        if (!em.contains(currentUser)) 
        {
            em.merge(currentUser);
        }
        em.flush();
        System.out.println("Password changed successfully.");
    }
    
    @Transactional
    public void changeEmail() {
        Users currentUser = LoginBean.getUserLoggedIn();
        currentUser.setEmail(newEmail);
        
        if (!em.contains(currentUser)) 
        {
            em.merge(currentUser);
        }
        em.flush();
        System.out.println("Email updated successfully.");
    }
    
    /*protected static Users findByUsername(String username) throws DoesNotExistException 
    {
        for (Users currentUser : Database.getUsers()) {
            if (currentUser.getUsername().equals(username)) {
                return currentUser;
            }
        }
        throw new DoesNotExistException("The user " + username + " does not exist.");
    }

   
    protected boolean emailExists() throws AlreadyExistsException 
    {
        for (Users currentUser : Database.getUsers()) {
            if (currentUser.getEmail().equals(email)) {
                throw new AlreadyExistsException("The email " + email + " already in use.");
            }
        }
        return false;
    }

    */

    /*protected boolean usernameExists() throws DoesNotExistException 
    {
        for (Users currentUser : Database.getUsers()) {
            if (currentUser.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }*/
    
    
    private boolean usernameExists() throws DoesNotExistException {
        Query query = em.createNamedQuery("Users.findByUsername");
        List<Users> users = query.setParameter("username", username).getResultList();
        return !users.isEmpty();
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
    
    private boolean isAnyFieldEmpty() {
    // Assuming these are your input fields, adjust as needed
        return isEmpty(username) || isEmpty(name) || isEmpty(email) || isEmpty(password) || isEmpty(confirmPassword);
    }
    
    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
    
    /*public Budget getBudget() {
        return budget;
    }*/
    
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
    
    public List<Operations> getAllUserOperations(int userId)
    {
        Query query = em.createNamedQuery("Operations.findByUserId", Operations.class);
        List<Operations> operations = query.setParameter("userId", userId).getResultList();
        
        return operations;
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
    
    /*public void setBudget(Budget budget) {
        this.budget = budget;
    }*/
    
    public List<Operations> initOperations() {
        return new ArrayList<>();
    }

    
    public void setOperations(List<Operations> userOperations) {
        this.operations = userOperations;
    }
    
    /*public Budget initBudget() 
    {
        /*Map<Category, Double> initialCategory = new HashMap<>();
        for (Category category : Database.getCategories()) {
            initialCategory.put(category, 0.0); // Use 0.0 instead of 0 for a double value
        }
        Budget budget = new Budget(initialCategory, selectedCategory, selectedAmount);
        return budget;
    }*/
    
    /*
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
    }*/


    public List<Operations> getUserOperations() {
        return null;
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

    /**
     *
     * @param user
     * @return
     */
    public double calculateRemainingBalance(Users user) 
    {
        return user.getBalance() - calculateTotalExpenses(user);
    }
    
    
    public double calculateTotalExpenses(Users user) 
    {
        List<Operations> operations = getAllUserOperations(user.getUserId());
        double total = 0.0;

        if (operations != null) {
            for (Operations operation : operations) {
                
                total += operation.getAmount();              
            }
        }

        return total;
    }
    
    @Transactional
    public void addOperation(String category, double amount, Users user) 
    {
        Operations operation = new Operations();
        operation.setUser(user);
        operation.setDate(new Date());
        operation.setCategory(findCategoryByName(category));
        operation.setAmount(amount);
        
        em.persist(operation);
    }
    
    @Transactional
    public void deleteLastOperation(Users user) 
    {
        List<Operations> userOperations = getAllUserOperations(user.getUserId());

        if (userOperations != null && !userOperations.isEmpty()) {
            Operations lastOperation = userOperations.get(userOperations.size() - 1);
            em.remove(lastOperation);
            userOperations.remove(lastOperation);
        }
    }

    
    public Categories findCategoryByName(String categoryName)
    {
        Query query = em.createNamedQuery("Categories.findByName", Categories.class);
        List<Categories> category = query.setParameter( "name", categoryName).getResultList();
        if (!category.isEmpty()) { 
            return category.get(0);
        }
        
        return null;
    }

    
    public List<Operations> getLastThreeUserOperations(int userId) 
    {
        String jpqlQuery = "SELECT o FROM Operations o WHERE o.user.userId = :userId ORDER BY o.date DESC";
        TypedQuery<Operations> query = em.createQuery(jpqlQuery, Operations.class);
        List<Operations> operations = query.setParameter("userId", userId)
                                          .setMaxResults(3)
                                          .getResultList();

        return operations;
    }

   public List<Map<String, Double>> getFinanceSummary(Users user) {
            //double totalBudget = calculateTotalBudgeted();
            double totalBudget = 0;
            double totalExpenses = calculateTotalExpenses(user);
            double totalDifferences = totalBudget - totalExpenses; // Calculate the difference

            Map<String, Double> summary = new HashMap<>();
            summary.put("totalBudget", totalBudget);
            summary.put("totalExpenses", totalExpenses);
            summary.put("totalDifferences", totalDifferences);

            List<Map<String, Double>> summaryList = new ArrayList<>();
            summaryList.add(summary);

            return summaryList;
    } 
    
}
