import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class BoxStack {
    public int creatStack(ArrayList<Box> boxes) {
        Collection.sort(boxes, new BoxComparator());
        int maxHeight = 0;
        for (int i = 0; i < boxes.size(); i++) {
            int height = creatStack(boxes, i);
            maxHeight = Math.max(maxHeight, height);
        }
        return maxHeight;
    }

    public int creatStack(ArrayList<Box> boxes, int bottomIndex) {
        Box bottom = boxes.get(bottomIndex);
        int maxHeight = 0;
        for (int i = bottomIndex + 1; i < boxes.size(); i++) {
            if (boxes.get(i).canBeAbove(bottom)) {
                int height = creatStack(boxes, bottomIndex);
                maxHeight = Math.max(maxHeight, height);
            }
        }
        maxHeight += bottom.height;
        return maxHeight;
    }

    class BoxComparator implements Comparator<Box> {
        @Override
        public int compare(Box x, Box y) {
            return y.height - x.height;
        }
    }
}