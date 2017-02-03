package com.company.stack;

public class Exercise {
    //R-6.4
    public static <E> void transfer(Stack<E> orig, Stack<E> target) {
        E e = orig.pop();
        while (e != null) {
            target.push(e);
            e = orig.pop();
        }
    }

    //R-6.5
    public static <E> void removeAllElemInStack(Stack<E> stack) {
        if (stack.isEmpty()) {
            return;
        } else {
            stack.pop();
            removeAllElemInStack(stack);
        }
    }

    public static void main(String[] args) {
        Stack<Integer> orig = new ArrayStack<>();
        Stack<Integer> target = new ArrayStack<>();
        orig.push(5);
        orig.push(3);
        orig.push(7);
        orig.push(8);
        transfer(orig, target);
        removeAllElemInStack(target);
    }
}
