package com.company.cc186.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree {
    public static class BinaryTreeNode {
        public int data;
        public BinaryTreeNode left;
        public BinaryTreeNode right;
        public BinaryTreeNode parent;
    }

    public static BinaryTreeNode genLowestBST(int startIndex, int endIndex, int[] arr) {
        if (endIndex < startIndex) {
            return null;
        }

        BinaryTreeNode node = new BinaryTreeNode();
        int middle = (startIndex + endIndex) / 2;
        node.data = arr[middle];
        node.left = genLowestBST(startIndex, middle - 1, arr);
        node.right = genLowestBST(middle + 1, endIndex, arr);
        return node;
    }

    public static List<LinkedList<BinaryTreeNode>> genNodeOfDepth(BinaryTreeNode root) {
        List<LinkedList<BinaryTreeNode>> linkedLists = new ArrayList<>();
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        BinaryTreeNode nilNode = new BinaryTreeNode();
        queue.offer(root);
        queue.offer(nilNode);

        while (!queue.isEmpty()) {
            LinkedList<BinaryTreeNode> linkedList = new LinkedList<>();
            while (queue.peek() != nilNode) {
                BinaryTreeNode node = queue.remove();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                linkedList.add(node);
            }
            linkedLists.add(linkedList);
            queue.remove();
            if (!queue.isEmpty()) {
                queue.offer(nilNode);
            }
        }

        return linkedLists;
    }

    public static class RetIndicator {
        public int height;
        public boolean isBalanced;

        public RetIndicator(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    public static RetIndicator checkBalanced(BinaryTreeNode node) {
        if (node == null) {
            return new RetIndicator(-1, true);
        }
        RetIndicator leftResult = checkBalanced(node.left);
        System.out.println(node.data + " left height = " + leftResult.height);
        System.out.println(node.data + " left balanced = " + leftResult.isBalanced);
        if (!leftResult.isBalanced) {
            return leftResult;
        }
        RetIndicator rightRight = checkBalanced(node.right);
        System.out.println(node.data + " right height = " + rightRight.height);
        System.out.println(node.data + " right balanced = " + rightRight.isBalanced);
        if (!rightRight.isBalanced) {
            return rightRight;
        }
        int maxHeight = Math.max(leftResult.height, rightRight.height) + 1;
        return new RetIndicator(maxHeight, Math.abs(leftResult.height - rightRight.height) <= 1);
    }

    public static class RetIndicatorBST {
        public int data;
        public boolean isBST;
        public RetIndicatorBST(int data, boolean isBST) {
            this.data = data;
            this.isBST = isBST;
        }
    }

    // Wrong
    public static RetIndicatorBST checkBST(BinaryTreeNode node) {
        if (node == null) {
            return null;
        }
        RetIndicatorBST leftResult = checkBST(node.left);
        if (leftResult != null) {
            if (!leftResult.isBST) {
                return leftResult;
            }
            if (leftResult.data >= node.data) {
                return new RetIndicatorBST(node.data, false);
            }
        }
        RetIndicatorBST rightResult = checkBST(node.right);
        if (rightResult != null) {
            if (!rightResult.isBST) {
                return rightResult;
            }
            if (rightResult.data <= node.data) {
                return new RetIndicatorBST(node.data, false);
            }
        }
        return new RetIndicatorBST(node.data, true);
    }

    public static boolean checkBSTCorrect(BinaryTreeNode node, List<Integer> dataList) {
        if (node == null) {
            return true;
        }
        boolean retLeft = checkBSTCorrect(node.left, dataList);
        if (retLeft) {
            int beforeIndex = dataList.size() - 1;
            if (!dataList.isEmpty()) {
                int previousData = dataList.get(beforeIndex);
                if (previousData >= node.data) {
                    return false;
                }
            }
            dataList.add(node.data);
            beforeIndex += 2;
            boolean retRight = checkBSTCorrect(node.right, dataList);
            if (retRight) {
                if (beforeIndex < dataList.size()) {
                    int previousData = dataList.get(beforeIndex);
                    if (previousData <= node.data) {
                        return false;
                    }
                }
            } else {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public static BinaryTreeNode getBSTSuccessorPreorder(BinaryTreeNode node) {
        if (node.left != null) {
            return node.left;
        }
        if (node.right != null) {
            return node.right;
        }
        BinaryTreeNode parent = node.parent;
        while (parent != null) {
            if (node == parent.left) {
                if (parent.right != null) {
                    return parent.right;
                }
            }
            node = parent;
            parent = node.parent;
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {5,6,7,8,9,10,11,13,15};
        BinaryTreeNode root = genLowestBST(0, arr.length - 1, arr);

        BinaryTreeNode a = new BinaryTreeNode();
        a.data = 1;
        BinaryTreeNode b = new BinaryTreeNode();
        b.data = 2;
        BinaryTreeNode c = new BinaryTreeNode();
        c.data = 3;
        BinaryTreeNode d = new BinaryTreeNode();
        d.data = 4;
        BinaryTreeNode e = new BinaryTreeNode();
        e.data = 5;
        BinaryTreeNode f = new BinaryTreeNode();
        f.data = 6;
        BinaryTreeNode g = new BinaryTreeNode();
        g.data = 7;

        a.left = b;
        a.right = c;
        b.left = d;
        c.left = e;
        e.left = f;
        e.right = g;

        List<LinkedList<BinaryTreeNode>> linkedLists = genNodeOfDepth(a);
        Integer height = -1;
//        checkBalanced(f);
//        checkBalanced(g);
        checkBalanced(e);
        checkBalanced(c);
        System.out.println(checkBalanced(a).isBalanced);

        BinaryTreeNode A = new BinaryTreeNode();
        A.data = 3;
        BinaryTreeNode B = new BinaryTreeNode();
        B.data = 2;
        BinaryTreeNode C = new BinaryTreeNode();
        C.data = 7;
        BinaryTreeNode D = new BinaryTreeNode();
        D.data = 1;
        BinaryTreeNode E = new BinaryTreeNode();
        E.data = 5;
        BinaryTreeNode F = new BinaryTreeNode();
        F.data = 4;
        BinaryTreeNode G = new BinaryTreeNode();
        G.data = 6;

        A.left = B;
        A.right = C;
        B.left = D;
        B.parent = A;
        C.left = E;
        C.parent = A;
        E.left = F;
        E.right = G;
        E.parent = C;
        F.parent = E;
        G.parent = E;
        D.parent = B;

        List<Integer> list = new ArrayList<>();
        System.out.println("Is BST = " + checkBSTCorrect(A, list));
        System.out.println(getBSTSuccessorPreorder(F).data);
    }
}
