package s01.thread_management.s04;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main started");
        Thread wait = new Thread(new WaitForMeRunnable(), "wait");
        Thread please = new Thread(new WaitForMeRunnable(), "please");

        wait.start();
        please.start();

        wait.join();
        please.join(TimeUnit.SECONDS.toMillis(2));

        System.out.println("Main finished");
    }

}
