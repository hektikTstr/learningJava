public class Palindrome {
    public static class Node {
        int value;
        Node next;
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public static Node reverseLinkedList(Node node) {
        Node head = null;
        Node cur = null;
        while (node != null) {
            head = new Node(node.value, head);
            node = node.next;
        }
        return head;
    }

    static void printLinkedList(Node node) {
        Node temp = node;
        while (temp != null) {
            System.out.print(temp.value + ",");
            temp = temp.next;
        }
        System.out.println();
    }

    public static Node createListFromArr(int[] arr) {
        Node head = null;
        Node cur = null;
        for (int elem : arr) {
            if (head == null) {
                head = new Node(elem, null);
                cur = head;
            } else {
                cur.next = new Node(elem, null);
                cur = cur.next;
            }
        }
        return head;
    }

    public static boolean check(Node node) {
        Node reversed = reverseLinkedList(node);
        while (node != null && reversed != null) {
            if (node.value != reversed.value) {
                return false;
            }
            node = node.next;
            reversed = reversed.next;
        }
        return node == null && reversed == null;
    }

    static class Result {
        Node node = null;
        boolean isPalindrome = false;
        public Result(Node node, boolean isPalindrome) {
            this.node = node;
            this.isPalindrome = isPalindrome;
        }
    }
    public static Result checkRecursive(Node node, int len) {
        if (len == 0) {
            return new Result(node, true);
        }
        if (len == 1) {
            return new Result(node.next, true);
        }
        Result ret = checkRecursive(node.next, len - 2);
        if (ret.isPalindrome) {
            if (node.value == ret.node.value) {
                ret.node = ret.node.next;
            } else {
                ret.isPalindrome = false;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,1,1};
        Node list = createListFromArr(arr);
        printLinkedList(list);
        printLinkedList(reverseLinkedList(list));
        System.out.println(check(list));
        System.out.println(checkRecursive(list, 5).isPalindrome);
    }
}