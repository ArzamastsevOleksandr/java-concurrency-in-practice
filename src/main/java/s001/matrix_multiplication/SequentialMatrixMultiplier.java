package s001.matrix_multiplication;

class SequentialMatrixMultiplier {

    static int[][] multiply(int[][] m1, int[][] m2) {
        int m1rows = m1.length;
        int m2columns = m2[0].length;
        int[][] result = new int[m1rows][m2columns];
        for (int i = 0; i < m1rows; ++i) {
            for (int k = 0; k < m2columns; ++k) {
                int sum = 0;
                for (int j = 0; j < m1[i].length; ++j) {
                    sum += m1[i][j] * m2[j][k];
                }
                result[i][k] = sum;
            }
        }
        return result;
    }

}
