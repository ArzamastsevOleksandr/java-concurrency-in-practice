package s003.kn_nearest_neighbour.loader;

import s003.kn_nearest_neighbour.data.BankMarketingSample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BankMarketingLoader {

    public List<BankMarketingSample> load(String dataPath) {
        Path file = Paths.get(dataPath);
        List<BankMarketingSample> dataSet = new ArrayList<>();
        try (var in = Files.newInputStream(file);
             var reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                BankMarketingSample dataObject = new BankMarketingSample();
                dataObject.setData(data);
                dataSet.add(dataObject);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
        return dataSet;
    }

}
