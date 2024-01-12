public class Book extends Medium 
{
    private final String author;

    public Book(String title, String author) {
        super(title);
        this.author = author;
    }
}