package s001.matrix_multiplication;

import java.util.Arrays;
import java.util.stream.Collectors;

class MatrixUtil {

    public static String matrixToString(int[][] matrix) {
        return Arrays.stream(matrix)
                .map(Arrays::toString)
                .collect(Collectors.joining("\n"));
    }

}
