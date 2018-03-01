import java.util.ArrayList;
import java.util.HashMap;

public class PermutationWithDups {
    public static HashMap<Character, Integer> buildFrequencyMap(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        return map;
    }

    public static void printPerms(HashMap<Character, Integer> map, String prefix, int remaining, ArrayList<String> result) {
        if (remaining == 0) {
            result.add(prefix);
            System.out.println(prefix);
            return;
        }
        for (Character c : map.keySet()) {
            int count = map.get(c);
            if (count > 0) {
                map.put(c, count - 1);
                printPerms(map, prefix + c, remaining - 1, result);
                map.put(c, count);
            }
        }
    }

    public static ArrayList<String> printPerms(String str) {
        ArrayList<String> result = new ArrayList<>();
        printPerms(buildFrequencyMap(str), "", str.length(), result);
        return result;
    }

    public static void main(String[] args) {
        // System.out.println(buildFrequencyMap("abbaccd"));
        printPerms(buildFrequencyMap("aaab"), "", 4, new ArrayList<>());
    }
}