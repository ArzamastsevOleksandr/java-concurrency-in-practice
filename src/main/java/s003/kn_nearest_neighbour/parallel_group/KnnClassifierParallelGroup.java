package s003.kn_nearest_neighbour.parallel_group;

import s003.kn_nearest_neighbour.data.Distance;
import s003.kn_nearest_neighbour.data.Sample;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class KnnClassifierParallelGroup {

    private final List<? extends Sample> dataSet;
    private final int k;
    private final ThreadPoolExecutor executor;
    private final boolean parallelSort;

    public KnnClassifierParallelGroup(List<? extends Sample> dataSet, int k, boolean parallelSort) {
        this.dataSet = dataSet;
        this.k = k;
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        this.parallelSort = parallelSort;
    }

    public String classify(Sample sample) {
        Distance[] distances = new Distance[dataSet.size()];
        var endController = new CountDownLatch(Runtime.getRuntime().availableProcessors());

        int length = dataSet.size() / Runtime.getRuntime().availableProcessors();
        int start = 0;
        int end = length;

        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); ++i) {
            var groupDistanceTask = new GroupDistanceTask(distances, start, end, dataSet, sample, endController);
            executor.execute(groupDistanceTask);

            start = end;
            if (i < Runtime.getRuntime().availableProcessors() - 2) {
                end += length;
            } else {
                end = dataSet.size();
            }
        }
        try {
            endController.await();
        } catch (InterruptedException e) {
            throw new IllegalStateException(e.getMessage());
        }
        if (parallelSort) {
            Arrays.parallelSort(distances);
        } else {
            Arrays.sort(distances);
        }
        Map<String, Integer> results = new HashMap<>();
        for (int i = 0; i < k; ++i) {
            Sample localExample = dataSet.get(distances[i].getIndex());
            String tag = localExample.getTag();
            results.merge(tag, 1, Integer::sum);
        }
        return Collections.max(results.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public void destroy() {
        executor.shutdown();
    }

}
