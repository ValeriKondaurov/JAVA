package animals;
/**
 * Abstract class Animal - write a description of the class here
 *
 * @author Valeriy Kondaurov
 * @version dated Dec 21, 2017
 */
public abstract class Animal {
    protected String name;
    protected int run_limit;
    protected boolean coursecheck=false;

    public abstract String voice();

    public boolean run(int length) {
        return run_limit >= length;
    }
    
    @Override
    public String toString() {
        return this.getClass().getName() + " " + name ;
    }

    public void setCoursecheck(boolean coursecheck) {
        this.coursecheck = coursecheck;
    }

    public boolean isCoursecheck() {
        return coursecheck;
    }

}