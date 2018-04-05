package animals;
/**
 * Write a description of class Hippo here.
 *
 * @author Valeriy Kondaurov
 * @version dated Dec 21, 2017
 */
public class Hippo extends Animal implements Swimable {
    private int swim_limit;

    public Hippo(String name) {
        this.name = name;
        this.run_limit = 50;
        swim_limit = 200;
    }

    public String voice() {
        return "uf-uf-uf";
    }

    public boolean swim(int length) {
        return swim_limit >= length;
    }
}