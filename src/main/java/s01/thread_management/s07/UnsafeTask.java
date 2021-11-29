package s01.thread_management.s07;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UnsafeTask implements Runnable {

    private Date start;

    @Override
    public void run() {
        start = new Date();
        System.out.printf("Start thread %s at %s\n", Thread.currentThread().getId(), start);
        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Finish thread %s with start time %s\n", Thread.currentThread().getId(), start);
    }

}
