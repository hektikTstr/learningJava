import java.util.HashMap;
import java.util.Map;

public class PalinDromePermutation {
    public static boolean check(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        System.out.println(map.toString());
        int oddCount = 0;
        for (int i : map.values()) {
            if (i % 2 == 1) {
                oddCount++;
            }
            if (oddCount > 1) {
                return false;
            }
        }
        // if (str.length() % 2 == 0 && oddCount == 1) {
        //     return false;
        // }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(check("abcab"));
        System.out.println(check("abcabc"));
        System.out.println(check("abcaEbcD"));
        System.out.println(check("tactcoapapa"));
    }
}