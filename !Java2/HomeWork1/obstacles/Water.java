package obstacles;
import animals.*;
/**
 * Write a description of class Water here.
 *
 * @author Valeriy Kondaurov
 * @version dated Dec 21, 2017
 */
public class Water extends Obstacles{
    private int length;

    public Water(int length) {
        this.name = "Water";
        this.length = length;
    }

    public boolean doIt(Animal animal) {
        if (animal instanceof Swimable)
            return ((Swimable) animal).swim(length);
        else
            return false;
    }
}