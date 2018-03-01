import java.util.HashMap;

public class Parens {
    public static HashMap<String, Boolean> getParens(int count) {
        HashMap<String, Boolean> map = new HashMap<>();
        if (count == 1) {
            map.put("()", true);
        } else {
            HashMap<String, Boolean> prevMap = getParens(count - 1);
            for (String s : prevMap.keySet()) {
                String str = "()" + s;
                if (!map.containsKey(str)) {
                    map.put(str, true);
                }
                str = "(" + s + ")";
                if (!map.containsKey(str)) {
                    map.put(str, true);
                }
                str = s + "()";
                if (!map.containsKey(str)) {
                    map.put(str, true);
                }
            }
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(getParens(1).keySet());
        System.out.println(getParens(2).keySet());
        System.out.println(getParens(3).keySet());
    }
}