package s03.thread_synchronization_utilities.s03;

import lombok.RequiredArgsConstructor;
import util.ThreadUtil;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@RequiredArgsConstructor
class SearchTask implements Runnable {

    private final MatrixMock matrixMock;
    private final CyclicBarrier cyclicBarrier;
    private final int target;
    private final int rowNumber;
    private final SearchResults searchResults;

    @Override
    public void run() {
        System.out.printf("%s: process row %d\n", Thread.currentThread().getName(), rowNumber);
        long count = Arrays.stream(matrixMock.getRow(rowNumber))
                .peek(i -> ThreadUtil.sleepMillis((long) (Math.random() * 2000)))
                .filter(i -> i == target)
                .count();
        searchResults.set(rowNumber, (int) count);
        System.out.printf("%s array processed\n", Thread.currentThread().getName());
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.printf("%s DONE\n", Thread.currentThread().getName());
    }

}
