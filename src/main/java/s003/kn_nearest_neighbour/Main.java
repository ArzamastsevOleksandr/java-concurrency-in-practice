package s003.kn_nearest_neighbour;

import s003.kn_nearest_neighbour.data.BankMarketingSample;
import s003.kn_nearest_neighbour.loader.BankMarketingLoader;
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
        int k = 10;
        System.out.println("k: " + k);
        var classifier = new KnnClassifier(trainDataSet, k);
        try {
            for (BankMarketingSample example : testDataSet) {
                String tag = classifier.classify(example);
                if (tag.equals(example.getTag())) {
                    success++;
                } else {
                    mistakes++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Success: " + success);
        System.out.println("Mistakes: " + mistakes);
    }

}
