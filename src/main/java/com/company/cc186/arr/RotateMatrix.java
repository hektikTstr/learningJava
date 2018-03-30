import java.util.Arrays;

public class RotateMatrix {
    public static int[][] rotate(int[][] matrix) {
        for (int layer = 0; layer < matrix.length / 2; layer++) {
            for (int i = layer; i < matrix.length - 1 - layer; i++) {
                // save top row elem
                int temp = matrix[layer][i];
                // copy left column elem to top row
                matrix[layer][i] = matrix[matrix.length - 1 - i][layer];
                // copy bottom row to left column
                matrix[matrix.length - 1 - i][layer] = matrix[matrix.length - 1 - layer][matrix.length - 1 - i];
                // copy right column to bottom row
                matrix[matrix.length - 1 - layer][matrix.length - 1 - i] = matrix[i][matrix.length - 1 - layer];
                // copy temp to right column
                matrix[i][matrix.length - 1 - layer] = temp;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},
                          {4,5,6},
                          {7,8,9}};
        for (int[] arr : matrix) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();
        rotate(matrix);
        for (int[] arr : matrix) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();

        int[][] matrix2 = {{1,2},
                          {3,4}};
        for (int[] arr : matrix2) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();
        rotate(matrix2);
        for (int[] arr : matrix2) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();

        int[][] matrix3 = {{1,2,3,4},
                          {4,5,6,7},
                          {7,8,9,10},
                          {10,11,12,13}};
        for (int[] arr : matrix3) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();
        rotate(matrix3);
        for (int[] arr : matrix3) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();

        int[][] matrix4 = {{1,2,3,4,5},
                          {4,5,6,7,8},
                          {7,8,9,10,11},
                          {10,11,12,13,14},
                          {14,15,16,17,18}};
        for (int[] arr : matrix4) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();
        rotate(matrix4);
        for (int[] arr : matrix4) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();
    }
}