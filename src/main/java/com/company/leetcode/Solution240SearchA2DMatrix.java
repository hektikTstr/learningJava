import java.util.Arrays;

public class Solution240SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        // calculate row range
        int[] start = new int[matrix.length];
        int[] end = new int[matrix.length];
        for (int row = 0; row < start.length; row++) {
            start[row] = matrix[row][0];
        }
        int colLen = matrix[0].length;
        for (int row = 0; row < end.length; row++) {
            end[row] = matrix[row][colLen - 1];
        }
        // System.out.println(Arrays.toString(start));
        // System.out.println(Arrays.toString(end));
        int[] rowRange = calculateRange(target, start, end);
        // System.out.println(Arrays.toString(rowRange));

        // calculate col range
        start = new int[colLen];
        end = new int[colLen];
        for (int col = 0; col < colLen; col++) {
            start[col] = matrix[0][col];
        }
        for (int col = 0; col < colLen; col++) {
            end[col] = matrix[matrix.length - 1][col];
        }
        // System.out.println(Arrays.toString(start));
        // System.out.println(Arrays.toString(end));
        int[] colRange = calculateRange(target, start, end);
        // System.out.println(Arrays.toString(colRange));

        return searchMatrixInRange(rowRange, colRange, matrix, target);
    }

    public boolean searchMatrixInRange(int[] rowRange, int[] colRange, int[][] matrix, int target) {
        if (rowRange == null || colRange == null) {
            return false;
        }
        for (int row = rowRange[0]; row <= rowRange[1]; row++) {
            for (int col = colRange[0]; col <= colRange[1]; col++) {
                if (matrix[row][col] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public int[] calculateRange(int target, int[] start, int[] end) {
        int[] ret = new int[]{-1, -1};
        for (int i = 0; i < end.length; i++) {
            if (target <= end[i]) {
                ret[0] = i;
                break;
            }
        }
        if (ret[0] == -1) {
            return null;
        }
        for (int i = start.length - 1; i >= 0; i--) {
            if (target >= start[i]) {
                ret[1] = i;
                break;
            }
        }
        if (ret[1] == -1) {
            return null;
        }
        if (ret[0] > ret[1]) {
            return null;
        }
        return ret;
    }

    public static void main(String[] args) {
        Solution240SearchA2DMatrix solution = new Solution240SearchA2DMatrix();
        int[][] matrix = {  {1,  4,  7,  11, 15, 17},
                            {2,  5,  8,  12, 19, 20},
                            {3,  6,  9,  16, 22, 23},
                            {10, 13, 14, 17, 24, 24},
                            {18, 22, 23, 26, 30, 35}};

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.println(matrix[row][col] + " = " + solution.searchMatrix(matrix, matrix[row][col]));
            }
        }

        System.out.println(solution.searchMatrix(matrix, 0));
        System.out.println(solution.searchMatrix(matrix, -1));
        System.out.println(solution.searchMatrix(matrix, 21));
        System.out.println(solution.searchMatrix(matrix, 33));
        System.out.println(solution.searchMatrix(matrix, 36));
        System.out.println(solution.searchMatrix(matrix, 50));
        // System.out.println(solution.searchMatrix(matrix, 22));
        // System.out.println(solution.searchMatrix(matrix, 25));
        // System.out.println(solution.searchMatrix(matrix, 0));
    }
}
