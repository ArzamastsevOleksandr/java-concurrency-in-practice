package s05.fork_join_framework.s03;

import java.util.List;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.LongAdder;

class SumCountedCompleter extends CountedCompleter<Long> {

    static final int THRESHOLD = 10;
    static final LongAdder sumAll = new LongAdder();

    final List<Long> workList;

    SumCountedCompleter(CountedCompleter<?> completer, List<Long> workList) {
        super(completer);
        this.workList = workList;
    }

    @Override
    public void compute() {
        int size = workList.size();
        if (size <= THRESHOLD) {
            Long sum = workList.stream().reduce(Long::sum).orElse(0L);
            sumAll.add(sum);
            System.out.printf("%s partial sum: %d\n", Thread.currentThread().getName(), sum);
        } else {
            int mid = (size + 1) / 2;
            List<Long> left = workList.subList(0, mid);
            List<Long> right = workList.subList(mid, size);

            addToPendingCount(2);

            var completer1 = new SumCountedCompleter(this, left);
            var completer2 = new SumCountedCompleter(this, right);

            completer1.fork();
            completer2.fork();
        }
        tryComplete();
    }

    @Override
    public void onCompletion(CountedCompleter<?> caller) {
        System.out.printf("%s DONE\n", Thread.currentThread().getName());
    }

    @Override
    public Long getRawResult() {
        return sumAll.sum();
    }

}
