public class MagicIndex {
    public static int magic(int[] arr, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (arr[mid] == mid) {
            return mid;
        } else if (arr[mid] < mid) {
            return magic(arr, mid + 1, end);
        } else {
            return magic(arr, start, mid - 1);
        }
    }
}