package s01.thread_management.s04;

import java.util.concurrent.TimeUnit;

public class WaitForMeRunnable implements Runnable {

    @Override
    public void run() {
        System.out.printf("Thread %s started\n", Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.printf("Thread %s interrupted\n", Thread.currentThread().getName());
        }
        System.out.printf("Thread %s finished\n", Thread.currentThread().getName());
    }

}
