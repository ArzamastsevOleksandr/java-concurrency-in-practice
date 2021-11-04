package s001.matrix_multiplication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.stream.IntStream;

public class ExecutionTimeTracker {

    public static Measurements measure(Runnable runnable, int times, String type) {
        var measurements = new Measurements(type);
        long avg = IntStream.range(0, times)
                .mapToLong(i -> measure(runnable, measurements))
//                .peek(i -> System.out.printf("Time taken: %s\n", i))
                .sum() / times;
        measurements.setAvgMs(avg);
        return measurements;
    }

    private static long measure(Runnable runnable, Measurements measurements) {
        long start = System.currentTimeMillis();
        runnable.run();
        long end = System.currentTimeMillis();

        measurements.incCycles();
        return end - start;
    }

    @Getter
    @ToString
    @RequiredArgsConstructor
    public static class Measurements {

        private final String type;

        private long cycles;
        private long avgMs;

        public void incCycles() {
            this.cycles++;
        }

        public void setAvgMs(long avg) {
            this.avgMs = avg;
        }

    }

}
