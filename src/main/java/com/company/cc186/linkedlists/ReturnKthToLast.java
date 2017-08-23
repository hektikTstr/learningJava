package com.company.cc186.linkedlists;

import com.company.cc186.linkedlists.RemoveDuplicates.Node;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReturnKthToLast {
    int getNodeCount(Node head) {
        Node n = head;
        int count = 0;
        while (n != null) {
            count++;
            n = n.next;
        }
        return count;
    }

    int returnNthToLast(Node head, int nTh) {
        if (nTh < 0) {
            throw new IllegalArgumentException("Negative nTh is not allowed");
        }
        int count = getNodeCount(head);
        if (count == 0) {
            throw  new IllegalArgumentException("Linked list is empty.");
        }
        int index = count - 1 - nTh;
        if (index < 0) {
            throw new IllegalArgumentException(nTh + " to last is out of bound.");
        }
        Node n = head;
        while (index-- > 0) {
            n = n.next;
        }
        return n.data;
    }

    static boolean isFound = false;
    int returnNthToLastRecursive(Node node, int nTh) {
        if (nTh < 0) {
            throw new IllegalArgumentException("Negative nTh is not allowed.");
        }
        if (node == null) {
            return nTh;
        }
        int ret = returnNthToLastRecursive(node.next, nTh);
        if (isFound) {
            return ret;
        }
        if (ret == 0) {
            isFound = true;
            return node.data;
        } else {
            return ret - 1;
        }
    }

    @Test
    public void testGetNodeCount() {
        final int COUNT = 20;
        Node node = RemoveDuplicates.generateRandomSingleLinkedList(COUNT);
        int count = getNodeCount(node);
        RemoveDuplicates.printLinkedList(node);
        Assert.assertEquals(count, COUNT);

        System.out.println(returnNthToLast(node, 0));
    }

    @Test
    public void testReturnNthToLastRecursive() {
        final int COUNT = 11;
        Node node = RemoveDuplicates.generateRandomSingleLinkedList(COUNT);
        int count = getNodeCount(node);
        RemoveDuplicates.printLinkedList(node);
        Assert.assertEquals(count, COUNT);

        System.out.println(returnNthToLastRecursive(node, 99));
    }
}
