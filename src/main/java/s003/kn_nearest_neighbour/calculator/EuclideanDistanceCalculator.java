package s003.kn_nearest_neighbour.calculator;

import s003.kn_nearest_neighbour.data.Sample;

public final class EuclideanDistanceCalculator {

    public static double calculate(Sample example1, Sample example2) {
        double ret = 0.0d;
        double[] data1 = example1.getExample();
        double[] data2 = example2.getExample();

        if (data1.length != data2.length) {
            throw new IllegalArgumentException("Vectors have different lengths");
        }

        for (int i = 0; i < data1.length; i++) {
            ret += Math.pow(data1[i] - data2[i], 2);
        }
        return Math.sqrt(ret);
    }

}
