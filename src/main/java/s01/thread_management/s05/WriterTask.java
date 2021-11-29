package s01.thread_management.s05;

import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class WriterTask implements Runnable {

    private final Deque<Event> events;

    @Override
    public void run() {
        for (int i = 0; i < 8; ++i) {
            var event = new Event(new Date(), "Event by thread " + Thread.currentThread().getId());
            events.addFirst(event);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
