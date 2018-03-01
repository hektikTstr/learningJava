import java.util.Arrays;

public class Solution1TwoSum {
    public static int[] twoSum(int[] numsOrig, int target) {
        int[] nums = numsOrig.clone();
        Arrays.sort(nums); //nlog(n)
        for (int i = 0; i < nums.length; i++) {
            System.out.println("i = " + i);
            int number = target - nums[i];
            if (number < nums[i]) {
                return null;
            } else {
                int index = binarySearch(i + 1, nums.length - 1, nums, number);
                System.out.println("index = " + index);
                if (index == -1) {
                    continue;
                } else {
                    int[] ret = new int[2];
                    System.out.println("nums[i] = " + nums[i]);
                    System.out.println("nums[index] = " + nums[index]);
                    for (int j = 0; j < numsOrig.length; j++) {
                        if (ret[0] != 0 && ret[1] != 0) {
                            return ret;
                        } else if (numsOrig[j] == nums[i] && ret[0] == 0) {
                            ret[0] = j;
                            System.out.println("ret[0] = " + j);
                        } else if (numsOrig[j] == nums[index] && ret[1] == 0) {
                            ret[1] = j;
                            System.out.println("ret[1] = " + j);
                        }
                    }
                    return ret;
                }
            }
        }
        return null;
    }

    public static int binarySearch(int start, int end, int[] arr, int target) {
        if (start > end) {
            return -1;
        }
        int middle = (start + end) / 2;
        if (arr[middle] == target) {
            return middle;
        } else if (arr[middle] > target) {
            return binarySearch(start, middle - 1, arr, target);
        } else {
            return binarySearch(middle + 1, end, arr, target);
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 5, 11};
        System.out.println(Arrays.toString(twoSum(arr, 10)));
    }
}