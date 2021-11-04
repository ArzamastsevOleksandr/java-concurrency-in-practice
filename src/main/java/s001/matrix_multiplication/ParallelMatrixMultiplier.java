package s001.matrix_multiplication;

import java.util.ArrayList;

class ParallelMatrixMultiplier {

    static int[][] multiply(int[][] m1, int[][] m2) {
        int[][] result = new int[m1.length][m2[0].length];
        int availableProcessors = Runtime.getRuntime().availableProcessors();

        ArrayList<MatrixMultiplier> threads = new ArrayList<>();
        for (int i = 0; i < availableProcessors; ++i) {
            int left = (m1.length / availableProcessors) * i;
            int right = (m1.length / availableProcessors) * (i + 1);
            var multiplier = new MatrixMultiplier(m1, m2, result, left, right);
            threads.add(multiplier);
        }
        for (var thread : threads) {
            thread.start();
        }
        for (var thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static class MatrixMultiplier extends Thread {

        private final int[][] ma;
        private final int[][] mb;
        private final int[][] result;
        private final int left;
        private final int right;

        public MatrixMultiplier(int[][] ma, int[][] mb, int[][] result, int left, int right) {
            this.ma = ma;
            this.mb = mb;
            this.result = result;
            this.left = left;
            this.right = right;
        }

        @Override
        public void run() {
            for (int i = left; i < right; ++i) {
                for (int j = 0; j < mb[0].length; ++j) {
                    int sum = 0;
                    for (int k = 0; k < ma[i].length; ++k) {
                        sum += ma[i][k] * mb[k][j];
                    }
                    result[i][j] = sum;
                }
            }
        }

    }

}
