package obstacles;
import animals.Animal;
/**
 * Write a description of class Obstacles
 *
 * @author Valeriy Kondaurov
 * @version dated Dec 21, 2017
 */


public abstract class Obstacles {
    protected String name;

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " " + this.name;
    }

    public abstract boolean doIt(Animal a);
}
