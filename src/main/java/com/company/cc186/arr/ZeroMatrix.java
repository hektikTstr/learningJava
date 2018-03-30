import java.util.Arrays;

public class ZeroMatrix {
    public static int[][] zeroMatrix(int[][] matrix) {
        if (matrix == null || matrix[0].length == 0) {
            return matrix;
        }
        boolean[] rowZeros = new boolean[matrix.length];
        boolean[] colZeros = new boolean[matrix[0].length];

        // iterate through the matrix to set rowZeros and colZeros
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 0) {
                    rowZeros[row] = true;
                    colZeros[col] = true;
                }
            }
        }

        // set rows true when their representative elems rowZeros are true
        for (int row = 0; row < rowZeros.length; row++) {
            if (rowZeros[row]) {
                for (int i = 0; i < matrix[row].length; i++) {
                    matrix[row][i] = 0;
                }
            }
        }

        // set columns true when their representative elems rowZeros are true
        for (int col = 0; col < colZeros.length; col++) {
            if (colZeros[col]) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][col] = 0;
                }
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},
                          {4,0,6},
                          {7,8,9}};
        zeroMatrix(matrix);
        for (int[] arr : matrix) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();

        int[][] matrix2 = {{1,7,8,2},
                          {0,3,1,0},
                          {2,8,9,4},
                          {6,5,10,0}};
        zeroMatrix(matrix2);
        for (int[] arr : matrix2) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();
    }
}