package s003.kn_nearest_neighbour.serial;

import lombok.RequiredArgsConstructor;
import s003.kn_nearest_neighbour.calculator.EuclideanDistanceCalculator;
import s003.kn_nearest_neighbour.data.Distance;
import s003.kn_nearest_neighbour.data.Sample;

import java.util.*;

@RequiredArgsConstructor
public class KnnClassifier {

    private final List<? extends Sample> dataSet;
    private final int k;

    public String classify(Sample example) {
        Distance[] distances = new Distance[dataSet.size()];
        int index = 0;

        for (Sample local : dataSet) {
            distances[index] = new Distance();
            distances[index].setIndex(index);
            distances[index].setDistance(EuclideanDistanceCalculator.calculate(local, example));
            ++index;
        }

        Arrays.sort(distances);

        Map<String, Integer> results = new HashMap<>();
        for (int i = 0; i < k; ++i) {
            Sample localExample = dataSet.get(distances[i].getIndex());
            String tag = localExample.getTag();
            results.merge(tag, 1, Integer::sum);
        }
        return Collections.max(results.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

}
