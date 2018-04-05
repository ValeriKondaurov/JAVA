import animals.*;
import obstacles.*;
/**
 * Write a description of class Course.
 *
 * @author Valeriy Kondaurov
 * @version dated Dec 21, 2017
 */
public class Course {
    protected Obstacles[] course = new Obstacles[4];

    public Course (Obstacles c[]) {

        for (int i=0; i<4; i++) {
            this.course[i] = c[i];
        }
    }
    public void dolt(Team team) {//прохождение дистации участниками команды
        boolean check;
        for (Animal a:team.animalteam) {
            check = true;
            for (Obstacles c:course) {
                        check=check&&c.doIt(a);
                }
            a.setCoursecheck(check);
            }
    }

}

