package s02.basic_thread_synchronization.s02;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class EventStorage {

    private final Queue<Date> events = new LinkedList<>();

    public synchronized void add() {
        while (events.size() == 10) {
            try {
                System.out.println("ADD WAIT");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        events.offer(new Date());
        System.out.printf("Set. Size: %d\n", events.size());
        notify();
    }

    public synchronized void get() {
        while (events.isEmpty()) {
            try {
                System.out.println("GET WAIT");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String element = events.poll().toString();
        System.out.printf("Get %s. Size: %d\n", element, events.size());
        notify();
    }

}
