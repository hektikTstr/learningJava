import java.util.concurrent.ThreadLocalRandom;

public class GetNthToLast {
    protected static class Node {
        Node next;
        int data;
    }

    public static Node generateRandomSingleLinkedList(int count) {
        if (count <= 0) {
            return null;
        }
        Node head = new Node();
        head.data = ThreadLocalRandom.current().nextInt(20);
        head.next = null;
        Node n = head;
        while (--count > 0) {
            n.next = new Node();
            n = n.next;
            n.data = ThreadLocalRandom.current().nextInt(20);
        }
        return head;
    }

    public static void printLinkedList(Node temp) {
        while (temp != null) {
            System.out.print(temp.data + ",");
            temp = temp.next;
        }
        System.out.println();
    }

    public static class Ret {
        boolean isFound;
        int value;
    }

    static int getNthToLastRecursive(Node node, int n, Ret ret) {
        if (node == null) {
            return n;
        }
        int index = getNthToLastRecursive(node.next, n, ret);
        if (index == 0 && !ret.isFound) {
            ret.value = node.data;
            ret.isFound = true;
        }
        if (!ret.isFound) {
            index = index - 1;
        }
        return index;
    }

    static Node getNthToLastIterative(Node node, int nTh) {
        Node front = node, back = node;
        while (nTh > 0 && front != null) {
            front = front.next;
            nTh--;
        }
        System.out.println("nTh = " + nTh);
        if (nTh != 0 || front == null) {
            return null;
        }
        while (front.next != null) {
            front = front.next;
            back = back.next;
        }
        return back;
    }

    public static void main(String[] args) {
        Node node = generateRandomSingleLinkedList(20);
        printLinkedList(node);
        System.out.println(getNthToLastIterative(node, 19).data);
        System.out.println(getNthToLastIterative(node, 0).data);
        System.out.println(getNthToLastIterative(node, 5).data);
        System.out.println(getNthToLastIterative(node, 20));
        System.out.println(getNthToLastIterative(node, -1));
        Ret a = new Ret();
        getNthToLastRecursive(node, 19, a);
        if (a.isFound) {
            System.out.println(a.value);
        } else {
            System.out.println("not found");
        }
        a.isFound = false;
        getNthToLastRecursive(node, 0, a);
        if (a.isFound) {
            System.out.println(a.value);
        } else {
            System.out.println("not found");
        }
        a.isFound = false;
        getNthToLastRecursive(node, 5, a);
        if (a.isFound) {
            System.out.println(a.value);
        } else {
            System.out.println("not found");
        }
        a.isFound = false;
        getNthToLastRecursive(node, 20, a);
        if (a.isFound) {
            System.out.println(a.value);
        } else {
            System.out.println("not found");
        }
        a.isFound = false;
        getNthToLastRecursive(node, -1, a);
        if (a.isFound) {
            System.out.println(a.value);
        } else {
            System.out.println("not found");
        }
    }
}
