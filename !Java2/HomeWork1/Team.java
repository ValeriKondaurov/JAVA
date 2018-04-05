import animals.*;
/**
 * Write a description of class Team.
 *
 * @author Valeriy Kondaurov
 * @version dated Dec 21, 2017
 */
public class Team {
    protected String nameteam;
    protected Animal [] animalteam = new Animal[4];

    public Team (String name, Animal animal[]) {
        this.nameteam = name;
        animalteam = animal;
        /*for (int i=0; i<4; i++) {
            animalteam[i] = animal[i];
        }*/
   }
    public void teamInfo() {//вывод данных о команды
        System.out.println("Info about Team.");
        System.out.println(" Name  "+ this.nameteam);
        for (Animal a:this.animalteam) {
            System.out.println(a);
        }
        System.out.println("");
    }

    public boolean isCoursecheckAll() {//проверка прохождение дистанции хоть одни членом командры
        boolean c=false;
        for (Animal a:this.animalteam) {
            if (a.isCoursecheck()) c=c||a.isCoursecheck();
        }
        return c;
    }

    public void showResults() {//вывод членой команды прошедших дистацию
        System.out.println("In team " + this.nameteam + " obstacle" + (isCoursecheckAll()?" overcome: ":" not overcome"));
                for (Animal a:this.animalteam) {
                    if (a.isCoursecheck()) System.out.println(a);
                }
        System.out.println("");
    }

}