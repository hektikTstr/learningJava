public class SparseSearch {
    public static int search(int start, int end, String[] strings, String target) {
        System.out.println("start = " + start + " end = " + end);
        if (start > end) {
            return -1;
        }
        int middle = (start + end) / 2;
        if (strings[middle].isEmpty()) {
            int left = middle - 1;
            int right = middle + 1;
            while (true) {
                if (left < start && right > end) {
                    return -1;
                } else if (left >= start && !strings[left].isEmpty()) {
                    middle = left;
                    System.out.println("middle = " + middle);
                    break;
                } else if (right <= end && !strings[right].isEmpty()) {
                    middle = right;
                    System.out.println("middle = " + middle);
                    break;
                }
                left--;
                right++;
            }
        }
        if (strings[middle].equals(target)) {
            return middle;
        } else if (strings[middle].compareTo(target) > 0) {
            return search(start, middle - 1, strings, target);
        } else {
            return search(middle + 1, end, strings, target);
        }
    }

    public static void main(String[] args) {
        String[] strings = {"", "ab", "", "", "ba", "ca", "db", "", "", "edb", "", ""};
        System.out.println(strings.length);
        System.out.println(search(0, strings.length - 1, strings, "ed"));
    }
}