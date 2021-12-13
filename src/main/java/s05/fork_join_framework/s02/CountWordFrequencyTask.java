package s05.fork_join_framework.s02;

import lombok.RequiredArgsConstructor;
import util.ThreadUtil;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

@RequiredArgsConstructor
class CountWordFrequencyTask extends RecursiveTask<Long> {

    final String[][] document;
    final String target;
    final int start, end;

    @Override
    protected Long compute() {
        if (end - start <= 5) {
            System.out.printf("%s: process [%d-%d]\n", Thread.currentThread().getName(), start, end);
            ThreadUtil.sleepSeconds(1);
            return IntStream.range(start, end)
                    .mapToLong(i -> Arrays.stream(document[i])
                            .peek(s -> ThreadUtil.sleepMillis(20))
                            .filter(s -> s.equals(target))
                            .count())
                    .reduce(Long::sum).orElse(-1L);
        } else {
            int mid = (end + start) / 2;
            var task1 = new CountWordFrequencyTask(document, target, start, mid);
            var task2 = new CountWordFrequencyTask(document, target, mid, end);
            invokeAll(task1, task2);
            try {
                return task1.get() + task2.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return Long.MIN_VALUE;
    }

}
