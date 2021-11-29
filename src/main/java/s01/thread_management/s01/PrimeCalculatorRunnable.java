package s01.thread_management.s01;

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

    private boolean isPrime(long current) {
        if (current <= 2) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(current); ++i) {
            if (current % i == 0) {
                return false;
            }
        }
        return true;
    }

}
