package s03.thread_synchronization_utilities.s01;

import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        int processors = 8;
        var printQueue = new PrintQueue(2);
        IntStream.range(0, processors)
                .mapToObj(i -> new Thread(new Job(printQueue), "Thread " + i))
                .forEach(Thread::start);
    }

}
