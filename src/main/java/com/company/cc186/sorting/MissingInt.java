import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MissingInt {
    public static long numberOfInts = ((long) Integer.MAX_VALUE + 1);
    public static int getMissingInt(String filePath) {
        byte[] bitVec = new byte[(int) (numberOfInts / 8)];
        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextInt()) {
                int i = sc.nextInt();
                bitVec[i / 8] |= 1 << (i % 8);
            }
            sc.close();
            for (int x = 0; x < bitVec.length; x++) {
                for (int y = 0; y < 8; y++) {
                    if ((bitVec[x] & (1 << y)) == 0) {
                        return x * 8 + y;
                    } 
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(getMissingInt("nonNegNumbers"));
    }

    // follow up, if we only have 10MB memory
    public static int[] getCountPerBlock(String fileName, int rangeSize) throws FileNotFoundException {
        int arraySize = Integer.MAX_VALUE / rangeSize + 1;
        int[] blocks = new int[arraySize];
        Scanner sc = new Scanner(new File(fileName));
        while (sc.hasNextInt()) {
            int next = sc.nextInt();
            blocks[next / rangeSize]++;
        }
        sc.close();
        return blocks;
    }

    public static int findBlockWithMissing(int[] blocks, int rangeSize) {
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] < rangeSize) {
                return i;
            }
        }
        return -1;
    }

    public static byte[] getBitVectorForRange(String fileName, int blockIndex, int rangeSize) throws FileNotFoundException {
        int startRange = blockIndex * rangeSize;
        int endRange = startRange + rangeSize;
        byte[] bitVec = new byte[rangeSize / Byte.SIZE];
        Scanner sc = new Scanner(new File(fileName));
        while (sc.hasNextInt()) {
            int next = sc.nextInt();
            if (startRange <= next && next < endRange) {
                int offset = next - startRange;
                int mask = (1 << (offset % Byte.SIZE));
                bitVec[offset / Byte.SIZE] |= mask;
            }
        }
        sc.close();
        return bitVec;
    }

    public static int findZero(byte b) {
        for (int j = 0; j < Byte.SIZE; j++) {
            if ((b & (1 << j)) == 0) {
                return j;
            }
        }
        return -1;
    }

    public static int findZero(byte[] bitVec) {
        for (int i = 0; i < bitVec.length; i++) {
            if (bitVec[i] != ~0) {
                int index = findZero(bitVec[i])
                return i * Byte.SIZE + findZero(bitVec[i]);
            }
        }
    }
}