package s01.thread_management.s06;

public class ThrowingTask implements Runnable {

    @Override
    public void run() {
        throw new RuntimeException("You won't catch me");
    }

}
