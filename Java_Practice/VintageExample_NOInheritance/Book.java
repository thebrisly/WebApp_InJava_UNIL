public class Book 
{
    private final String author;
    private final String title;
    private int rating;

    public Book(String title, String author) {
        this.author = author;
        this.title = title;
        this.rating = 0;
    }

    public void rate(int rating) {
        this.rating = rating;
    }

    public void printInfo() {
        System.out.println("Name of the book : " + this.title + ", By : " + this.author);
        if (this.rating > 0) {
            System.out.print("Rating of the book : ");
            for (int i = 0; i < this.rating; i++) {
                System.out.print("*");
            }
            System.out.println("");
        }
        else 
            System.out.println("This book has never been rated. Use the methode rate() to do it ");
    }

}