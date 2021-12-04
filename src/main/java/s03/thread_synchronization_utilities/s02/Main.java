package s03.thread_synchronization_utilities.s02;

import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        int participants = 5;
        var videoConference = new VideoConference(participants);

        new Thread(videoConference).start();

        IntStream.range(0, participants)
                .mapToObj(i -> new Participant(videoConference, "P " + i))
                .map(Thread::new)
                .forEach(Thread::start);
    }

}
