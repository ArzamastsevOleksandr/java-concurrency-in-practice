package s02.basic_thread_synchronization.s03;

import lombok.RequiredArgsConstructor;
import util.ThreadUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {

    public static void main(String[] args) {
        var lock1 = new ReentrantLock();
        var lock2 = new ReentrantLock();

        var thread1 = new Thread(new DeadlockTask1(lock1, lock2));
        var thread2 = new Thread(new DeadlockTask1(lock2, lock1));

        thread1.start();
        thread2.start();
    }

    @RequiredArgsConstructor
    private static class DeadlockTask1 implements Runnable {

        private final Lock lock1;
        private final Lock lock2;

        private boolean done = false;

        @Override
        public void run() {
            if (lock1.tryLock()) {
                try {
                    ThreadUtil.sleepSeconds(2);
                    while (!done) {
                        if (lock2.tryLock()) {
                            try {
                                done = true;
                                System.out.printf("%s: must not happen when modeling deadlock\n", Thread.currentThread().getName());
                            } finally {
                                lock2.unlock();
                            }
                        } else {
                            System.out.printf("%s failed to acquire lock2\n", Thread.currentThread().getName());
                            ThreadUtil.sleepSeconds(1);
                        }
                    }
                } finally {
                    lock1.unlock();
                }
            } else {
                System.out.printf("%s: must not happen when modeling deadlock", Thread.currentThread().getName());
            }
        }

    }

}
