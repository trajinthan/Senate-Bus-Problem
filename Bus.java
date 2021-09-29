import java.util.concurrent.Semaphore;

public class Bus implements Runnable {
    private int id; // ID of the bus
    private int capacity = 50; // maximum capacity of the bus
    private Semaphore mutex;
    private Semaphore bus;
    private Semaphore boarded;
    public WaitingCounter waiting;

    public Bus(int id, Semaphore mutex, Semaphore bus, Semaphore boarded, WaitingCounter waiting) {
        this.id = id;
        this.mutex = mutex;
        this.bus = bus;
        this.boarded = boarded;
        this.waiting = waiting;
    }

    // print bus has arrived (with ID)
    public void arrive() {
        System.out.println("\n" + "Bus " + "(ID: " + id + ")" + " has arrived!" + " --- " + waiting.countToString());
    }

    // print bus has departed (with ID)
    public void depart() {
        System.out.println("Bus " + "(ID: " + id + ")" + " has departed!" + " --- " + waiting.countToString() + "\n");
    }

    @Override
    public void run() {
        try {
            mutex.acquire();
            arrive();
            int n = Math.min(waiting.getCount(), capacity);

            for (int i = 0; i < n; i++) {
                bus.release();
                boarded.acquire();
            }

            waiting.setCount(Math.max(waiting.getCount() - capacity, 0));
            mutex.release();

            depart();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
