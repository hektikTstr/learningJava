public class SearchInRotatedArray {
    public static int search(int start, int end, int[] arr, int target) {
        if (start > end) {
            return -1;
        }
        int middle = (start + end) / 2;
        if (arr[middle] == target) {
            return middle;
        } else if (arr[middle] < target) {
            if (arr[middle] >= arr[start]) {
                return search(middle + 1, end, arr, target);
            } else {
                if (arr[start] <= target) {
                    return search(start, middle - 1, arr, target);
                } else {
                    return search(middle + 1, end, arr, target);
                }
            }
        } else {
            if (arr[middle] < arr[start]) {
                return search(start, middle - 1, arr, target);
            } else {
                if (arr[start] > target) {
                    return search(middle + 1, end, arr, target);
                } else {
                    return search(start, middle - 1, arr, target);
                }
            }
        }
    }

    public static int searchOptimizedNoDup(int start, int end, int[] arr, int target) {
        if (start > end) {
            return -1;
        }
        int middle = (start + end) / 2;
        if (arr[middle] == target) {
            return middle;
        }
        if (arr[start] <= arr[middle]) {
            if (arr[start] <= target && target < arr[middle]) {
                return searchOptimizedNoDup(start, middle - 1, arr, target);
            }
            return searchOptimizedNoDup(middle + 1, end, arr, target);
        } else {
            if (arr[middle] < target && target <= arr[end]) {
                return searchOptimizedNoDup(middle + 1, end, arr, target);
            }
            return searchOptimizedNoDup(start, middle - 1, arr, target);
        }
    }

    public static int findPivot(int start, int end, int[] arr) {
        if (start > end) {
            return -1;
        }
        if (arr[start] <= arr[end]) {
            return start;
        }
        int middle = (start + end) / 2;
        if (arr[start] <= arr[middle]) {
            return findPivot(middle + 1, end, arr);
        }
        return findPivot(start, middle, arr);
    }

    public static int findPivot(int[] arr) {
        return findPivot(0, arr.length - 1, arr);
    }

    public static int searchByPivotNoDup(int[] arr, int target) {
        int pivot = findPivot(arr);
        System.out.println("pivot = " + pivot);
        if (pivot == -1) {
            return -1;
        }
        if (pivot == 0) {
            return binarySearch(0, arr.length - 1, arr, target);
        }
        if (target >= arr[0]) {
            return binarySearch(0, pivot - 1, arr, target);
        }
        return binarySearch(pivot, arr.length - 1, arr, target);
    }

    public static int binarySearch(int start, int end, int[] arr, int target) {
        if (start > end) {
            return -1;
        }
        int middle = (start + end) / 2;
        if (arr[middle] == target) {
            return middle;
        } else if (target < arr[middle]) {
            return binarySearch(start, middle - 1, arr, target);
        } else {
            return binarySearch(middle + 1, end, arr, target);
        }
    }

    public static boolean testFindPivot() {
        int[] arr1 = {13, 15, 19, 24, 0, 3, 7, 10, 11};
        int[] arr2 = {13, 15, 19, 24, 0, 3, 7, 10};
        int[] arr3 = {9, 11, 13, 15, 3, 7};
        int[] arr4 = {9, 11, 13, 15};
        int[] arr5 = {15, 3, 7};
        int[] arr6 = {15, 3};
        int[] arr7 = {15};
        int[] arr8 = {3, 15};
        boolean result1 = findPivot(arr1) == 4;
        boolean result2 = findPivot(arr2) == 4;
        boolean result3 = findPivot(arr3) == 4;
        boolean result4 = findPivot(arr4) == 0;
        boolean result5 = findPivot(arr5) == 1;
        boolean result6 = findPivot(arr6) == 1;
        boolean result7 = findPivot(arr7) == 0;
        boolean result8 = findPivot(arr8) == 0;
        return result1 && result2 && result3 && result4 && result5 && result6 && result7 && result8;
    }

    public static void main(String[] args) {
        System.out.println(testFindPivot());
        // int[] arr1 = {2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        // for (int i : arr1) {
        //     System.out.println(searchOptimizedNoDup(0, arr1.length - 1, arr1, i));
        // }
        int[] arr2 = {13, 15, 19, 24, 0, 3, 7, 10, 11};
        for (int i : arr2) {
            System.out.println(searchByPivotNoDup(arr2, i));
        }
        System.out.println("binary search test.");
        int[] arr3 = {0, 3, 7, 10, 11, 13, 15, 19, 24};
        for (int i : arr3) {
            System.out.println(binarySearch(0, arr3.length - 1, arr3, i));
        }
        // int[] arr3 = {0, 3, 7, 10, 11, 13, 15, 19, 24};
        // for (int i : arr3) {
        //     System.out.println(searchOptimizedNoDup(0, arr3.length - 1, arr3, i));
        // }
        // System.out.println(search(0, arr1.length - 1, arr1, -2));
    }
}