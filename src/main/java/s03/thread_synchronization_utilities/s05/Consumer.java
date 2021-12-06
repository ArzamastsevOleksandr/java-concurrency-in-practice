package s03.thread_synchronization_utilities.s05;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

@RequiredArgsConstructor
class Consumer implements Runnable {

    private List<String> buffer = new ArrayList<>();
    private final Exchanger<List<String>> exchanger;

    @Override
    public void run() {
        for (int cycle = 1; cycle <= 5; ++cycle) {
            System.out.println();
            System.out.printf("Consumer before (%s): %s\n", cycle, buffer);
            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("Consumer after (%s): %s\n", cycle, buffer);
            buffer.clear();
        }
    }

}
