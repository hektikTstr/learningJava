package com.company.stack;

import com.company.fundamental.linkList.SinglyLinkedList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LinkedStack<E> implements Stack<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();
    public LinkedStack() {}

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E top() {
        return list.first();
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    public static boolean isMatched(String expression) {
        final String opening = "([{";
        final String closing = ")]}";
        Stack<Character> buffer = new LinkedStack<>();
        for (char c : expression.toCharArray()) {
            if (opening.indexOf(c) != -1) {
                buffer.push(c);
            } else if (closing.indexOf(c) != -1) {
                if (buffer.isEmpty() || closing.indexOf(c) != opening.indexOf(buffer.pop())) {
                    return false;
                }
            }
        }
        return buffer.isEmpty();
    }

    public static boolean isHTMLMatched(String html) {
        Stack<String> buffer = new LinkedStack<>();
        int j = html.indexOf('<');
        while (j != -1) {
            int k = html.indexOf('>', j + 1);
            if (k == -1) {
                return false;
            }
            String tag = html.substring(j + 1, k);
            if (!tag.trim().endsWith("/")) {
                if (!tag.startsWith("/")) {
                    int index = tag.indexOf(' ');
                    if (index != -1) {
                        tag = tag.substring(0, index);
                    }
                    buffer.push(tag);
                } else {
                    if (buffer.isEmpty()) {
                        return false;
                    }
                    if (!tag.substring(1).equals(buffer.pop())) {
                        return false;
                    }
                }
            }
            j = html.indexOf('<', k + 1);
        }
        return buffer.isEmpty();
    }

    public static void main(String args[]) {
//        Stack<Integer> stack = new LinkedStack<>();
//        stack.push(5);
//        stack.push(3);
//        System.out.println(stack.size());
//        System.out.println(stack.pop());
//        System.out.println(stack.isEmpty());
//        System.out.println(stack.pop());
//        System.out.println(stack.isEmpty());
//        System.out.println(stack.pop());
//        stack.push(7);
//        stack.push(9);
//        System.out.println(stack.top());
//        stack.push(4);
//        System.out.println(stack.size());
//        System.out.println(stack.pop());
//        stack.push(6);
//        stack.push(8);
//        System.out.println(stack.pop());
//        System.out.println(isMatched("(1){6][5]}23"));
        try {
            System.out.println(isHTMLMatched(new String(Files.readAllBytes(Paths.get(LinkedStack.class.getResource("/html.htm").getPath())))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
