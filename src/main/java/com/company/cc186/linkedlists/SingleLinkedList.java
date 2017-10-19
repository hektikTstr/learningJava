//import GetNthToLast.*;

public class SingleLinkedList {
//    protected static class Node {
//        Node next;
//        int data;
//    }


    static GetNthToLast.Node deleteNode(GetNthToLast.Node head, int data) {
        if (head == null) {
            return null;
        }
        if (head.next.data == data) {
            return head.next;
        }
        GetNthToLast.Node node = head;
        while (node.next != null) {
            if (node.next.data == data) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        GetNthToLast.Node node = GetNthToLast.generateRandomSingleLinkedList(20);
        GetNthToLast.printLinkedList(node);
        node = deleteNode(node, 1);
        GetNthToLast.printLinkedList(node);
    }
}
