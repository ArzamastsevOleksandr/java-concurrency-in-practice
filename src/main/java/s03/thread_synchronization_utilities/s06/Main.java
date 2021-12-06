package s03.thread_synchronization_utilities.s06;

import util.ThreadUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class Main {

    public static void main(String[] args) {
        var step1 = new CompletableFuture<Integer>();

        System.out.println("Main: wait for step1...");
        new Thread(new Step1(step1)).start();

        int step1Result = 0;
        try {
            step1Result = step1.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.printf("Main: step1Result=%d\n", step1Result);

        CompletableFuture<Integer> step2 = CompletableFuture.supplyAsync(new Step2(step1Result));

        CompletableFuture<Integer> step31 = step2.thenApplyAsync(number -> {
            System.out.println("Step31 START");
            ThreadUtil.sleepSeconds(number);
            System.out.println("Step31 END");
            return number;
        });
        CompletableFuture<Integer> step32 = step2.thenApplyAsync(number -> {
            System.out.println("Step32 START");
            ThreadUtil.sleepSeconds(number * 2);
            System.out.println("Step32 END");
            return number;
        });

        CompletableFuture.allOf(step31, step32).thenAcceptAsync(v -> {
            System.out.println("Main: END");
        }).join();
    }

}
