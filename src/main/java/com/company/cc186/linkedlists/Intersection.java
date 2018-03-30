public class Intersection {

    public static class Node {
        int value;
        Node next;
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    static Node getIntersection(Node list1, Node list2) {

    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        Node list = createListFromArr(arr);
        printLinkedList(list);
        printLinkedList(reverse(list));
    }

    static Node reverse(Node head) {
        Node node = head.next;
        head.next = null;
        Node prev = head;
        Node next = null;
        while (node != null) {
            next = node.next;
            node.next = prev;
            prev = node;
            node = next;
            if (next != null) {
                next = next.next;
            }
        }
        return prev;
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

}