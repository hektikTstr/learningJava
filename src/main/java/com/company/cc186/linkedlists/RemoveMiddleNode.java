
public class RemoveMiddleNode {
    public static void main(String[] args) {
        GetNthToLast.Node node = GetNthToLast.generateRandomSingleLinkedList(20);
        GetNthToLast.printLinkedList(node);

        GetNthToLast.Node node5 = node.next.next.next.next.next;
        removeMiddle(node5);
        GetNthToLast.printLinkedList(node);

        GetNthToLast.Node node1 = node.next;
        removeMiddle(node1);
        GetNthToLast.printLinkedList(node);

        GetNthToLast.Node node4 = node.next.next.next.next;
        removeMiddle(node4);
        GetNthToLast.printLinkedList(node);

        GetNthToLast.Node node2 = node.next.next;
        removeMiddle(node2);
        GetNthToLast.printLinkedList(node);

        GetNthToLast.Node node3 = node.next.next.next;
        removeMiddle(node3);
        GetNthToLast.printLinkedList(node);
    }

    static void removeMiddle(GetNthToLast.Node node) {
        if (node == null || node.next == null) {
            return;
        }
        node.data = node.next.data;
        node.next = node.next.next;
    }
}
