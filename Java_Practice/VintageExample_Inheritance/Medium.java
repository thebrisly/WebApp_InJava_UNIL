public class Medium 
{
    private final String title;
    private int rating;

    public Medium(String title) {
        this.title = title;
        this.rating = -1;
    }

    public void rate(int rating) {
        this.rating = rating;
    }
    public void printInfo() 
    {
        String rating = "?";
        if (this.rating >= 0) 
        {
            rating = "";
            for (int i = 0; i < this.rating; i++) {
                rating = rating + "*";
            }
        }
        System.out.println("Medium[title: " + this.title + " | rating: " + rating + "]");
    }
}