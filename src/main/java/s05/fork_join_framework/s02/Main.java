package s05.fork_join_framework.s02;

import util.ThreadUtil;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

class Main {

    public static void main(String[] args) {
        String[][] document = DocumentCreator.create(100);

        Arrays.stream(document)
                .map(Arrays::toString)
                .forEach(System.out::println);
        System.out.println();

        String target = "is";
        var task = new CountWordFrequencyTask(document, target, 0, document.length);
        var pool = new ForkJoinPool();
        pool.execute(task);

        while (!task.isDone()) {
            System.out.printf("Main: Thread count: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Task count: %d\n", pool.getQueuedTaskCount());
            System.out.printf("Main: Steal count: %d\n", pool.getStealCount());
            ThreadUtil.sleepSeconds(1);
        }
        pool.shutdown();
        try {
            System.out.printf("Word [%s] count is: %d\n", target, task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
