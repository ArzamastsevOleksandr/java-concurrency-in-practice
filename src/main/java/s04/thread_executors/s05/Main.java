package s04.thread_executors.s05;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class Main {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        IntStream.rangeClosed(1, 4)
                .mapToObj(i -> new Task("Task " + i, i))
                .forEach(task -> scheduledExecutorService.schedule(task, task.delay, TimeUnit.SECONDS));

        scheduledExecutorService.shutdown();
        try {
            scheduledExecutorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main END");
    }

    @RequiredArgsConstructor
    private static class Task implements Callable<Integer> {

        final String name;
        final int delay;

        @Override
        public Integer call() {
            System.out.printf("%s started\n", name);
            return 0;
        }

    }

}
