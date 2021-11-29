package s01.thread_management.s07;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SafeTask implements Runnable {

    private final ThreadLocal<Date> start = ThreadLocal.withInitial(Date::new);

    @Override
    public void run() {
        System.out.printf("Start thread %s at %s\n", Thread.currentThread().getId(), start.get());
        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Finish thread %s with start time %s\n", Thread.currentThread().getId(), start.get());
    }

}
