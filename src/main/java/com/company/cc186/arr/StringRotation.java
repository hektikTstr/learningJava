public class StringRotation {
    public static boolean isSubstring(String origStr, String subStr) {
        return origStr.contains(subStr);
    }

    public static boolean check(String origStr, String rotatedStr) {
        String destStr = rotatedStr + rotatedStr;
        return origStr.length() == rotatedStr.length() && isSubstring(destStr, origStr);
    }

    public static void main(String[] args) {
        System.out.println(check("abcd", "cdab"));
        System.out.println(check("abcd", "bcda"));
        System.out.println(check("abcd", "abcda"));
    }
}