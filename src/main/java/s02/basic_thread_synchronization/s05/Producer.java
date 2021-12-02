package s02.basic_thread_synchronization.s05;

import lombok.RequiredArgsConstructor;
import util.ThreadUtil;

@RequiredArgsConstructor
public class Producer implements Runnable {

    private final Buffer buffer;
    private final int count;

    @Override
    public void run() {
        buffer.setPendingLines(true);
        for (int i = 0; i < count; ++i) {
            String content = "Content " + i;
            buffer.insert(content);
//            ThreadUtil.sleepSeconds(1);
        }
        buffer.setPendingLines(false);
    }

}
