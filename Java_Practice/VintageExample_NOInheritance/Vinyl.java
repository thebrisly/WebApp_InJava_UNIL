public class Vinyl 
{

    private final String title;
    private final String artist;
    private final int duration;
    private int rating;

    // CONSTRUCTOR
    public Vinyl(String title, String artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.rating = -1;
    }

    public void rate(int rating) {
        this.rating = rating;
    }

    public void printInfo() {
        String rating = "?";
        if (this.rating >= 0) {
            rating = "";
            for (int i = 0; i < this.rating; i++) {
                rating = rating + "*";
            }
        }
        System.out.println("Vinyl[title: " + this.title + " | artist: " + this.artist +
        " | duration: " + this.duration + " minutes | rating: " + rating + "]");
    }

}