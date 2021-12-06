package s03.thread_synchronization_utilities.s06;

import lombok.RequiredArgsConstructor;
import util.ThreadUtil;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
class Step1 implements Runnable {

    private final CompletableFuture<Integer> future;

    @Override
    public void run() {
        System.out.println(this.getClass().getSimpleName() + " START");
        ThreadUtil.sleepSeconds(new Random().nextInt(5) + 1);
        future.complete(3);
        System.out.println(this.getClass().getSimpleName() + " END");
    }

}
