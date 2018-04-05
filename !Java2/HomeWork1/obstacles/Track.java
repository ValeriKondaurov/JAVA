package obstacles;
import animals.*;
/**
 * Write a description of class Track here.
 *
 * @author Valeriy Kondaurov
 * @version dated Dec 21, 2017
 */
public class Track extends Obstacles{
    private int length;

    public Track(int length) {
        this.name = "Track";
        this.length = length;
    }

    public boolean doIt(Animal animal) {
        return animal.run(length);
    } 
}