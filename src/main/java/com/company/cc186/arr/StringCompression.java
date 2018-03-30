import java.lang.StringBuilder;

public class StringCompression {
    public static String compress(String str) {
        StringBuilder sb = new StringBuilder();
        int charCount = 0;
        for (int i = 0; i < str.length(); i++) {
            charCount++;
            if (i == str.length() - 1 || str.charAt(i) != str.charAt(i + 1)) {
                sb.append(str.charAt(i));
                sb.append(charCount);
                charCount = 0;
            }
        }
        return str.length() <= sb.length() ? str : sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(compress(""));
        System.out.println(compress("a"));
        System.out.println(compress("aabcccddeeee"));
        System.out.println(compress("aabbccdd"));
    }
}