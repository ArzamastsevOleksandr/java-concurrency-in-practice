package s01.thread_management.s01;

import static util.NumberUtil.isPrime;

public class PrimeCalculatorRunnable implements Runnable {

    @Override
    public void run() {
        long current = 1L;
        long end = 20_000L;
        long primeNumbersCount = 0;

        System.out.printf("Thread '%s': start\n", Thread.currentThread().getName());
        while (current <= end) {
            if (isPrime(current)) {
                ++primeNumbersCount;
            }
            ++current;
        }
        System.out.printf("Thread '%s': end. Prime numbers found: %d\n", Thread.currentThread().getName(), primeNumbersCount);
    }

}
