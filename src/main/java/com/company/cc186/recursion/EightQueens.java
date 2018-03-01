import java.util.ArrayList;

public class EightQueens {
    public static boolean checkValid(Integer[] columns, int row1, int column1) {
        for (int row = 0; row < row1; row++) {
            if (columns[row] == column1) {
                return false;
            }
            if (Math.abs(row - row1) == Math.abs(columns[row] - column1)) {
                return false;
            }
        }
        return true;
    }

    public void placeQueens(int row, Integer[] columns, ArrayList<Integer[]> result) {
        if (row == columns.length) {
            result.add(columns.clone());
        } else {
            for (int col = 0; col < columns.length; col++) {
                if (checkValid(columns, row, col)) {
                    columns[row] = col;
                    placeQueens(row + 1, columns, result);
                }
            }
        }
    }
}