
class LCA {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static class Result {
        TreeNode node;
        boolean isAncestor = false;
        public Result(TreeNode node, boolean isAncestor) {
            this.node = node;
            this.isAncestor = isAncestor;
        }
    }

    public Result lowestCommonAncestor_checkingNode(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        Result retL = lowestCommonAncestor_checkingNode(root.left, p, q);
        if (retL != null) {
            if (retL.isAncestor) {
                return retL;
            } else if (root == p || root == q) {
                return new Result(root, true);
            }
        }
        Result retR = lowestCommonAncestor_checkingNode(root.right, p, q);
        if (retR != null) {
            if (retR.isAncestor) {
                return retR;
            } else if (root == p || root == q) {
                return new Result(root, true);
            }
        }
        if (retL != null && retR != null) {
            return new Result(root, true);
        } else if (root == p || root == q) {
            return new Result(root, false);
        } else {
            return retL != null ? retL : retR;
        }
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        // if p equals q then return root itself
        if (root == p && root == q) return root;
        
        TreeNode x = lowestCommonAncestor(root.left, p, q);
        // if the return from left subtree is the ancestor, then return it;
        if (x != null && x != p && x != q)
            return x;
        TreeNode y = lowestCommonAncestor(root.right, p, q);
        // if the return from right subtree is the ancestor, then return it;
        if (y != null && y != p && y != q)
            return y;
        
        if (x != null && y != null) // p and q found in different subtrees
            return root;
        else if (root == p || root == q) 
            return root;
        else
            return x == null ? y : x;
    }

    public TreeNode lowestCommonAncestor_youtube(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        
        TreeNode retL = lowestCommonAncestor_youtube(root.left, p, q);
        if (retL != null && retL != p && retL != q) {
            return retL;
        }
        
        TreeNode retR = lowestCommonAncestor_youtube(root.right, p, q);
        if (retR != null && retR != p && retR != q) {
            return retR;
        }
        
        if (retL != null && retR != null) {
            return root;
        } else {
            return retL != null ? retL : retR;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
    }
}