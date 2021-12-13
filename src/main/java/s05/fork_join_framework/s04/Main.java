package s05.fork_join_framework.s04;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

class Main {

    public static void main(String[] args) {
        int size = 100;
        int[] ints = ArrayGenerator.generate(size);

        var taskManager = new TaskManager();
        var forkJoinPool = new ForkJoinPool();

        var searchNumberTask = new SearchNumberTask(ints, 0, size, 5, taskManager);

        forkJoinPool.execute(searchNumberTask);
        forkJoinPool.shutdown();
        try {
            forkJoinPool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main END");
    }

}
