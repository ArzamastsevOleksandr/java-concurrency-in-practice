package s03.thread_synchronization_utilities.s03;

import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

class Main {

    static final int SIZE = 3;
    static final int TARGET = 3;

    public static void main(String[] args) {
        var matrixMock = new MatrixMock(SIZE);
        System.out.println(matrixMock);
        System.out.printf("Count of %d in the matrix is %d\n\n", TARGET, matrixMock.countOf(TARGET));

        var searchResults = new SearchResults(SIZE);
        var grouper = new Grouper(searchResults);

        var cyclicBarrier = new CyclicBarrier(SIZE, grouper);
        IntStream.range(0, SIZE)
                .mapToObj(rowNumber -> new SearchTask(matrixMock, cyclicBarrier, TARGET, rowNumber, searchResults))
                .map(Thread::new)
                .forEach(Thread::start);
        System.out.println("Main thread finished");
    }

}
