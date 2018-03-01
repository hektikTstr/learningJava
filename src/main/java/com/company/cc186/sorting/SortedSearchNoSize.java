import java.util.Arrays;

public class SortedSearchNoSize {
    public static class Listy {
        public int[] arr;
        public int elementAt(int i) {
            return arr[i];
        }
    }
    public static int search(Listy listy, int target) {
        return search(0, listy.arr.length - 1, listy, target);
    }
    public static int search(int start, int end, Listy listy, int target) {
        if (start > end) {
            return -1;
        }
        int middle = (start + end) / 2;
        int midValue = listy.elementAt(middle);
        if (midValue == target) {
            return middle;
        }
        if (midValue == -1 || target < midValue) {
            System.out.println(String.format("searching %d to %d", start, middle - 1));
            return search(start, middle - 1, listy, target);
        }
        return search(middle + 1, end, listy, target);
    }

    public static void main(String[] args) {
        Listy listy = new Listy();
        int[] arr = {1, 2, 2, 4, 7, 10, 13, 14, 14};
        listy.arr = Arrays.copyOf(arr, 30);
        Arrays.fill(listy.arr, arr.length, listy.arr.length, -1);
        System.out.println(Arrays.toString(listy.arr));
        System.out.println(search(listy, 2));
    }
}