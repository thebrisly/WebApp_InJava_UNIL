public class TestsExam {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";
    public static final String BLUE = "\u001B[34m";


    /* All tests will be done in the following "main" function : */
    public static void main(String[] args) 
    {
        explicit_implicit_typing();
        explicit_types_with_cast();
        constant_variables();
        boolean_understanding();
        command_line_args(args);
        errors_handling();
    }



    /* --------------- TEST TO UNDERSTAND IMPLICIT & EXPLICIT TYPING --------------- */
    public static void explicit_implicit_typing() {

        System.out.println(PURPLE + "TEST 1 - "  + RESET + "Understanding the implicit VS explicit typing \n");

        int i = 20;                     // EXPLICIT TYPING for an integer
        var j = 20;                     // IMPLICIT TYPING for an integer variable

        System.out.println("Type of i: " + ((Object) i).getClass().getSimpleName());
        System.out.println("Type of j: " + ((Object) j).getClass().getSimpleName() + "\n");

        // As you can see with the above code, both variables will be of type "int"
        // On the first line (21) you will EXPLICITLY tell your programm the type of the variable (int)
        // And on the second line (22) the compiler will interfer the type of the variable (he will know that 22 corresponds to an int)

        var a = 3.4;                    // IMPLICIT TYPING for a double variable
        var b = 3.3f;                   // IMPLICIT TYPING for a floating variable

        System.out.println("Type of a: " + ((Object) a).getClass().getSimpleName());
        System.out.println("Type of b: " + ((Object) b).getClass().getSimpleName() + "\n");
    }



    /* --------------- TEST TO UNDERSTAND CASTING --------------- */
    public static void explicit_types_with_cast() {

        System.out.println(PURPLE + "\n TEST 2 - "  + RESET + "Converting an int to a double with casting \n");


        int i = 20;                     // EXPLICIT TYPING
        System.out.println(BLUE + "INITIAL INT value : " + RESET + i);

        double d = 3.4;
        System.out.println(BLUE + "INITIAL DOUBLE value : " + RESET + d);    

        // The following line (31) will NOT work and will get you a compilation error because the variable i can not support a value of type "double" as it has been defined as an int. --> you can try it be decommenting th next line.

        // i = d

        // To solve the problem you can cast the double value d into an int :

        i = (int) d;
        System.out.println(GREEN + "NEW INT value " + RESET + "(after casting) : " + i);

        // you could also transform this value into a String ouf you wanted :

        String string = Double.toString(d);
    }



    public static void constant_variables() {
        System.out.println(PURPLE + "\n TEST 3 - "  + RESET + "Test to understand constant variables \n");

        int normal_variable = 10;               
        final int constant_variable = 20; // The tag final indicates that this variable is constant

        // first let's print the values of these two variables :
        System.out.println(BLUE + "Normal variable value : " + RESET + normal_variable);
        System.out.println(BLUE + "Constant variable value : " + RESET + constant_variable);

        // if you want to change the value of a non constant variable, you can do easily as following :

        normal_variable = 30;

        // Let's print its new value :
        System.out.println(BLUE + "NEW Normal variable value : " + RESET + normal_variable);

        // but now, if you wanna change the value of a constant variable, it will NOT be possible. 

        // if you try to decomment the following line (94), you will get a compilation error "error: cannot assign a value to final variable constant_variable"

        //constant_variable = 1;
    }



    public static void boolean_understanding() {

        System.out.println(PURPLE + "\n TEST 4 - "  + RESET + "Understanding the boolean variables \n");


        // Change the following values, compile, run & see which result you obtain :)
        boolean a = false;
        boolean b = true;

        if (a && b)
            System.out.println("&& : If this line appears, that means that the variable 'a' & 'b' are BOTH true !");
        if (a || b)
            System.out.println("|| : If this line appears, that means that between the variabls 'a' & 'b' it exists AT LEAST ONE variable that is true ! ");
        if (a != b)
            System.out.println("!= : If this line appears, that means that the variable 'a' & 'b' have not the same values");

    }



    public static void command_line_args(String[] args){

        System.out.println(PURPLE + "\n TEST 5 - "  + RESET + "Understanding command line arguments \n");

        if (args.length == 0)
            System.out.println("0 arguments have been given :/ ");
        else if (args.length == 1)
            System.out.println("1 argument has been given ! It's : " + args[0]);
        else
            System.out.println("A lot of arguments have been given !");
    }

    
    /* --------------- TEST TO UNDERSTAND ERRORS HANDLING WITH CATCH, TRY & THROW --------------- */
    public static void errors_handling() {
        System.out.println(PURPLE + "\n TEST 6 - "  + RESET + "Errors handling \n");

        int balance = 300;

        try 
        {
            // Attempt to withdraw an amount greater than the balance
            withdrawMoney(balance, 400);
        } 

        catch (RuntimeException e) 
        {
            System.out.println("Error: " + e.getMessage());
        } 
        
        finally { System.out.println("This message will be added at the end"); }

    }

    private static void withdrawMoney(int currentBalance, int withdrawalAmount) {
        if (withdrawalAmount > currentBalance) {
            throw new RuntimeException("Insufficient funds for withdrawal.");
        }

        // Perform the withdrawal if all conditions are met
        currentBalance -= withdrawalAmount;
        System.out.println("Withdrawal of " + withdrawalAmount + " successful. New balance: " + currentBalance);
    }
    


}
