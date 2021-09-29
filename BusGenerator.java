import java.util.*;
import java.util.concurrent.Semaphore;

public class BusGenerator implements Runnable {
    private Semaphore mutex;
    private Semaphore bus;
    private Semaphore boarded;
    public WaitingCounter waiting;

    int i = 1;
    Random r = new Random();
    int minInterval = (int) (1000 * 2.5); // minimum interval for next bus arrival to the bus stop
    int maxInterval = (int) (1000 * 10); // maximum interval for next bus arrival to the bus stop

    public BusGenerator(Semaphore mutex, Semaphore bus, Semaphore boarded, WaitingCounter waiting) {
        this.mutex = mutex;
        this.bus = bus;
        this.boarded = boarded;
        this.waiting = waiting;
    }

    @Override
    public void run() {
        while (true) {
            new Thread(new Bus(i++, mutex, bus, boarded, waiting)).start();
            try {
                Thread.sleep((int) (r.nextInt(maxInterval - minInterval) + minInterval));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}