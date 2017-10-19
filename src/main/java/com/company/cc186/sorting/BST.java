
public class BST {
    public static class Node {
        Node left, right;
        int data;
        public Node(int val) {
            data = val;
        }
    }

    public static boolean isPresent(Node root, int val) {
        if (root == null) {
            return false;
        } else if (root.data == val) {
            return true;
        } else if (root.data > val) {
            return isPresent(root.left, val);
        } else {
            return isPresent(root.right, val);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(20);
        root.left = new Node(10);
        root.right = new Node(30);
        root.left.left = new Node(8);
        root.left.right = new Node(12);
        root.right.left = new Node(25);
        root.right.right = new Node(40);
        System.out.println(isPresent(root, 30));
        System.out.println(isPresent(root, 10));
        System.out.println(isPresent(root, 12));
        System.out.println(isPresent(root, 15));
        System.out.println();
        System.out.println(isPresent(root, 79));
        System.out.println(isPresent(root, 10));
        System.out.println(isPresent(root, 20));
        System.out.println(isPresent(root, 30));
        System.out.println(isPresent(root, 40));
        System.out.println();
        System.out.println(isPresent(null, 40));
    }
}
