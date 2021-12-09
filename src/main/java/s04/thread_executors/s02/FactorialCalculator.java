package s04.thread_executors.s02;

import lombok.RequiredArgsConstructor;
import util.ThreadUtil;

import java.util.concurrent.Callable;
import java.util.stream.IntStream;

@RequiredArgsConstructor
class FactorialCalculator implements Callable<Integer> {

    private final int number;

    @Override
    public Integer call() {
        if (number == 0 || number == 1) {
            return 1;
        }
        return IntStream.rangeClosed(2, number)
                .peek(i -> ThreadUtil.sleepSeconds(1))
                .reduce(1, (a, b) -> a * b);
    }

}
