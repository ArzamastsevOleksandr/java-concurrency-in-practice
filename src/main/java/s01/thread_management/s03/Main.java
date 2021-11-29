package s01.thread_management.s03;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        var thread = new Thread(new ConsoleClockRunnable());
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        thread.interrupt();
    }

}
