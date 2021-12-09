package s04.thread_executors.s02;

import util.ThreadUtil;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

class Main {

    static final int COUNT = 10;

    public static void main(String[] args) {
        var executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> futures = IntStream.rangeClosed(0, COUNT)
                .mapToObj(FactorialCalculator::new)
                .map(executor::submit)
                .collect(toList());

        while (executor.getCompletedTaskCount() < futures.size()) {
            System.out.printf("Completed count: %d\n", executor.getCompletedTaskCount());
            ThreadUtil.sleepSeconds(1);
        }

        IntStream.rangeClosed(0, COUNT)
                .forEach(i -> {
                    Future<Integer> future = futures.get(i);
                    try {
                        Integer result = future.get();
                        System.out.printf("Task %d result: %d\n", i, result);
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                });

        executor.shutdown();
    }

}
