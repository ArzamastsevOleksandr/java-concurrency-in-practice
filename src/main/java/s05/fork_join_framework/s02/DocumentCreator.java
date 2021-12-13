package s05.fork_join_framework.s02;

import java.util.Random;
import java.util.stream.IntStream;

class DocumentCreator {

    private static final String[] VOCABULARY = {"this", "is", "a", "very", "large", "vocabulary"};

    static String[][] create(int size) {
        var random = new Random();
        return IntStream.range(0, size)
                .mapToObj(i -> IntStream.range(0, size)
                        .mapToObj(j -> VOCABULARY[random.nextInt(VOCABULARY.length)])
                        .toArray(String[]::new)
                ).toArray(String[][]::new);
    }

}
