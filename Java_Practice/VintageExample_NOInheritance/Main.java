public class Main {
    public static void main(String[] args) {
        Catalog c = new Catalog();

        Book b1 = new Book("Amphitryon", "Molière");
        b1.rate(3);

        Book b2 = new Book("Hamlet", "Shakespeare");

        c.addBook(b1);
        c.addBook(b2);

        Vinyl v1 = new Vinyl("Abbey Road", "Beatles", 47);
        Vinyl v2 = new Vinyl("Synchronicity", "Police", 40);

        c.addVinyl(v1);
        c.addVinyl(v2);

        c.listInfo();
    }
}
