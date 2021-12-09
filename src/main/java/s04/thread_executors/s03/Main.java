package s04.thread_executors.s03;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class Main {

    static final int DELAY1 = 2;
    static final int DELAY2 = 4;

    public static void main(String[] args) {
        var executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        List<Callable<Integer>> callables = List.of(() -> {
            boxedOperations(DELAY1);
            return DELAY1;
        }, () -> {
            boxedOperations(DELAY2);
            return DELAY2;
        });

        try {
            Integer result = executor.invokeAny(callables);
            System.out.printf("Expected: %d, actual: %d\n", DELAY1, result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdownNow();
        }
    }

    private static void boxedOperations(int times) {
        Integer result = 1;
        for (int j = 0; j < times; ++j) {
            for (int i = 0; i < Integer.MAX_VALUE; ++i) {
                result *= i;
            }
        }
    }

}
