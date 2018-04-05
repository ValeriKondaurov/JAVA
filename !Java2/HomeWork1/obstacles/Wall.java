package obstacles;
import animals.*;
/**
 * Write a description of class Wall here.
 *
 * @author Valeriy Kondaurov
 * @version dated Dec 21, 2017
 */
public class Wall extends Obstacles{
    private float height;

    public Wall(float height) {
        this.name = "Wall";
        this.height = height;
    }

    public boolean doIt(Animal animal) {
        if (animal instanceof Jumpable)
            return ((Jumpable) animal).jump(height);
        else
            return false;
    }

}