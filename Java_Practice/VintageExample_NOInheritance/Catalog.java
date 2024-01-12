import java.util.ArrayList;
import java.util.List;

public class Catalog 
{
    private final List<Book> books = new ArrayList<Book>();
    private final List<Vinyl> vinyls = new ArrayList<Vinyl>();

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void addVinyl(Vinyl vinyl) {
        this.vinyls.add(vinyl);
    }

    public void listInfo() {
        System.out.println("BOOKS");
        for (Book book : books) {
            book.printInfo();
        }
        System.out.println("------");
        System.out.println("VINYLS");
        for (Vinyl vinyl : vinyls) {
            vinyl.printInfo();
        }
    }
}