import java.util.Arrays;

public class PeakAndValley {
    public int[] rearrange(int[] origArr) {
        int[] ret = origArr.clone();
        Arrays.sort(ret);
        for (int i = 1; i < ret.length; i += 2) {
            swap(i - 1, i, ret);
        }
        return ret;
    }

    public void swap(int idx1, int idx2, int[] arr) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
    public static void main(String[] args) {
        int[] arr = {7, 9, 8, 0, 1, 4, 7, 9, 8, 8};
        System.out.println(Arrays.toString(new PeakAndValley().rearrange(arr)));
    }
}