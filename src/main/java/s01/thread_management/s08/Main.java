package s01.thread_management.s08;

public class Main {

    public static void main(String[] args) {
        var task = new Task();
        var threadGroup = new CustomThreadGroup("Custom thread group");

        for (int i = 0; i < Runtime.getRuntime().availableProcessors() * 2; ++i) {
            new Thread(threadGroup, task).start();
        }

        System.out.printf("Num of threads: %d\n", threadGroup.activeCount());
        System.out.println("Thread group info:");
        threadGroup.list();
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (int i = 0; i < threadGroup.activeCount(); ++i) {
            System.out.printf("Thread %d, state: %s\n", threads[i].getId(), threads[i].getState());
        }
    }

}
