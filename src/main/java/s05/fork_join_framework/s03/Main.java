package s05.fork_join_framework.s03;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;

class Main {

    public static void main(String[] args) {
        List<Long> list = LongStream.range(1, 100).boxed().collect(toList());

        var sumCountedCompleter = new SumCountedCompleter(null, list);
        var pool = ForkJoinPool.commonPool();
        pool.invoke(sumCountedCompleter);

        System.out.printf("Done. Result: %d\n", sumCountedCompleter.getRawResult());
    }

}
