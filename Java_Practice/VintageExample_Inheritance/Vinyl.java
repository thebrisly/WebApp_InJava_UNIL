public class Vinyl extends Medium 
{
    private final String artist;
    private final int duration;

    public Vinyl(String title, String artist, int duration) {
        super(title);
        this.artist = artist;
        this.duration = duration;
    }
}