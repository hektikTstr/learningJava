import java.awt.Point;
import java.util.ArrayList;

public class RobotInAGrid {
    public static boolean getPath(boolean[][] maze, int row, int col, ArrayList<Point> path) {
        // if out of bound or unavaliable
        if (col < 0 || row < 0 || !maze[col][row]) {
            return false;
        }
        boolean isAtOrigin = (row == 0) && (col == 0);
        // if there's a path from the start to here, add my location
        if (isAtOrigin || getPath(maze, row - 1, col, path)
            || getPath(maze, row, col - 1, path)) {
            Point p = new Point(row, col);
            path.add(p);
            return true;
        }
        return false;
    }

    public static ArrayList<Point> getPath(boolean[][] maze) {
        if (maze == null) {
            return null;
        }
        ArrayList<Point> path = new ArrayList<>();
        if (getPath(maze, maze.length - 1, maze[0].length - 1, path)) {
            return path;
        }
        return null;
    }

    public static void main(String[] args) {
        
    }
}