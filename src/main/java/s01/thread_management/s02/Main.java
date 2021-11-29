package s01.thread_management.s02;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        var thread = new PrimeGeneratorThread();
        thread.start();

        Thread.sleep(10L);
        thread.interrupt();

        printThreadInfo(thread);
        Thread.sleep(10L);
        printThreadInfo(thread);
    }

    private static void printThreadInfo(PrimeGeneratorThread thread) {
        System.out.printf("State: %s\n", thread.getState());
        System.out.printf("Is interrupted: %s\n", thread.isInterrupted());
        System.out.printf("Is alive: %s\n", thread.isAlive());
        System.out.println("*****************************************");
    }

}
