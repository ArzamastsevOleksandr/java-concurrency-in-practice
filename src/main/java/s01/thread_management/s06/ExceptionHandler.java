package s01.thread_management.s06;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("An exception has been caught");
        System.out.printf("Thread: %s. State: %s\n", t.getId(), t.getState());
        System.out.printf("%s, message: %s\n", e.getClass().getName(), e.getMessage());
        System.out.println("Stack trace:");
        e.printStackTrace();
    }

}
