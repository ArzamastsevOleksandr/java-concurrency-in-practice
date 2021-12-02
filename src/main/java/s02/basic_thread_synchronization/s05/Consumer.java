package s02.basic_thread_synchronization.s05;

import lombok.RequiredArgsConstructor;
import util.ThreadUtil;

@RequiredArgsConstructor
public class Consumer implements Runnable {

    private final Buffer buffer;

    @Override
    public void run() {
        while (buffer.hasPendingLines()) {
            buffer.get();
            ThreadUtil.sleepMillis(100);
        }
    }

}
