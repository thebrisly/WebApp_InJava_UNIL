public class Book 
{
    private final String title;
    private final String author;
    private int rating;

    public Book(String title, String author) 
    {
        this.title = title;
        this.author = author;
        this.rating = -1;
    }

    public void rate(int rating) 
    {
        this.rating = rating;
    }

    public void printInfo() 
    {
        String rating = "?";
        if (this.rating >= 0) {
            rating = "";
            for (int i = 0; i < this.rating; i++) {
                rating = rating + "*";
            }
        }
        System.out.println("Book[title: " + this.title + " | author: " + this.author + " | rating: " + rating + "]");
    }
}
