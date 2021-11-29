package s01.thread_management.s07;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

class Main {

    static final int NUM_OF_THREADS = 3;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("***********UNSAFE**************");
        var unsafeTask = new UnsafeTask();
        List<Thread> unsafeThreads = IntStream.range(0, NUM_OF_THREADS)
                .mapToObj(i -> new Thread(unsafeTask))
                .peek(t -> {
                    t.start();
                    sleepSeconds();
                }).collect(toList());
        for (Thread unsafeThread : unsafeThreads) {
            unsafeThread.join();
        }
        System.out.println("***********SAFE**************");
        var safeTask = new SafeTask();
        for (int i = 0; i < NUM_OF_THREADS; ++i) {
            new Thread(safeTask).start();
            sleepSeconds();
        }
    }

    private static void sleepSeconds() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
