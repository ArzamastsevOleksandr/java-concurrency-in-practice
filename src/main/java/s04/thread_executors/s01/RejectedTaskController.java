package s04.thread_executors.s01;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

class RejectedTaskController implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.printf("Task %s rejected. Terminating: %s. Terminated: %s\n",
                r, executor.isTerminating(), executor.isTerminated());
    }

}
