import java.util.*;
import java.util.concurrent.Semaphore;

public class SenateBusProblem {
    public static void main(String[] args) {
        Semaphore mutex = new Semaphore(1);
        Semaphore bus = new Semaphore(0);
        Semaphore boarded = new Semaphore(0);
        WaitingCounter waiting = new WaitingCounter(0);

        // Initialize user input to exit the system
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter key to exit at anytime.\n");

        // Generate Riders
        new Thread(new RiderGenerator(mutex, bus, boarded, waiting)).start();

        // Generate Buses
        new Thread(new BusGenerator(mutex, bus, boarded, waiting)).start();

        // Check user input to exit the system
        try {
            if (scanner.hasNextLine()) {
                scanner.close();
                System.exit(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            scanner.close();
            System.exit(1);
        }
    }
}