package s05.fork_join_framework.s04;

import java.util.Random;
import java.util.stream.IntStream;

interface ArrayGenerator {

    static int[] generate(int size) {
        var random = new Random();
        return IntStream.range(0, size)
                .map(i -> random.nextInt(10))
                .toArray();
    }

}
