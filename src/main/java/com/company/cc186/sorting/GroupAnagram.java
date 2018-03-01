import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class GroupAnagram {
    public static class AnagramComparator implements Comparator<String> {
        public String sortStr(String str) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            return new String(arr);
        }
        public int compare(String a, String b) {
            return sortStr(a).compareTo(sortStr(b));
        }
    }

    public static String[] groupAnagram(String[] strs) {
        Arrays.sort(strs, new AnagramComparator());
        return strs;
    }

    public static String[] groupAnagramHash(String[] strs) {
        AnagramComparator cmp = new AnagramComparator();
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String str : strs) {
            String sortedStr = cmp.sortStr(str);
            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }
            map.get(sortedStr).add(str);
        }

        int index = 0;
        for (String str : map.keySet()) {
            List<String> list = map.get(str);
            for (String str1 : list) {
                strs[index++] = str1;
            }
        }
        return strs;
    }
    public static void main(String[] args) {
        String[] strs = {"ba", "bac", "ab", "cba", "abc", "aac", "caa", "abcd", "bbc", "cbb"};
        strs = groupAnagramHash(strs);
        System.out.println(Arrays.toString(strs));
    }
}