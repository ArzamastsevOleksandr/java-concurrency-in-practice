package s001.matrix_multiplication;

class MatrixGenerator {

    static final int DEFAULT_CELL_VALUE = 1;

    static int[][] generate(int rows, int columns) {
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                matrix[i][j] = DEFAULT_CELL_VALUE;
            }
        }
        return matrix;
    }

}
