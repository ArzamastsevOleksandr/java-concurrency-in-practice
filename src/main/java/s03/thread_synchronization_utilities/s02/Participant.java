package s03.thread_synchronization_utilities.s02;

import lombok.RequiredArgsConstructor;
import util.ThreadUtil;

@RequiredArgsConstructor
public class Participant implements Runnable {

    private final VideoConference conference;
    private final String name;

    @Override
    public void run() {
        long delay = (long) (Math.random() * 10) + 1;
        ThreadUtil.sleepSeconds(delay);
        conference.arrive(name);
    }

}
