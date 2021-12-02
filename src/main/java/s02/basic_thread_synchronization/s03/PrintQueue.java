package s02.basic_thread_synchronization.s03;

import util.ThreadUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {

    private final Lock lock;

    public PrintQueue(boolean isFair) {
        this.lock = new ReentrantLock(isFair);
    }

    public void printJob(Object o) {
        printJob();
        printJob();
    }

    private void printJob() {
        lock.lock();
        long duration = (long) (Math.random() * 4_000);
        System.out.println(Thread.currentThread().getName() + " in printing a job for : " + duration / 1000 + " s");
        try {
            ThreadUtil.sleepMillis(duration);
        } finally {
            System.out.println(Thread.currentThread().getName() + ": has printed a job");
            lock.unlock();
        }
    }

}
