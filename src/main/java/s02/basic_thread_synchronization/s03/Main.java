package s02.basic_thread_synchronization.s03;

import util.ThreadUtil;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {
        printQueue(true);
        printQueue(false);
    }

    private static void printQueue(boolean isFair) {
        System.out.printf("Test locks with fair mode: %s\n", isFair);
        var printQueue = new PrintQueue(isFair);
        List<Thread> threads = IntStream.rangeClosed(0, 9)
                .mapToObj(i -> new Thread(new Job(printQueue), "T " + i))
                .collect(toList());
        threads.forEach(Thread::start);
        threads.forEach(ThreadUtil::join);
        System.out.printf("Test locks with fair mode: %s finished\n", isFair);
        System.out.println("*********************************************");
    }


}
