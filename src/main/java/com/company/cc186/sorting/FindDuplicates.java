import java.util.BitSet;

public class FindDuplicates {
    public static void printAllDuplicates(int[] arr) {
        BitSet bs = new BitSet(32000);
        for (int i : arr) {
            int num = i - 1;
            if (bs.get(num)) {
                System.out.println(i);
            } else {
                bs.set(num);
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 6, 7, 2, 1, 8, 8, 9, 32000, 100, 32000};
        printAllDuplicates(a);
        System.out.println(32 & 0x1f);
    }
}