import java.util.Arrays;
import java.util.Random;

public class QuickSortNew {
    public static void quickSort(int start, int end, int[] arr) {
        if (start < end) {
            System.out.println("start: " + start + " end: " + end);
            System.out.println("before reorder: " + Arrays.toString(arr)); 
            int midIdx = reorder(start, end, arr);
            quickSort(start, midIdx - 1, arr);
            quickSort(midIdx + 1, end, arr);
        }
    }

    public static int reorder(int start, int end, int[] arr) {
        int midNum = arr[end];
        int firstBiggerNumberIndex = -1;
        for (int i = start; i < end; i++) {
            if (arr[i] > midNum) {
                if (firstBiggerNumberIndex == -1) {
                    firstBiggerNumberIndex = i; 
                }
            } else if (firstBiggerNumberIndex != -1) {
                swap(i, firstBiggerNumberIndex, arr);
                firstBiggerNumberIndex++;
                System.out.println("after swap: " + Arrays.toString(arr));
            }
        }
        if (firstBiggerNumberIndex != -1) {
            swap(firstBiggerNumberIndex, end, arr);
            return firstBiggerNumberIndex;
        }
        return end;
    }

    public static void swap(int x, int y, int[] arr) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void main(String[] args) {
        int[] intArr = new int[10];
        for (int i = 0; i < intArr.length; i++) {
            Random random = new Random();
            intArr[i] = random.nextInt(10);
        }
        // int[] intArr = new int[]{7, 5, 3, 6, 8, 3, 2, 6};
        System.out.println(Arrays.toString(intArr));
        quickSort(0, intArr.length - 1, intArr);
        // reorder(0, intArr.length - 1, intArr);
        System.out.println(Arrays.toString(intArr));
    }
}