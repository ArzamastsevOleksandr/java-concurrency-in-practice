package s03.thread_synchronization_utilities.s05;

import lombok.RequiredArgsConstructor;
import util.ThreadUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
class Producer implements Runnable {

    private List<String> buffer = new ArrayList<>();
    private final Exchanger<List<String>> exchanger;

    @Override
    public void run() {
        for (int cycle = 1; cycle <= 5; ++cycle) {
            final int cycleCopy = cycle;
            List<String> data = IntStream.range(0, 5)
                    .peek(i -> ThreadUtil.sleepMillis(500))
                    .mapToObj(i -> "D " + cycleCopy + i)
                    .collect(toList());

            buffer.addAll(data);
            try {
                System.out.printf("Producer before (%s): %s\n", cycle, buffer);
                buffer = exchanger.exchange(buffer);
                System.out.printf("Producer after (%s): %s\n", cycle, buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
