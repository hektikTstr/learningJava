public class Matrix {
    public static void main(String[] args) {
        int[][] arrA = new int[3][3];
        System.out.println("arrA length = " + arrA.length);
        System.out.println("arrA[0][1] = " + arrA[0][1]);
        int[][] arrB = new int[4][];
        System.out.println("arrB length = " + arrB.length);
        arrB[0] = new int[]{1, 2, 3};
        arrB[1] = new int[]{3, 4};
        System.out.println("arrB[0][1] = " + arrB[0][1]);
        System.out.println("arrB[1][1] = " + arrB[1][1]);
    }
}