package s03.thread_synchronization_utilities.s04.server;

import util.ThreadUtil;

import java.util.List;
import java.util.concurrent.Phaser;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Server implements Runnable {

    private final Phaser phaser = new Phaser(1) {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            System.out.printf("Phase: %d. Parties: %d\n", phase, registeredParties);
            return registeredParties == 0;
        }
    };

    @Override
    public void run() {
        startFirst3Services();
        startNext2Services();
        finalCheck();
    }

    private void startFirst3Services() {
        List<Thread> threads = Stream.of(new ServerService("HTTP Listener", phaser), new ServerService("JMX", phaser), new ServerService("Connectors", phaser))
                .map(Thread::new)
                .collect(toList());
        threads.forEach(Thread::start);

        phaser.arriveAndAwaitAdvance();
        System.out.println();
    }

    private void startNext2Services() {
        List<Thread> threads = Stream.of(new ServerService("Virtual Hosts", phaser), new ServerService("Ports", phaser))
                .map(Thread::new)
                .collect(toList());
        threads.forEach(Thread::start);

        phaser.arriveAndAwaitAdvance();
        System.out.println();
    }

    private void finalCheck() {
        try {
            System.out.println("FINALIZATION (2 s)...");
            ThreadUtil.sleepSeconds(2);
        } finally {
            phaser.arriveAndDeregister();
        }
    }

}
