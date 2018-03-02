import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

interface J3HW5IConstants {
    int CARS_COUNT = 4;
    int TUNNEL_CARS = CARS_COUNT/2;
    CyclicBarrier cb = new CyclicBarrier(CARS_COUNT);
    Semaphore tunnelcars = new Semaphore(TUNNEL_CARS, true);
    boolean[] TUNNEL_PLACES = new boolean[TUNNEL_CARS];
    Semaphore fihishcars = new Semaphore(1, true);
    boolean[] FIHISH = new boolean[1];

}
