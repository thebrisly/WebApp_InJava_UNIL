package com.kiwi.monthlymoneymanager.views;

// import java utils
import java.util.Scanner;
import java.util.*;

// importing our controllers
import com.kiwi.monthlymoneymanager.controllers.UserController;
import com.kiwi.monthlymoneymanager.controllers.ContactFormController;
import com.kiwi.monthlymoneymanager.controllers.EmailSenderController;
import com.kiwi.monthlymoneymanager.controllers.LoginController;
import com.kiwi.monthlymoneymanager.controllers.UtilsController;

// importing the Database
import com.kiwi.monthlymoneymanager.database.Database;

//importing our exceptions
import com.kiwi.monthlymoneymanager.exceptions.EmailSendingException;
import com.kiwi.monthlymoneymanager.exceptions.WrongPasswordException;
import com.kiwi.monthlymoneymanager.exceptions.DoesNotExistException;

// importing the models just for debugging
import com.kiwi.monthlymoneymanager.models.User;

/**
 *
 * @authors mpetracca, lfabbian
 */


public class Main 
{
    private static final Scanner sc = new Scanner(System.in);
     // Mock Database
    private static double balance = 1000.00; // Initial balance
    private static Map<String, Double> expenses = new HashMap<>(); // Expenses by category
    private static Map<String, Double> budget = new HashMap<>(); // Budget by category

    public static void main(String[] args) 
    {
        System.out.println("Welcome to the MonthlyMoneyManager Web App");
        homePage();
    }
   
    
    
    private static void homePage() 
    {
    String choice;
    do {
        displayHomePageMenu();
        choice = sc.nextLine();
        handleHomePageChoice(choice);
    } while (!choice.equals("q"));
}
    
