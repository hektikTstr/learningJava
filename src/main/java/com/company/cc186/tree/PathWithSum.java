
// Not the optimized solution
public class PathWithSum {
    public static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { data = x; }
    }

    public static int countPathWithSumFromNode(TreeNode node, int targetSum, int currentSum) {
        if (node == null) {
            return 0;
        }
        currentSum += node.data;
        int totalNum = 0;
        if (currentSum == targetSum) {
            totalNum++;
        }
        totalNum += countPathWithSumFromNode(node.left, targetSum, currentSum);
        totalNum += countPathWithSumFromNode(node.right, targetSum, currentSum);
        return totalNum;
    }

    public static int countPathWithSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int totalNum = countPathWithSumFromNode(root, targetSum, 0);
        totalNum += countPathWithSum(root.left, targetSum);
        totalNum += countPathWithSum(root.right, targetSum);
        return totalNum;
    }
}