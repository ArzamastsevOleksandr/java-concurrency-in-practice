package s01.thread_management.s02;

import static util.NumberUtil.isPrime;

public class PrimeGeneratorThread extends Thread {

    @Override
    public void run() {
        long current = 1L;
        while (true) {
            if (isPrime(current)) {
                System.out.printf("%s is prime\n", current);
            }
            if (isInterrupted()) {
                System.out.println("Thread has been interrupted");
                return;
            }
            ++current;
        }
    }

}
