import java.util.Scanner;

public class LeapYear 
{
    public static boolean isLeap(int year) {
        if (year % 400 == 0) return true;
        if (year % 100 == 0) return false;
        if (year % 4 == 0) return true;
        return false;
    }
    
    public static void main(String[] args) 
    {
        System.out.print("Give us a year: ");
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        System.out.println("isLeap(" + year + ") = " + isLeap(year));
 }
}