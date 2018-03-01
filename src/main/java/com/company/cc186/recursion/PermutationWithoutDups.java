import java.util.ArrayList;

public class PermutationWithoutDups {
    public static void permute(String prefix, String remain, ArrayList<String> permutation) {
        if (remain.isEmpty() && !prefix.isEmpty()) {
            permutation.add(prefix);
        } else {
            // StringBuilder sbPrefix = new StringBuilder(prefix);
            // StringBuilder sbRemain = new StringBuilder(remain);
            for (int i = 0; i < remain.length(); i++) {
                char ch = remain.charAt(i);
                String newPrefix = new StringBuilder(prefix).append(ch).toString();
                String newRemain = new StringBuilder(remain).deleteCharAt(i).toString();
                permute(newPrefix, newRemain, permutation);
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<String> permutation = new ArrayList<>();
        permute("", "abcdef", permutation);
        System.out.println(permutation.size());
        for (String s : permutation) {
            System.out.println(s);
        }
    }
}