    private static void userHomePage() 
    {
    Scanner sc = new Scanner(System.in);
    String choice;

    while (true) 
    {  // This loop will keep the user in the main page menu until they decide to log out
        System.out.println("\n--- MonthlyMoney Manager Main Page ---\n");
        System.out.println("1. My MMM");
        System.out.println("2. Analysis");
        System.out.println("3. Edit Budget");
        System.out.println("4. Enter Expenses");
        System.out.println("5. My Profile");
        System.out.println("6. What is it ?");
        System.out.println("7. Team");
        System.out.println("8. Contact");
        System.out.println("q. Log Out");
        System.out.print("Choose an option: ");
        choice = sc.nextLine();
        System.out.print("\n");

        switch (choice) {
            case "1":
                // Display a summary of the user's current balance and top 3 spendings
                displayMyMMM();
                break;

            case "2":
                // Display the user's balance throughout the year
                displayAnalysis();
                break;

            case "3":
                // Allow the user to enter their monthly incomes and set a budget for different expense categories
                editBudget();
                break;

            case "4":
                // Allow the user to enter their actual spendings for different categories with a date
                enterExpenses();
                break;

            case "5":
                // Display the user's account information
                displayProfile();
                break;

            case "q":
                LoginController.userLogsOut();
                LoginController.setPasswordMasking(true);
                System.out.println("Logged out successfully!");
                return;  // Exit the loop and return to the main menu or exit the program
            
            case "6":
                displayWhatIsIt();
                break;
                
            case "7":
                displayTeamInfo();
                break;
            
            case "8":
                handleContactForm();
                break;    
                
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
}
    
    private static double getValidDoubleInput(String prompt) {
    while (true) {
        System.out.print(prompt);
        try 
        {
            return sc.nextDouble();
        } 
        catch (InputMismatchException e) 
        {
            System.out.println("Invalid input. Please enter a valid number.");
            sc.nextLine();
        }
    }
}

    private static void displayMyMMM() {
        System.out.println("Current Balance: $" + balance);
        System.out.println("\nExpenses vs. Budget:");
    
        for (String category : expenses.keySet()) {
            double actualExpense = expenses.getOrDefault(category, 0.0);
            double budgetedAmount = budget.getOrDefault(category, 0.0);
            System.out.println(category + ": $" + actualExpense + " (Budget: $" + budgetedAmount + ")");
        }
    }

    private static void displayAnalysis() {
        System.out.println("Yearly Balance Analysis:");
        // For simplicity, let's just display the current balance
        System.out.println("Current Balance: $" + balance);
    }
    private static boolean salarySet = false;
    private static void displayCurrentBudget() {
    System.out.println("Current Balance: " + balance);
    System.out.println("Budgeted Amounts:");
    for (Map.Entry<String, Double> entry : budget.entrySet()) {
        System.out.println(entry.getKey() + ": " + entry.getValue());
    }
    System.out.println("------------------------------");
}
    private static void editBudget() {
        displayCurrentBudget();
        if (!salarySet) {
            System.out.println("Enter your monthly income or salary:");
            double income = getValidDoubleInput("Income/Salary: ");
            balance = income; // Assuming you have a balance variable to store this value
            sc.nextLine(); // Consume the leftover newline character
            salarySet = true;
        } else {
            System.out.println("Enter additional income to add to your balance:");
            double additionalIncome = getValidDoubleInput("Additional Income: ");
            balance += additionalIncome;
            sc.nextLine(); // Consume the leftover newline character
        }

        System.out.println("Enter budget for categories (type 'done' to finish):");
        while (true) {
            System.out.print("Category: ");
            String category = sc.nextLine();
            if ("done".equalsIgnoreCase(category)) break;
            double amount = getValidDoubleInput("Budget Amount: ");
            sc.nextLine(); // Consume the leftover newline character
            budget.put(category, amount);
        }
        System.out.println("Budget set successfully!");
    }



    private static void enterExpenses() {
        System.out.println("Enter expenses for categories (type 'done' to finish):");
        while (true) {
            System.out.print("Category: ");
            String category = sc.nextLine();
            if ("done".equalsIgnoreCase(category)) break;
            System.out.print("Expense Amount: ");
            double amount = getValidDoubleInput("Enter the amount for " + category + ": ");
            sc.nextLine(); // consume newline
            expenses.put(category, expenses.getOrDefault(category, 0.0) + amount);
            balance -= amount; // deduct from balance
        }
        System.out.println("Expenses entered successfully!");
    } 

    private static void displayProfile() 
    {
        boolean exitProfile = false;
        
        while (!exitProfile) 
        {
            System.out.println("\n--- User Profile ---\n");
            System.out.println("Username: " + LoginController.getUserLoggedIn().getUsername());
            System.out.println("Name: " + LoginController.getUserLoggedIn().getName());
            String passwordDisplay;

            if (LoginController.getPasswordMasking()) 
            {
                passwordDisplay = UtilsController.maskPassword(LoginController.getPassword());
            } else {
                passwordDisplay = LoginController.getPassword();
            }

            System.out.println("Password: " + passwordDisplay);
            System.out.println("Current Balance: $" + balance);

            System.out.println("Options:");
            System.out.println("1. Change Username");
            System.out.println("2. Change Name");
            System.out.println("3. Change Password");
            System.out.println("4. Show/Hide Password"); // Option pour basculer entre masqué et non masqué
            System.out.println("0. Exit");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) 
            {
                case 1:
                    System.out.println("Enter new username: ");
                    String newUsername = scanner.next();
                    UserController.changeUsername(newUsername);
                    break;
                case 2:
                    System.out.println("Enter new name: ");
                    String newName = scanner.next();
                    UserController.changeName(newName);
                    break;
                case 3:
                    System.out.println("Enter current password: ");
                    String currentPassword = scanner.next();

                    if (currentPassword.equals(LoginController.getPassword())) 
                    {
                        System.out.println("Enter a new password: ");
                        String newPassword = scanner.next();
                        System.out.println("Enter again the new password: ");
                        String newPassword2 = scanner.next();

                        if (newPassword.equals(newPassword2)) 
                        {
                            UserController.changePassword(currentPassword, newPassword);
                        } 
                        else 
                        {
                            System.out.println("Error: New password and confirmation do not match.");
                        }
                    } 
                    else 
                    {
                        System.out.println("Error: Current password is incorrect.");
                    }
                    break;
                case 4:
                    LoginController.setPasswordMasking(!LoginController.getPasswordMasking()); 
                    break;
                case 0:
                    exitProfile = true;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }



    private static void displayHomePageMenu() {
        System.out.println("\n--- MonthlyMoney Manager ---\n");
        System.out.println("Enter:"
                + "\n[q] to quit the application"
                + "\n[1] What is it ?"
                + "\n[2] Team"
                + "\n[3] Contact"
                + "\n[4] Sign up or Login");
        System.out.print("Choose an option: ");
    }

    private static void handleHomePageChoice(String choice) {
        switch (choice) {
            case "1":
                displayWhatIsIt();
                break;
            case "2":
                displayTeamInfo();
                break;
            case "3":
                handleContactForm();
                break;
            case "4":
                handleSignUpOrLogin();
                break;
            case "q":
                System.out.println("Quitting the application... Thank you for using MMM !");
                break;
            default:
                System.out.println("Choice = " + choice + " does not exist.");
                break;
        }
    }

    private static void displayWhatIsIt() {
        System.out.println("""
                           Welcome to MonthlyMoney Manager! 
                           
                           MonthlyMoney Manager is your personal financial assistant, designed to help you effortlessly manage and track your monthly budget. With our user-friendly interface, you can:
                           
                           1. **Input Your Finances**: Easily enter your monthly income and expenses to get a clear picture of your financial standing.
                           2. **Analysis**: Access detailed graphs and breakdowns to understand your spending habits.
                           3. **Set Financial Goals**: Define and track your financial objectives, ensuring you stay motivated and on the right path.
                           4. **Access Monthly Breakdowns**: Dive deep into your month-by-month financial habits to make informed decisions.
                           5. **Customize & Get Support**: Tailor the app settings to your preference and access our help & support anytime you need.
                           
                           Take control of your finances today with MonthlyMoney Manager!"""); // rest of the message
    }

    private static void displayTeamInfo() {
        System.out.println("""
                            **Meet the Team Behind MonthlyMoney Manager** 
                           
                           At MonthlyMoney Manager, we're proud to have a dedicated team of professionals who are passionate about helping you manage your finances. Here's a brief introduction to our talented team:
                           
                           ---
                           
                           **Laura Fabbiano** [picture of Laura] - Developer
                           Laura is the coding genius behind the seamless functionality of MonthlyMoney Manager. With her expertise in software development, she ensures that the platform is user-friendly, efficient, and bug-free.
                           
                           ---
                           
                           **Laurent Sierro** [picture of Laurent] - Founder
                           The visionary behind MonthlyMoney Manager, Laurent identified the need for a simple yet effective budgeting tool and took the initiative to bring this idea to life. His leadership and passion drive the team forward.
                           
                           ---
                           
                           **Melvin Petracca** [picture of Melvin] - CTO (Chief Technical Officer)
                           Overseeing the technical aspects of MonthlyMoney Manager, Melvin ensures that the platform is built on a robust and scalable architecture. His technical insights have been instrumental in shaping the app's success.
                           
                           ---
                           
                           **Tigran Grishkian** [picture of Tigran] - CFO (Chief Financial Officer)
                           With a keen eye for numbers, Tigran manages the financial health of MonthlyMoney Manager. His expertise ensures that the company remains financially strong while delivering top-notch services to its users.
                           
                           ---
                           
                           We're here to provide you with the best budgeting experience. If you have any questions or feedback, please don't hesitate to reach out!"""); // rest of the message
    }

    private static void handleContactForm() 
    {
        String cf_name, cf_email, cf_message;
        System.out.println("\nNeed some help ? Just contact us ! :-) \n");

        System.out.print("Enter your name: ");
        cf_name = sc.nextLine();

        System.out.print("Enter your email: ");
        cf_email = sc.nextLine();

        System.out.print("Enter your message: ");
        cf_message = sc.nextLine(); //rajouter comment on va envoyer ce message
        
        ContactFormController.setCFName(cf_name);
        ContactFormController.setCFMessage(cf_message);
        ContactFormController.setCFEmail(cf_email);
        
        ContactFormController.createForm();
        
        // you can try to send an e-mail to us - we really receive it :)
        try 
        {
            EmailSenderController.sendEmail("monthlymoneymanager@hotmail.com",cf_email, "MonthlyMoneyManager Contact Form", cf_message);
            System.out.println("E-mail sent successfully!");
            
            System.out.println("\nThank you, " + cf_name + ". Press 'Enter' to send your message.");
            sc.nextLine();  // Wait for user to press Enter

            System.out.println("Your message has been sent! We'll get back to you at " + cf_email + " soon.");
            
        } catch (EmailSendingException e) {
            System.err.println(e.getMessage());
        }

    }

    private static void handleSignUpOrLogin() 
    {
        System.out.println("1. Sign Up");
        System.out.println("2. Sign In");
        System.out.print("Choose an option: ");
        String subChoice = sc.nextLine();
        handleSubChoice(subChoice);
    }

    private static void handleSubChoice(String subChoice) 
    {
        switch (subChoice) {
            case "1":
                handleSignUp();
                break;
            case "2":
                handleSignIn();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void handleSignUp() 
    {
        String su_username, su_password, su_name, su_email, su_password_confirmation;
        System.out.println("-- Sign Up Page --");
        
        System.out.println("Enter a username:");
        su_username = sc.nextLine();
        
        System.out.println("Enter your name:");
        su_name = sc.nextLine();
        
        System.out.println("Enter an email:");
        su_email = sc.nextLine();
        
        System.out.println("Enter a password:");
        su_password = sc.nextLine();
        
        System.out.println("Confirm password:");
        su_password_confirmation = sc.nextLine();
        
        try {
            if (su_password.equals(su_password_confirmation)) {
                UserController.setUsername(su_username);
                UserController.setName(su_name);
                UserController.setEmail(su_email);
                UserController.setPassword(su_password);
                UserController.createUser();
                //userHomePage(); // Directly navigate to the homepage after successful sign up
                
            } 
            else 
            {
                throw new WrongPasswordException("Error: Wrong password !");
            }
        } 
        catch (WrongPasswordException e) 
        {
            System.out.println(e.getMessage());
        } 
        catch (DoesNotExistException e) 
        {
            System.out.println(e.getMessage());
        }
    }

    private static void handleSignIn() {
        String si_username, si_password;
        
        System.out.println("-- Sign In Page --");
        System.out.println("Enter your username:");
        si_username = sc.nextLine();
        System.out.println("Enter your password:");
        si_password = sc.nextLine();
        
        LoginController.setUsername(si_username);
        LoginController.setPassword(si_password);
        
        LoginController.userLogsIn();
        if (LoginController.getUserLoggedIn() != null) 
        {
            userHomePage();
        }  
    }
}