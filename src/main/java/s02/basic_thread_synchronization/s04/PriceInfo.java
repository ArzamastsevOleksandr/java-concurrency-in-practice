package s02.basic_thread_synchronization.s04;

import util.ThreadUtil;

import java.util.Date;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PriceInfo {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private double price1 = 1;
    private double price2 = 2;

    public double getPrice1() {
        lock.readLock().lock();
        double value = price1;
        lock.readLock().unlock();
        return value;
    }

    public double getPrice2() {
        lock.readLock().lock();
        double value = price2;
        lock.readLock().unlock();
        return value;
    }

    public void setPrices(double price1, double price2) {
        lock.writeLock().lock();
        System.out.printf("Write lock acquired at %s\n", new Date());
        ThreadUtil.sleepSeconds(5);
        this.price1 = price1;
        this.price2 = price2;
        System.out.printf("Write lock released at %s\n", new Date());
        lock.writeLock().unlock();
    }

}
