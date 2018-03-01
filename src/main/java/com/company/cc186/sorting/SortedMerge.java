import java.util.Arrays;

public class SortedMerge {
    public static void merge(int[] arr1, int size, int[] arr2) {
        int index1 = size - 1;
        int index2 = arr2.length - 1;
        while (index1 >= 0 && index2 >= 0) {
            if (arr1[index1] >= arr2[index2]) {
                arr1[index1 + index2 + 1] = arr1[index1];
                index1--;
            } else {
                arr1[index1 + index2 + 1] = arr2[index2];
                index2--;
            }
            System.out.println(Arrays.toString(arr1)); 
        }
        while (index2 >= 0) {
            arr1[index2] = arr2[index2];
            index2--;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[20];
        System.out.println(Arrays.toString(arr1));
        int[] arr = {0, 3, 3, 5, 8, 9};
        for (int i = 0; i < arr.length; i++) {
            arr1[i] = arr[i];
        }
        int[] arr2 = {1, 3, 7};
        merge(arr1, arr.length, arr2);
        System.out.println(Arrays.toString(arr1));
    }
}