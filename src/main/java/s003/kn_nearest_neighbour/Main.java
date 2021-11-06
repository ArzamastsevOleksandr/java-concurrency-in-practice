package s003.kn_nearest_neighbour;

import s003.kn_nearest_neighbour.data.BankMarketingSample;
import s003.kn_nearest_neighbour.loader.BankMarketingLoader;
import s003.kn_nearest_neighbour.parallel_group.KnnClassifierParallelGroup;
import s003.kn_nearest_neighbour.parallel_individual.KnnClassifierParallelIndividual;
import s003.kn_nearest_neighbour.serial.KnnClassifier;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        var loader = new BankMarketingLoader();

        List<BankMarketingSample> trainDataSet = loader.load("/home/alex/git/learn-concurrency-in-java/src/main/resources/bank/bank.data.txt");
        System.out.println("TrainDataSet size: " + trainDataSet.size());
        List<BankMarketingSample> testDataSet = loader.load("/home/alex/git/learn-concurrency-in-java/src/main/resources/bank/bank.test.txt");
        System.out.println("TestDataSet size: " + testDataSet.size());

        int success = 0, mistakes = 0;
        int k = 50;
        System.out.println("k: " + k);

        // serial
        var classifier = new KnnClassifier(trainDataSet, k);
        try {
            long start = System.currentTimeMillis();
            System.out.println("Serial");
            for (BankMarketingSample example : testDataSet) {
                String tag = classifier.classify(example);
                if (tag.equals(example.getTag())) {
                    success++;
                } else {
                    mistakes++;
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("Time taken: " + (end - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Success: " + success);
        System.out.println("Mistakes: " + mistakes);

        // parallel individual
        // parallelSort=false: 51446 ms parallelSort=true: 39548 ms
        var knnClassifierParallelIndividual = new KnnClassifierParallelIndividual(trainDataSet, k, true);
        try {
            System.out.println("Parallel individual");
            long start = System.currentTimeMillis();
            for (BankMarketingSample example : testDataSet) {
                String tag = knnClassifierParallelIndividual.classify(example);
                if (tag.equals(example.getTag())) {
                    success++;
                } else {
                    mistakes++;
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("Time taken: " + (end - start) + " ms");
            knnClassifierParallelIndividual.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Success: " + success);
        System.out.println("Mistakes: " + mistakes);

        // parallel group
        // parallelSort=false: 24592 ms parallelSort=true: 12257 ms
        var knnClassifierParallelGroup = new KnnClassifierParallelGroup(trainDataSet, k, false);
        try {
            System.out.println("Parallel group");
            long start = System.currentTimeMillis();
            for (BankMarketingSample example : testDataSet) {
                String tag = knnClassifierParallelGroup.classify(example);
                if (tag.equals(example.getTag())) {
                    success++;
                } else {
                    mistakes++;
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("Time taken: " + (end - start) + " ms");
            knnClassifierParallelGroup.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Success: " + success);
        System.out.println("Mistakes: " + mistakes);
    }

}
