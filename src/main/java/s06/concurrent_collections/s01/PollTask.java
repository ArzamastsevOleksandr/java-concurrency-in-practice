package s06.concurrent_collections.s01;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.IntStream;

@RequiredArgsConstructor
class PollTask implements Runnable {

    final ConcurrentLinkedDeque<String> deque;

    @Override
    public void run() {
        IntStream.range(0, Constants.ADD_COUNT / 2).forEach(i -> {
            deque.pollFirst();
            deque.pollLast();
        });
    }

}
