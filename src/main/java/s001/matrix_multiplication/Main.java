package s001.matrix_multiplication;

class Main {

    static final int M1_ROWS = 400;
    static final int M1_COLUMNS = 600;
    static final int M2_ROWS = 600;
    static final int M2_COLUMNS = 300;
    static final int CYCLES = 10;

    static {
        if (M1_COLUMNS != M2_ROWS) {
            throw new AssertionError("m1 columns must be equal to m2.rows, got " + M1_COLUMNS + ", " + M2_ROWS + " respectively");
        }
    }

    public static void main(String[] args) {
        ExecutionTimeTracker.Measurements sequentialMeasurements = ExecutionTimeTracker.measure(() -> {
            int[][] m1 = MatrixGenerator.generate(M1_ROWS, M1_COLUMNS);
            int[][] m2 = MatrixGenerator.generate(M2_ROWS, M2_COLUMNS);
            int[][] multiply = SequentialMatrixMultiplier.multiply(m1, m2);
//            System.out.println(Arrays.toString(multiply[0]));
        }, CYCLES, "sequential");
        System.out.println(sequentialMeasurements);

        ExecutionTimeTracker.Measurements parallelMeasurements = ExecutionTimeTracker.measure(() -> {
            int[][] m1 = MatrixGenerator.generate(M1_ROWS, M1_COLUMNS);
            int[][] m2 = MatrixGenerator.generate(M1_COLUMNS, M2_COLUMNS);
            int[][] multiply = ParallelMatrixMultiplier.multiply(m1, m2);
//            System.out.println(Arrays.toString(multiply[0]));
        }, CYCLES, "parallel");
        System.out.println(parallelMeasurements);

        System.out.printf("Speed up: %.2f\n", sequentialMeasurements.getAvgMs() * 1.0 / parallelMeasurements.getAvgMs());
    }

}
