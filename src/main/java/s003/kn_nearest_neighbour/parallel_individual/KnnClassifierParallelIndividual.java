package s003.kn_nearest_neighbour.parallel_individual;

import s003.kn_nearest_neighbour.data.Distance;
import s003.kn_nearest_neighbour.data.Sample;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class KnnClassifierParallelIndividual {

    private final List<? extends Sample> dataSet;
    private final int k;
    private final ThreadPoolExecutor executor;
    private final boolean parallelSort;

    public KnnClassifierParallelIndividual(List<? extends Sample> dataSet, int k, boolean parallelSort) {
        this.dataSet = dataSet;
        this.k = k;
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        this.parallelSort = parallelSort;
    }

    public String classify(Sample sample) {
        Distance[] distances = new Distance[dataSet.size()];
        var endController = new CountDownLatch(dataSet.size());

        int index = 0;
        for (Sample local: dataSet) {
            var task = new IndividualDistanceTask(distances, index, local, sample, endController);
            executor.execute(task);
            index++;
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
