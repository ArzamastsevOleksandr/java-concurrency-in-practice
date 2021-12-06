package s03.thread_synchronization_utilities.s04.server;

import util.ThreadUtil;

import java.util.Random;
import java.util.concurrent.Phaser;

class ServerService implements Runnable {

    private final String name;
    private final Phaser phaser;
    private final Random random = new Random();

    ServerService(String name, Phaser phaser) {
        this.name = name;
        this.phaser = phaser;
        phaser.register();
    }

    @Override
    public void run() {
        try {
            System.out.printf("Starting service '%s'...\n", name);
            int startsIn = random.nextInt(10) + 1;
            ThreadUtil.sleepSeconds(startsIn);
            System.out.printf("Service '%s' started in %d s\n", name, startsIn);
        } finally {
            phaser.arriveAndDeregister();
        }
    }

}
