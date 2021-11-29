package s01.thread_management.s06;

public class Main {

    public static void main(String[] args) {
        var thread = new Thread(new ThrowingTask());
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }

}
