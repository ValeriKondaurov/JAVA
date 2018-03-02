/**
 * Organize races:
 All participants must start at the same time, despite the fact that the preparation of
 each of them takes a different time
 The tunnel can't come around at the same time, more than half of the participants(conditional)
 Try synchronizing all of this
 Only after all finish the race you need to issue an announcement of the end
 Can adjust the classes (including the designer of the machines) and add features
 classes from the util package.concurrent
 Once the first of the cars crosses the finish line, it must be declared
 the winner (the winner must be only one)
 *
 * @author Valeriy Kondaurov
 * @version dated MAR 02, 2018
 * @link https://github.com/ValeriKondaurov/Java3
 */


import java.util.ArrayList;
import java.util.Arrays;




public class J3HW5Race implements J3HW5IConstants {
    private static volatile boolean winner = false;
    public static void main(String[] args) {
        System. out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[ CARS_COUNT];
        Thread t[] = new Thread[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            final int w = i;
            t[i] = new Thread(()-> {
                try {
                    cars[w] = new Car(race, 20 + (int) (Math. random() * 10));
                    System.out.println(cars[w].getName() + " готовится");
                    Thread.sleep(500 + (int) (Math.random() * 800));
                    System.out.println(cars[w].getName() + " готов");
                    cb.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            t[i].start();
        }
        do {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println("Мain thread is interrupted.");
            }
        } while (talive(t));

        System. out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        for (int i = 0; i < cars.length; i++) {
            t[i] = new Thread(cars[i]);
            t[i].start();
        }
        do {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println("Мain thread is interrupted.");
            }
        } while (talive(t));
        System. out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

    public static boolean talive(Thread[] t) {
        for (int i=0;i<t.length; i++)
            if (t[i].isAlive()) return true;
         return false;
    }
}
class Car implements Runnable, J3HW5IConstants {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this. race = race;
        this. speed = speed;
        CARS_COUNT++;
        this. name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        System.out.println(this.getName() + " на финише");
        try {
            fihishcars.acquire();
            synchronized (FIHISH) {
                if (!FIHISH[0]) {
                    FIHISH[0] = true;
                    System.out.println(this.getName() + " WIN ");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally { synchronized (FIHISH) {
            fihishcars.release();}
        }
    }
}

abstract class Stage implements J3HW5IConstants {
    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}
class Road extends Stage {
    public Road(int length) {
        this. length = length;
        this. description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            System. out.println(c.getName() + " начал этап: " + description);
            Thread. sleep(length / c.getSpeed() * 1000);
            System. out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Tunnel extends Stage {
    public Tunnel() {
        this. length = 80;
        this. description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
       int  tunnelNumber = -1;
            try {
                tunnelcars.acquire();
                System. out.println(c.getName() + " готовится к этапу(ждет): " + description);
                synchronized (TUNNEL_PLACES) {
                    for (int i = 0; i < TUNNEL_CARS; i++)
                        if (!TUNNEL_PLACES[i]) {
                            TUNNEL_PLACES[i] = true;
                            tunnelNumber=i;
                            System. out.println(c.getName() + " начал этап: " + description);
                            break;
                        }
                }
                Thread. sleep(length / c.getSpeed() * 1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                synchronized (TUNNEL_PLACES) {
                    TUNNEL_PLACES[tunnelNumber] = false; // освобождаем место
                }
                System. out.println(c.getName() + " закончил этап: " + description);
                tunnelcars.release(); // освобождаем ресурс

            }

    }
}
class Race {
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Race(Stage... stages) {
        this. stages = new ArrayList<>(Arrays. asList(stages));
    }
}
