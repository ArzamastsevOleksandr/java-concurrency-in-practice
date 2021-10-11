package s001.matrix_multiplication;

import java.util.stream.IntStream;

public class ExecutionTimeTracker {

    public static Measurements measure(Runnable runnable, int times) {
        var measurements = new Measurements();
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

    public static class Measurements {
        private long cycles;
        private long avgMs;

        public void incCycles() {
            this.cycles++;
        }

        public void setAvgMs(long avg) {
            this.avgMs = avg;
        }

        @Override
        public String toString() {
            return "Measurements{" +
                    "cycles=" + cycles +
                    ", avgMs=" + avgMs +
                    '}';
        }

    }

}
