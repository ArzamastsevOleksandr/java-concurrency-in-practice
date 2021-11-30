package util;

import java.util.concurrent.TimeUnit;

public class SleepUtil {

    public static void sleepMillis(long ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
