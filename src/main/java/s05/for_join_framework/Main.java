package s05.for_join_framework;

import util.ThreadUtil;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

class Main {

    public static void main(String[] args) {
        List<Product> products = Product.generate(50);
        System.out.printf("Products START: %s\n", products);

        var updatePricesTask = new UpdatePricesTask(products, 0, products.size(), 2);
        var forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(updatePricesTask);

        while (!updatePricesTask.isDone()) {
            System.out.printf("Main: Thread count: %d\n", forkJoinPool.getActiveThreadCount());
            System.out.printf("Main: Thread steal count: %d\n", forkJoinPool.getStealCount());
            System.out.printf("Main: Parallelism: %d\n", forkJoinPool.getParallelism());
            ThreadUtil.sleepSeconds(1);
        }
        forkJoinPool.shutdown();
        System.out.printf("Products END: %s\n", products);

        System.out.printf("Task completed normally: %s\n", updatePricesTask.isCompletedNormally());
    }

}
