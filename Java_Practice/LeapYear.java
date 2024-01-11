public class LeapYear {

    public static void main(String[] args) {
        int year = 2024; // Remplacez cela par l'année que vous souhaitez tester
        boolean isLeap = isLeapYear(year);

        if (isLeap) {
            System.out.println(year + " est une année bissextile.");
        } else {
            System.out.println(year + " n'est pas une année bissextile.");
        }
    }

    public static boolean isLeapYear(int year) {
        boolean isLeap;

        if (year % 400 == 0) {
            isLeap = true;
        } else if (year % 100 == 0) {
            isLeap = false;
        } else if (year % 4 == 0) {
            isLeap = true;
        } else {
            isLeap = false;
        }

        return isLeap;
    }
}
