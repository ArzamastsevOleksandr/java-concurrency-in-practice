package s01.thread_management.s08;

public class CustomThreadGroup extends ThreadGroup {

    public CustomThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("Thread Group '%s' caught an exception %s with message %s\n", getName(), e.getClass().getName(), e.getMessage());
        System.out.println("Terminating the rest of the threads");
        interrupt();
    }

}
