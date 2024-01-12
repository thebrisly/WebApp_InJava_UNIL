public class VariableScopeExample {
    // Variable globale
    static String place = "Earth";

    public static void main(String[] args) {
        continent();
        System.out.println("your planet is " + place); // OUTPUT : "Earth"
    }

    static void continent() {
        // Variable dans la fonction continent()
        String place = "Europe";

        country();
        System.out.println("your continent is " + place); // OUTPUT : "Europe"
    }

    static void country() {
        // Variable dans la fonction country()
        String place = "Switzerland";
        System.out.println("your country is " + place); // OUTPUT : "Switzerland"
    }
}
