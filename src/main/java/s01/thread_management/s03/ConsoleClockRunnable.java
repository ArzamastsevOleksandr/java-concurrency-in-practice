package s01.thread_management.s03;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class ConsoleClockRunnable implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.printf("Time: %s\n", LocalDateTime.now());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Clock has been interrupted");
                return;
            }
        }
    }

}
