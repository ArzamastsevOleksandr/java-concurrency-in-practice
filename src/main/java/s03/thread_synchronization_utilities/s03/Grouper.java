package s03.thread_synchronization_utilities.s03;

import lombok.RequiredArgsConstructor;
import util.ThreadUtil;

import java.util.Arrays;

@RequiredArgsConstructor
class Grouper implements Runnable {

    private final SearchResults searchResults;

    @Override
    public void run() {
        ThreadUtil.sleepSeconds(2);
        System.out.println("Grouper: processing results...");
        int count = Arrays.stream(searchResults.getData()).reduce(0, Integer::sum);
        System.out.printf("Grouper: total targets found: %d\n", count);
    }

}
