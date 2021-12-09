package s04.thread_executors.s06;

import util.ThreadUtil;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class Main {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.printf("Time: %s\n", new Date());
        }, 0, 1, TimeUnit.SECONDS);
        System.out.printf("Is done: %s\n", scheduledFuture.isDone());
        ThreadUtil.sleepSeconds(5);
        scheduledExecutorService.shutdown();
        System.out.printf("Is done: %s\n", scheduledFuture.isDone());
    }

}
