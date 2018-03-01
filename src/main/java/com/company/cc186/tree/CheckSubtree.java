
public class CheckSubtree {
    public class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { data = x; }
    }
    public static void main(String[] args) {
        
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        StringBuilder sbSrc = new StringBuilder();
        preTraverse(s, sbSrc);
        StringBuilder sbTgt = new StringBuilder();
        preTraverse(t, sbTgt);
        return sbSrc.indexOf(sbTgt.toString()) != -1;
    }

    void preTraverse(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("N");
            return;
        }
        sb.append("X" + root.data + " ");
        preTraverse(root.left, sb);
        preTraverse(root.right, sb);
    }
}