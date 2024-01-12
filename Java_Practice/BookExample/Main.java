public class Main {

    public static void main(String[] args) 
    {
        Catalog c = new Catalog();
        Book b1 = new Book("Amphitryon", "Molière");
        b1.rate(3);
        Book b2 = new Book("Hamlet", "Shakespeare");
        c.addBook(b1);
        c.addBook(b2);
        c.listInfo();
    }
}