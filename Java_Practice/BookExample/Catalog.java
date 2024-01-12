import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void listInfo() {
        System.out.println("BOOKS");
        for (Book book : books) {
            book.printInfo();
        }
    }
}
