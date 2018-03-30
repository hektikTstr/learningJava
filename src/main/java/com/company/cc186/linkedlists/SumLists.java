public class SumLists {
    public static class Node {
        int value;
        Node next;
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
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

    static void printLinkedList(Node node) {
        Node temp = node;
        while (temp != null) {
            System.out.print(temp.value + ",");
            temp = temp.next;
        }
        System.out.println();
    }

    public static Node sumLists(Node list1Head, Node list2Head) {
        int carry = 0;
        Node cur1 = list1Head;
        Node cur2 = list2Head;
        Node retHead = null;
        Node curRetHead = null;
        while (list1Head != null && list2Head != null) {
            int sum = list1Head.value + list2Head.value + carry;
            System.out.println("sum = " + sum);
            if (retHead == null) {
                retHead = new Node(sum % 10, null);
                curRetHead = retHead;
            } else {
                curRetHead.next = new Node(sum % 10, null);
                curRetHead = curRetHead.next;
            }
            carry = sum / 10;
            list1Head = list1Head.next;
            list2Head = list2Head.next;
        }

        while (list1Head != null) {
            int sum = list1Head.value + carry;
            if (retHead == null) {
                retHead = new Node(sum % 10, null);
                curRetHead = retHead;
            } else {
                curRetHead.next = new Node(sum % 10, null);
                curRetHead = curRetHead.next;
            }
            carry = sum / 10;
            list1Head = list1Head.next;
        }

        while (list2Head != null) {
            int sum = list2Head.value + carry;
            if (retHead == null) {
                retHead = new Node(sum % 10, null);
                curRetHead = retHead;
            } else {
                curRetHead.next = new Node(sum % 10, null);
                curRetHead = curRetHead.next;
            }
            carry = sum / 10;
            list2Head = list2Head.next;
        }

        if (carry == 1) {
            curRetHead.next = new Node(1, null);
        }
        return retHead;
    }

    static int length(Node head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    public static Node reverseSum(Node list1Head, Node list2Head) {
        // padding the shorter list
        int len1 = length(list1Head);
        int len2 = length(list2Head);
        int delta = len1 - len2;
        if (delta != 0) {
            Node padList = delta < 0 ? list1Head : list2Head;
            int deltaAbs = Math.abs(delta);
            while (deltaAbs-- > 0) {
                padList = new Node(0, padList);
            }
            if (delta < 0) {
                list1Head = padList;
            } else {
                list2Head = padList;
            }
        }
        printLinkedList(list1Head);
        printLinkedList(list2Head);

        Result ret = sumRecursive(list1Head, list2Head);
        Node head = null;
        if (ret.carry == 1) {
            head = new Node(1, ret.node);
        } else {
            head = ret.node;
        }
        return head;
    }

    static class Result {
        int carry = 0;
        Node node = null;

        public Result(int carry, Node node) {
            this.carry = carry;
            this.node = node;
        }
    }

    static Result sumRecursive(Node list1, Node list2) {
        if (list1 == null && list2 == null) {
            return new Result(0, null);
        }
        Result ret = sumRecursive(list1.next, list2.next);
        int sum = list1.value + list2.value + ret.carry;
        int carry = sum / 10;
        sum = sum % 10;
        Node curNode = new Node(sum, ret.node);
        return new Result(carry, curNode);
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 1, 6, 9, 9};
        int[] arr2 = {9, 5, 9, 3, 9};
        Node list1 = createListFromArr(arr1);
        Node list2 = createListFromArr(arr2);
        printLinkedList(list1);
        printLinkedList(list2);
        // printLinkedList(sumLists(list1, list2));
        printLinkedList(reverseSum(list1, list2));


    }
}