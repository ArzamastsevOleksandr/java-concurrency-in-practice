package s03.thread_synchronization_utilities.s01;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class PrintQueue {

    private final Lock lock = new ReentrantLock();
    private final Semaphore semaphore;
    private final Boolean[] freePrinters;

    public PrintQueue(int printers) {
        semaphore = new Semaphore(printers, true);
        freePrinters = freePrinters(printers);
    }

    private Boolean[] freePrinters(int printers) {
        return IntStream.range(0, printers)
                .mapToObj(i -> true)
                .toArray(Boolean[]::new);
    }

    public void printJob(Object job) {
        try {
            semaphore.acquire();
            int printer = getPrinter();
            long duration = 1 + (long) (Math.random() * 5);
//            long duration = 3;
            System.out.printf("%s - %s: printer %s duration %s sec\n",
                    new Date(), Thread.currentThread().getName(), printer, duration);
            TimeUnit.SECONDS.sleep(duration);
            freePrinters[printer] = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private int getPrinter() {
        try {
            lock.lock();
            for (int i = 0; i < freePrinters.length; ++i) {
                if (freePrinters[i]) {
                    freePrinters[i] = false;
                    return i;
                }
            }
        } finally {
            lock.unlock();
        }
        return -1;
    }

}
