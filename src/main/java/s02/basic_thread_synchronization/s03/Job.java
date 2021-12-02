package s02.basic_thread_synchronization.s03;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Job implements Runnable {

    private final PrintQueue printQueue;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": start");
        printQueue.printJob(new Object());
        System.out.println(Thread.currentThread().getName() + ": end");
    }

}
