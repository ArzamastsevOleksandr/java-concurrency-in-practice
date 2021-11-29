package s01.thread_management.s09;

public class Main {

    public static void main(String[] args) {
        var threadFactory = new CustomThreadFactory("Custom thread factory");
        var task = new Task();
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); ++i) {
            threadFactory.newThread(task).start();
        }
        System.out.println("Factory stats:");
        System.out.println(threadFactory.getStats());
    }

}
