package s003.kn_nearest_neighbour.parallel_group;

import lombok.RequiredArgsConstructor;
import s003.kn_nearest_neighbour.calculator.EuclideanDistanceCalculator;
import s003.kn_nearest_neighbour.data.Distance;
import s003.kn_nearest_neighbour.data.Sample;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@RequiredArgsConstructor
public class GroupDistanceTask implements Runnable {

    private final Distance[] distances;
    private final int start;
    private final int end;
    private final List<? extends Sample> dataSet;
    private final Sample example;
    private final CountDownLatch endController;

    @Override
    public void run() {
        for (int i = start; i < end; ++i) {
            distances[i] = new Distance();
            distances[i].setIndex(i);
            distances[i].setDistance(EuclideanDistanceCalculator.calculate(dataSet.get(i), example));
        }
        endController.countDown();
    }

}
