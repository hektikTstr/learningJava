import java.util.Random;

public class GetRandomNode {
    public class TreeNode {
        private int data;
        public TreeNode left;
        public TreeNode right;
        private int size = 0;

        public TreeNode(int data) {
            this.data = data;
            this.size = 1;
        }

        public TreeNode getRandomNode() {
            int leftSize = left == null ? 0 : left.size;
            Random random = new Random();
            int index = random.nextInt(size);
            if (index < leftSize) {
                return left.getRandomNode();
            } else if (index == leftSize) {
                return this;
            } else {
                return right.getRandomNode();
            }
        }

        public void insert(int data) {
            if (this.data <= data) {
                if (left == null) {
                    left = new TreeNode(data);
                } else {
                    left.insert(data);
                }
            } else {
                if (right == null) {
                    right = new TreeNode(data);
                } else {
                    right.insert(data);
                }
            }
            size++;
        }

        public TreeNode find(int data) {
            if (data < this.data) {
                if (left != null) {
                    return left.find(data);
                } else {
                    return null;
                }
            } else if (data == this.data) {
                return this;
            } else {
                if (right != null) {
                    return right.find(data);
                } else {
                    return null;
                }
            }
        }
    }
}