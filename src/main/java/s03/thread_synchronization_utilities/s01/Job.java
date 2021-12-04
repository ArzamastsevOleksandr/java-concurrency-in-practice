package s03.thread_synchronization_utilities.s01;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Job implements Runnable {

    private final PrintQueue printQueue;

    @Override
    public void run() {
        System.out.printf("%s: START\n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: DONE\n", Thread.currentThread().getName());
    }

}
