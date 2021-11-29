package s01.thread_management.s05;

import java.util.Date;
import java.util.Deque;

public class CleanerTask extends Thread {

    public static final int TIME = 3_000;
    private final Deque<Event> events;

    public CleanerTask(Deque<Event> events) {
        this.events = events;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            var date = new Date();
            cleanEvents(date);
        }
    }

    private void cleanEvents(Date date) {
        if (events.isEmpty()) {
            return;
        }
        long dif;
        boolean removed = false;
        do {
            if (events.isEmpty()) {
                return;
            }
            Event last = events.getLast();
            dif = date.getTime() - last.getDate().getTime();

            if (dif > TIME) {
                System.out.printf("Clean event: %s\n", last.getEvent());
                events.removeLast();
                removed = true;
            }
        } while (dif > TIME);

        if (removed) {
            System.out.printf("Events remaining: %d\n", events.size());
        }
    }

}
