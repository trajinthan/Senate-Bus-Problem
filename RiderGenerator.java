import java.util.*;
import java.util.concurrent.Semaphore;

public class RiderGenerator implements Runnable {
    private Semaphore mutex;
    private Semaphore bus;
    private Semaphore boarded;
    public WaitingCounter waiting;

    int i = 1;
    Random r = new Random();
    int minInterval = (int) (1000 * 0.05); // minimum interval for next rider arrival to the bus stop
    int maxInterval = (int) (1000 * 0.25); // maximum interval for next rider arrival to the bus stop

    public RiderGenerator(Semaphore mutex, Semaphore bus, Semaphore boarded, WaitingCounter waiting) {
        this.mutex = mutex;
        this.bus = bus;
        this.boarded = boarded;
        this.waiting = waiting;
    }

    @Override
    public void run() {
        while (true) {
            new Thread(new Rider(i++, mutex, bus, boarded, waiting)).start();
            try {
                Thread.sleep((int) (r.nextInt(maxInterval - minInterval) + minInterval));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}