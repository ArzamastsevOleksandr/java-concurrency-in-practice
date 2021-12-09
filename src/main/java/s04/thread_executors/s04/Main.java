package s04.thread_executors.s04;

import util.ThreadUtil;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

class Main {

    public static void main(String[] args) {
        var executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        List<Callable<Integer>> callables = IntStream.rangeClosed(1, 5)
                .mapToObj(i -> (Callable<Integer>) () -> {
                    ThreadUtil.sleepSeconds(i);
                    return i;
                }).collect(toList());

        try {
            List<Future<Integer>> futures = executorService.invokeAll(callables);
            System.out.println("All callables completed");
            futures.forEach(future -> {
                try {
                    Integer result = future.get();
                    System.out.println("Result: " + result);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

}
