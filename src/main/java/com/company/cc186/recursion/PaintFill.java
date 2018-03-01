public class PaintFill {
    enum Color {Black, White, Red, Yellow, Green}

    boolean paintFill(Color[][] screen, int r, int c, Color oColor, Color nColor) {
        if (r < 0 || r >= screen.length
            || c < 0 || c >= screen.length) {
            return false;
        }
        if (screen[r][c] == oColor) {
            screen[r][c] = nColor;
            paintFill(screen, r + 1, c, oColor, nColor);
            paintFill(screen, r - 1, c, oColor, nColor);
            paintFill(screen, r, c + 1, oColor, nColor);
            paintFill(screen, r, c - 1, oColor, nColor);
        }
        return true;
    }

    boolean paintFill(Color[][] screen, int r, int c, Color nColor) {
        if (screen[r][c] == nColor) {
            return false;
        }
        return paintFill(screen, r, c, screen[r][c], nColor);
    }
}