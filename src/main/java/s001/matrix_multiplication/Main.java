package s001.matrix_multiplication;

public class Main {

    public static final int M1_ROWS = 200;
    public static final int M1_COLUMNS = 300;
    public static final int M2_COLUMNS = 400;
    public static final int CYCLES = 10;

    public static void main(String[] args) {
        ExecutionTimeTracker.Measurements sequentialMeasurements = ExecutionTimeTracker.measure(() -> {
            int[][] m1 = MatrixGenerator.generate(M1_ROWS, M1_COLUMNS);
            int[][] m2 = MatrixGenerator.generate(M1_COLUMNS, M2_COLUMNS);
            SequentialMatrixMultiplier.multiply(m1, m2);
        }, CYCLES);
        System.out.printf("Sequential: %s ms\n", sequentialMeasurements);
    }

}
