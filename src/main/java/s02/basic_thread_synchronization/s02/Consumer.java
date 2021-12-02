package s02.basic_thread_synchronization.s02;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Consumer implements Runnable {

    private final EventStorage eventStorage;

    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            eventStorage.get();
        }
    }

}
