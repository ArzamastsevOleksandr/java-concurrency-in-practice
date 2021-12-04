package s03.thread_synchronization_utilities.s03;

import java.util.Arrays;
import java.util.stream.Collectors;

class MatrixMock {

    private final int[][] matrix;

    MatrixMock(int size) {
        matrix = getMatrix(size);
    }

    private int[][] getMatrix(int size) {
        int[][] ints = new int[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                ints[i][j] = (int) (Math.random() * 5);
            }
        }
        return ints;
    }

    int[] getRow(int row) {
        if (row >= 0 && row < matrix.length) {
            return matrix[row];
        }
        return null;
    }

    int countOf(int target) {
        return (int) Arrays.stream(matrix)
                .mapToLong(ints -> Arrays.stream(ints).filter(i -> i == target).count())
                .reduce(0L, Long::sum);
    }

    @Override
    public String toString() {
        return Arrays.stream(matrix)
                .map(Arrays::toString)
                .collect(Collectors.joining("\n"));
    }

}
