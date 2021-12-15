package s06.concurrent_collections.s01;

import util.ThreadUtil;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

class Main {

    public static void main(String[] args) {
        var deque = new ConcurrentLinkedDeque<String>();

        List<Thread> addTasks = IntStream.range(0, Constants.THREAD_COUNT)
                .mapToObj(i -> new Thread(new AddTask(deque)))
                .collect(toList());

        addTasks.forEach(Thread::start);
        addTasks.forEach(ThreadUtil::join);

        System.out.printf("ADD SIZE: Actual: %d. Expected: %d\n", deque.size(), Constants.THREAD_COUNT * Constants.ADD_COUNT);

        List<Thread> pollTasks = IntStream.range(0, 10)
                .mapToObj(i -> new Thread(new PollTask(deque)))
                .collect(toList());

        pollTasks.forEach(Thread::start);
        pollTasks.forEach(ThreadUtil::join);

        System.out.printf("POLL SIZE: %d\n", deque.size());
    }

}
