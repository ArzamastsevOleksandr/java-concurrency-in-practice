package s04.thread_executors.s01;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class Server {

    private final ThreadPoolExecutor executor;

    Server() {
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        executor.setRejectedExecutionHandler(new RejectedTaskController());
    }

    void executeTask(Task task) {
        System.out.println("New task arrived");
        executor.execute(task);
        System.out.printf("Pool size: %d, active count: %d, task count: %d, completed count: %s\n",
                executor.getPoolSize(), executor.getActiveCount(), executor.getTaskCount(), executor.getCompletedTaskCount());
    }

    void stop() {
        executor.shutdown();
    }

}
