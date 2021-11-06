package s003.kn_nearest_neighbour.parallel_individual;

import lombok.RequiredArgsConstructor;
import s003.kn_nearest_neighbour.calculator.EuclideanDistanceCalculator;
import s003.kn_nearest_neighbour.data.Distance;
import s003.kn_nearest_neighbour.data.Sample;

import java.util.concurrent.CountDownLatch;

@RequiredArgsConstructor
public class IndividualDistanceTask implements Runnable {

    private final Distance[] distances;
    private final int index;
    private final Sample localExample;
    private final Sample example;
    private final CountDownLatch endController;

    @Override
    public void run() {
        distances[index] = new Distance();
        distances[index].setIndex(index);
        distances[index].setDistance(EuclideanDistanceCalculator.calculate(localExample, example));
        endController.countDown();
    }

}
