package s03.thread_synchronization_utilities.s04.exam;

import util.ThreadUtil;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

class Main {

    public static void main(String[] args) {
        var phaser = new ExamPhaser();

        List<Thread> threads = IntStream.range(0, 5)
                .mapToObj(i -> new Thread(new Student(phaser), Student.class.getSimpleName() + " " + i))
                .collect(toList());

        threads.forEach(Thread::start);
        threads.forEach(ThreadUtil::join);

        System.out.printf("Phaser terminated?: %s\n", phaser.isTerminated());
    }

}
