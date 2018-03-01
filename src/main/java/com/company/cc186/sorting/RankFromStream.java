public class RankFromStream {
    public static class RankNode {
        public int leftSize = 0;
        public int value = 0;
        public RankNode left;
        public RankNode right;

        public RankNode(int value) {
            this.value = value;
        }

        public void insert(int d) {
            RankNode node = new RankNode(d);
            if (d <= this.value) {
                if (this.left != null) {
                    this.left.insert(d);
                } else {
                    this.left = node;
                }
                leftSize++;
            } else {
                if (this.right != null) {
                    this.right.insert(d);
                } else {
                    this.right = node;
                }
            }
        }

        public int getRank(int d) {
            if (d == this.value) {
                return leftSize;
            } else if (d < this.value) {
                if (this.left != null) {
                    return this.left.getRank(d);
                } else {
                    return -1;
                }
            } else {
                if (this.right != null) {
                    int rightRank = this.right.getRank(d);
                    if (rightRank == -1) {
                        return -1;
                    } else {
                        return this.leftSize + 1 + rightRank;
                    }
                } else {
                    return -1;
                }
            }
        }
    }

    public static RankNode root;

    public static void track(int number) {
        if (root == null) {
            root = new RankNode(number);
        } else {
            root.insert(number);
        }
    }

    public static int getRankOfNumber(int number) {
        return root.getRank(number);
    }

    public static void main(String[] args) {
        track(21);
        track(20);
        track(15);
        track(25);
        track(13);
        track(19);
        track(17);
        track(17);
        track(25);
        System.out.println(getRankOfNumber(12));
        System.out.println(getRankOfNumber(13));
        System.out.println(getRankOfNumber(15));
        System.out.println(getRankOfNumber(17));
        System.out.println(getRankOfNumber(19));
        System.out.println(getRankOfNumber(20));
        System.out.println(getRankOfNumber(21));
        System.out.println(getRankOfNumber(25));
    }
}