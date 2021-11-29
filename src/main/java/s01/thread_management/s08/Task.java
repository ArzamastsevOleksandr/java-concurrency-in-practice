package s01.thread_management.s08;

import java.util.Random;

public class Task implements Runnable {

    @Override
    public void run() {
        Random random = new Random(Thread.currentThread().getId());
        while (true) {
            int i = 1 / ((int) (random.nextDouble() * 1000000000));
            if (Thread.currentThread().isInterrupted()) {
                System.out.printf("Thread %d interrupted\n", Thread.currentThread().getId());
                return;
            }
        }
    }

}
