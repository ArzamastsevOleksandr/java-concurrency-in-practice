package s01.thread_management.s05;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {

    public static void main(String[] args) {
        Deque<Event> events = new ConcurrentLinkedDeque<>();
        Thread thread = new Thread(new WriterTask(events));
        thread.start();
        new CleanerTask(events).start();
    }

}
