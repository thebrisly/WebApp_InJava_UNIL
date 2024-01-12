public class Main {
    public static void main(String[] args) 
    {
        Catalog c = new Catalog();
        Book b = new Book("Amphitryon", "Molière");
        Vinyl v = new Vinyl("Abbey Road", "Beatles", 47);
        b.rate(3);
        c.addMedium(b);
        c.addMedium(v);
        c.listInfo();
    }
}