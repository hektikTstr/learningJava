package com.company.cc186.linkedlists;

import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

//import java.util.Random
public class RemoveDuplicates {
    public static class Node {
        int data;
        Node next;
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

    @Test
    public void test() {
        Node node = generateRandomSingleLinkedList(20);
        Node temp = node;
        printLinkedList(temp);
        temp = node;
        removeDuplicates(node);
        printLinkedList(temp);
    }

    public static void printLinkedList(Node temp) {
        while (temp != null) {
            System.out.print(temp.data + ",");
            temp = temp.next;
        }
        System.out.println();
    }

    void removeDuplicates(Node head) {
        Node first, second = head;
        while (second != null) {
            first = second.next;
            Node prev = second;
            while (first != null) {
                if (first.data == second.data) {
                    prev.next = first.next;
                    first = first.next;
                } else {
                    prev = first;
                    first = first.next;
                }
            }
            second = second.next;
        }
    }
}
