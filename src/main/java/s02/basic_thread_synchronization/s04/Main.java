package s02.basic_thread_synchronization.s04;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {
        var priceInfo = new PriceInfo();

        List<Thread> readers = IntStream.range(0, 2)
                .mapToObj(i -> new Thread(new PriceReaderTask(priceInfo)))
                .collect(toList());
        var writer = new Thread(new PriceWriterTask(priceInfo));

        readers.forEach(Thread::start);
        writer.start();
    }

}
