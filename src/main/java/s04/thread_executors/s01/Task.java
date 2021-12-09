package s04.thread_executors.s01;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import util.ThreadUtil;

@ToString
@RequiredArgsConstructor
class Task implements Runnable {

    private final String name;

    @Override
    public void run() {
        System.out.printf("%s START\n", name);
        ThreadUtil.sleepSeconds((int) (Math.random() * 10) + 1);
        System.out.printf("%s END\n", name);
    }

}
