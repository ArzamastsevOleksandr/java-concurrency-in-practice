package s02.basic_thread_synchronization.s05;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private final LinkedList<String> buffer = new LinkedList<>();

    private final Lock lock = new ReentrantLock(true);
    private final Condition lineCondition = lock.newCondition();
    private final Condition spaceCondition = lock.newCondition();

    private boolean pendingLines = true;

    private final int maxSize;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
    }

    public void insert(String line) {
        lock.lock();
        try {
            while (buffer.size() == maxSize) {
                System.out.printf("%s: AWAIT\n", Thread.currentThread().getName());
                spaceCondition.await();
            }
            buffer.addLast(line);
            System.out.printf("%s: add %s. Size: %d\n", Thread.currentThread().getName(), line, buffer.size());
            lineCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String get() {
        lock.lock();
        try {
            while (buffer.isEmpty() && hasPendingLines()) {
                System.out.printf("%s: AWAIT\n", Thread.currentThread().getName());
                lineCondition.await();
            }
            if (hasPendingLines()) {
                String poll = buffer.poll();
                System.out.printf("%s: read %s. Size: %s\n", Thread.currentThread().getName(), poll, buffer.size());
                spaceCondition.signalAll();
                return poll;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public synchronized boolean hasPendingLines() {
        return pendingLines || buffer.size() > 0;
    }

    public synchronized void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }

}
