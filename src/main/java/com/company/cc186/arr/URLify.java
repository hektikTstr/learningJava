import java.util.Arrays;

public class URLify {
    public static char[] urlify(char[] arr, int trueLength) {
        int spaceCounter = countSpace(arr, trueLength);
        int extraLength = spaceCounter * 2;
        int orignalEnd = trueLength - 1;
        int j = orignalEnd + extraLength;
        for (int i = orignalEnd; i >= 0; i--) {
            if (arr[i] != ' ') {
                arr[j] = arr[i];
                j--;
            } else {
                arr[j--] = '0';
                arr[j--] = '2';
                arr[j--] = '%';
            }
        }
        System.out.println(new String(arr));
        return arr;
    }


    public static int countSpace(char[] arr, int trueLength) {
        int counter = 0;
        for (int i = 0; i < trueLength; i++) {
            if (arr[i] == ' ') {
                counter++;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        char[] arr = "Mr John Smith    ".toCharArray();
        urlify(arr, 13);
    }
}