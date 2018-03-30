import java.util.LinkedList;

public class Patition {
    public static class Node {
        int value;
        Node next;
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    static void swapValue(Node n1, Node n2) {
        int temp = n1.value;
        n1.value = n2.value;
        n2.value = temp;
    }

    public static void patition(Node head, int number) {
        Node cur = head;
        Node firstBig = null;
        while (cur != null) {
            if (cur.value < number) {
                if (firstBig != null) {
                    swapValue(firstBig, cur);
                    firstBig = firstBig.next;
                }
            } else {
                if (firstBig == null) {
                    firstBig = cur;
                }
            }
            cur = cur.next;
            printLinkedList(head);
        }
    }

    static void printLinkedList(Node node) {
        Node temp = node;
        while (temp != null) {
            System.out.print(temp.value + ",");
            temp = temp.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Node head = new Node(3, null);
        head.next = new Node(0, null);
        head.next.next = new Node(1, null);
        head.next.next.next = new Node(2, null);
        head.next.next.next.next = new Node(5, null);
        head.next.next.next.next.next = new Node(0, null);
        head.next.next.next.next.next.next = new Node(4, null);
        printLinkedList(head);
        patition(head, 2);
        printLinkedList(head);
    }
}