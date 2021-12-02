package s02.basic_thread_synchronization.s05;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {
        var buffer = new Buffer(2);

        var producerThread = new Thread(new Producer(buffer, 15), "Producer");

        List<Thread> consumerThreads = IntStream.rangeClosed(0, 5)
                .mapToObj(i -> new Thread(new Consumer(buffer), "Consumer " + (i + 1)))
                .collect(toList());

        producerThread.start();
        consumerThreads.forEach(Thread::start);
    }

}
