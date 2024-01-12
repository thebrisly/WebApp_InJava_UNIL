import java.util.ArrayList;
import java.util.List;

public class Catalog 
{
    private final List<Medium> media = new ArrayList<Medium>();

    public void addMedium(Medium medium) {
        this.media.add(medium);
    }

    public void listInfo() 
    {
        System.out.println("MEDIA");
        for (Medium medium : media) {
            medium.printInfo();
        }
    }
}