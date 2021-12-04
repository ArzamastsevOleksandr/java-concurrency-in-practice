package s03.thread_synchronization_utilities.s02;

import java.util.concurrent.CountDownLatch;

public class VideoConference implements Runnable {

    private final CountDownLatch controller;

    public VideoConference(int participants) {
        controller = new CountDownLatch(participants);
    }

    public void arrive(String name) {
        System.out.printf("%s arrived\n", name);
        controller.countDown();
        System.out.printf("Wait for %d participants\n", controller.getCount());
    }

    @Override
    public void run() {
        System.out.printf("VideoConference init: wait for %d participants\n", controller.getCount());
        try {
            controller.await();
            System.out.println("VideoConference START");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
