package s06.concurrent_collections.s01;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.IntStream;

@RequiredArgsConstructor
class AddTask implements Runnable {

    final ConcurrentLinkedDeque<String> deque;

    @Override
    public void run() {
        IntStream.range(0, Constants.ADD_COUNT)
                .mapToObj(i -> Thread.currentThread().getName() + i)
                .forEach(deque::add);
    }

}
