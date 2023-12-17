package com.kiwi.mmm.v4.client.beans;

import com.kiwi.mmm.v4.client.models.Budgets;
import com.kiwi.mmm.v4.client.models.Categories;
import com.kiwi.mmm.v4.client.models.Operations;
import com.kiwi.mmm.v4.client.models.Users;
import com.kiwi.mmm.v4.exceptions.AlreadyExistsException;
import com.kiwi.mmm.v4.exceptions.DoesNotExistException;
import com.kiwi.mmm.v4.exceptions.IncompleteUserInformationException;
import com.kiwi.mmm.v4.exceptions.InvalidEmailDomainException;
import com.kiwi.mmm.v4.exceptions.PasswordMismatchException;
import com.kiwi.mmm.v4.resources.PersistenceClient;
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
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;

/**
 *
 * @author tgrishkian, lfabbian
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {
    
    private int                 userId;
    private String              username;
    private String              email;
    private String              password;
    private String              name;
    private String              selectedCategory;
    private String              pictureProfile;
    private double              selectedAmount;
    private double              monthlyIncome;
    private double              balance;
    private Budgets             budget;
    private String              confirmPassword;
    private List<Operations>    operations;
    
    private String              newPassword;
    private String              newUsername;
    private String              newEmail;
    private String              newName;
    
    
    // ------------------------------------------------
    // ---------------- CREATE Function ---------------
    // ------------------------------------------------    

    public String createUser() throws DoesNotExistException, IncompleteUserInformationException, InvalidEmailDomainException {
        try {
            if (isAnyFieldEmpty()) {
                throw new IncompleteUserInformationException("All fields must be filled out.");
            }
            if (PersistenceClient.getInstance().getUsersByName(username) == null) {
                if (password != null && password.equals(confirmPassword)) {
                    if (!email.contains("@")) {
                        throw new InvalidEmailDomainException("Email must end with a valid domain name");
                    } 
                    Users newUser = new Users();
                    newUser.setName(name);
                    newUser.setPassword(password);
                    newUser.setUsername(username);
                    newUser.setEmail(email);
                    newUser.setMonthlyIncome(0.0);
                    newUser.setBalance(0.0);
                    newUser.setPictureProfile("default-pp.jpeg");  
                    newUser.setSelectedAmount(selectedAmount);
                    
                    PersistenceClient.getInstance().createUser(newUser);
                    
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
 
    // ------------------------------------------------
    // ---------------- UPDATE Functions --------------
    // ------------------------------------------------

    
    public void changeUsername(Users userToModify)
    {
        userToModify.setUsername(newUsername);
        PersistenceClient.getInstance().updateUser(userToModify);  
    }
    
    public void changeEmail(Users userToModify)
    {
        userToModify.setEmail(newEmail);
        PersistenceClient.getInstance().updateUser(userToModify);  
    }
    
    public void changePassword(Users userToModify)
    {
        userToModify.setPassword(newPassword);
        PersistenceClient.getInstance().updateUser(userToModify);  
    }
    
    public void addDeposit(Users userToModify)
    {
        userToModify.setMonthlyIncome(monthlyIncome);
        PersistenceClient.getInstance().updateUser(userToModify);  
    }

    
    
    // ------------------------------------------------
    // ---------------- Getters and Setters -----------
    // ------------------------------------------------
    
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
        return monthlyIncome;
    }
   
    public Budgets getBudget() {
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
   

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setIncome(double newIncome) {
        this.monthlyIncome = newIncome;
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
    
    public void setBudget(Budgets budget) {
        this.budget = budget;
    }
    
    public List<Operations> initOperations() {
        return new ArrayList<>();
    }

    
    public void setOperations(List<Operations> userOperations) {
        this.operations = userOperations;
    }


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
    
    public Operations getLastOperationForUser(int userId) 
    {
        List<Operations> allOperations = PersistenceClient.getInstance().getAllUserOperations(userId);
        allOperations.sort(Comparator.comparing(Operations::getDate).reversed());
        return allOperations.isEmpty() ? null : allOperations.get(0);
    }
    


    /**
     *
     * @param user
     * @return
     */
    
    // ------------------------------------------------
    // ---------------- OTHER FUNCTIONS ---------------
    // ------------------------------------------------
    
    
    public double calculateRemainingBalance(Users user) 
    {
        return user.getBalance() - calculateTotalExpenses(user);
    }
   
    
    public List<Operations> getAllUserOperations(int userId) {
        return PersistenceClient.getInstance().getAllUserOperations(userId);
    }
    
    public double calculateTotalExpenses(Users user) {
        List<Operations> operations = PersistenceClient.getInstance().getAllUserOperations(user.getUserId());
        double total = 0.0;

        if (operations != null) {
            for (Operations operation : operations) {
                total += operation.getAmount();
            }
        }

        return total;
    }
    
    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
    
    private boolean isAnyFieldEmpty() {
    // Assuming these are your input fields, adjust as needed
        return isEmpty(username) || isEmpty(name) || isEmpty(email) || isEmpty(password) || isEmpty(confirmPassword);
    }

    
    // ---------------------------------------------------------------------
    // --------- ALL THE FUNCTIONS THAT WE DIDN'T USE ON THIS PHASE -------- 
    // ---------------------------------------------------------------------
    
    /*
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
            double totalBudget = calculateTotalBudgets(user);
            //double totalBudget = 0;
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
   
   public double calculateExpensesByCategory(Users user, String categoryName) 
   {
        List<Operations> operations = getAllUserOperations(user.getUserId());
        double total = 0.0;

        if (operations != null) {
            for (Operations operation : operations) {
                // Vérifier si l'opération appartient à la catégorie spécifiée
                if (operation.getCategory().getName().equals(categoryName)) {
                    total += operation.getAmount();
                }
            }
        }

        return total;
    }
*/
    
    /*public void deleteUser(Users user)
    {
        PersistenceClient.getInstance().deleteUser(user);
        
    }   */
    
        /*
    @Transactional
    public void initBudget(Users user) 
    {
        Query query = em.createNamedQuery("Categories.findAll", Categories.class);
        List<Categories> categories = query.getResultList();
        
        for (Categories category : categories) {
            Budgets budget = new Budgets(user, category, 0.0);
            em.persist(budget);
        }
    }
    
    public List<Budgets> getAllUserBudgets(int userId)
    {
        Query query = em.createNamedQuery("Budgets.findByUserId", Budgets.class);
        List<Budgets> budgets = query.setParameter("userId", userId).getResultList();
        
        return budgets;
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
    
        /*      
    public double calculateTotalBudgets(Users user) 
    {
        List<Budgets> budgets = getAllUserBudgets(user.getUserId());
        double total = 0.0;

        if (budgets != null) {
            for (Budgets budget : budgets) {
                
                total += budget.getSelectedAmount();              
            }
        }

        return total;
    }
    */ 
}
