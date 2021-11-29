package s01.thread_management.s01;

public class Main {

    public static final int THREAD_COUNT = 20;

    public static void main(String[] args) {
        printDefaultThreadPriorities();
        Thread[] threads = new Thread[THREAD_COUNT];
        Thread.State[] threadStates = new Thread.State[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; ++i) {
            threads[i] = new Thread(new PrimeCalculatorRunnable());
            threads[i].setName("Thread " + i);
            if (i % 2 == 0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
        }

        for (int i = 0; i < THREAD_COUNT; ++i) {
            System.out.printf("Thread %s state: %s\n", i, threads[i].getState());
            threadStates[i] = threads[i].getState();
        }
        for (int i = 0; i < THREAD_COUNT; ++i) {
            threads[i].start();
        }

        boolean finished = false;
        while (!finished) {
            for (int i = 0; i < THREAD_COUNT; ++i) {
                if (threads[i].getState() != threadStates[i]) {
                    writeThreadInfo(threads[i], threadStates[i]);
                    threadStates[i] = threads[i].getState();
                }
            }

            finished = true;
            for (int i = 0; i < THREAD_COUNT; ++i) {
                finished = finished && (threads[i].getState() == Thread.State.TERMINATED);
            }
        }
    }

    private static void printDefaultThreadPriorities() {
        System.out.printf("Min priority: %s\n", Thread.MIN_PRIORITY);
        System.out.printf("Norm priority: %s\n", Thread.NORM_PRIORITY);
        System.out.printf("Max priority: %s\n", Thread.MAX_PRIORITY);
    }

    private static void writeThreadInfo(Thread thread, Thread.State state) {
        System.out.printf("Main: id %s - %s\n", thread.getId(), thread.getName());
        System.out.printf("Priority: %s\n", thread.getPriority());
        System.out.printf("Old state: %s, new state: %s\n", state, thread.getState());
        System.out.println("***************************************************");
    }

}
