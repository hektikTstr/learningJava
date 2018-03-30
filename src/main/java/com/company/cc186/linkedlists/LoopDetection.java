import java.util.HashMap;
import java.util.Map;

public class LoopDetection {

    public static class Node {
        int value;
        Node next;
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public static Node createListFromArr(int[] arr, int index) {
        if (index > arr.length - 1 || index < 0) {
            return null;
        }
        Node head = null;
        Node cur = null;
        int counter = 0;
        Node nodeOfIndex = null;
        for (int elem : arr) {
            if (head == null) {
                head = new Node(elem, null);
                cur = head;
            } else {
                cur.next = new Node(elem, null);
                cur = cur.next;
            }
            if (counter++ == index) {
                nodeOfIndex = cur;
            }
        }
        cur.next = nodeOfIndex;
        return head;
    }

    static void printLinkedList(Node node, int upperLimit) {
        Node temp = node;
        int index = 0;
        while (temp != null && index++ < upperLimit) {
            System.out.print(temp.value + ",");
            temp = temp.next;
        }
        System.out.println();
    }

    static Node detectLoop(Node node) {
        Map<Node, Integer> nodeMap = new HashMap<>();
        Node temp = node;
        int index = 0;
        while (temp != null) {
            if (nodeMap.containsKey(temp)) {
                return temp;
            } else {
                nodeMap.put(temp, 1);
            }
            temp = temp.next;
        }
        return null;
    }

    static Node detectLoopFastSlowNode(Node node) {
       Node slowNode = node, fastNode = node;
       do {
           slowNode = slowNode.next;
           fastNode = fastNode.next.next;
       } while (fastNode != slowNode);
       fastNode = node;
       while (slowNode != fastNode) {
           fastNode = fastNode.next;
           slowNode = slowNode.next;
       }
       return slowNode;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        Node list = createListFromArr(arr, 2);
        printLinkedList(list, 8);
        System.out.println(detectLoop(list).value);
        System.out.println(detectLoopFastSlowNode(list).value);
    }
}