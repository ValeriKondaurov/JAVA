package animals;
/**
 * Write a description of class Hen here.
 *
 * @author Valeriy Kondaurov
 * @version dated Dec 21, 2017
 */
public class Hen extends Animal implements Jumpable {
    private float jump_limit;

    public Hen(String name) {
        this.name = name;
        this.run_limit = 100;
        jump_limit = 10f;
    }

    public String voice() {
        return "ko-ko-ko";
    }

    public boolean jump(float height) {
        return jump_limit >= height;
    }
}