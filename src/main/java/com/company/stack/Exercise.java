
package com.company.stack;

import com.company.deque.Deque;
import com.company.deque.LinkedDeque;
import com.company.queue.*;

import java.util.ArrayList;
import java.util.Arrays;

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

    //R-6.10
    public class StackAdapter<E> implements Stack<E> {
        private Deque<E> deque = null;

        public StackAdapter() {
            deque = new LinkedDeque<>();
        }

        public StackAdapter(Deque<E> deque) {
            this.deque = deque;
        }

        @Override
        public int size() {
            return deque.size();
        }

        @Override
        public boolean isEmpty() {
            return deque.isEmpty();
        }

        @Override
        public void push(E e) {
            deque.addFirst(e);
        }

        @Override
        public E top() {
            return deque.first();
        }

        @Override
        public E pop() {
            return deque.removeFirst();
        }
    }

    //R-6.11
    public class QueueAdapter<E> implements Queue<E> {
        private Deque<E> deque = null;

        public QueueAdapter() {
            deque = new LinkedDeque<>();
        }

        public QueueAdapter(Deque<E> deque) {
            this.deque = deque;
        }

        @Override
        public int size() {
            return deque.size();
        }

        @Override
        public boolean isEmpty() {
            return deque.isEmpty();
        }

        @Override
        public void enqueue(E e) {
            deque.addLast(e);
        }

        @Override
        public E first() {
            return deque.first();
        }

        @Override
        public E dequeue() {
            return deque.removeFirst();
        }
    }

    //R-6.13
    public static <E> void transfer2(Deque<E> orig, Queue<E> dest) {
        E e = orig.removeFirst();
        while (e != null) {
            dest.enqueue(e);
            e = orig.removeFirst();
        }
    }

    //R-6.14
    public static <E> void transfer3(Deque<E> orig, Stack<E> dest) {
        E e = orig.removeLast();
        while (e != null) {
            dest.push(e);
            e = orig.removeLast();
        }
    }

    // C-6.19
    public static int postfixNotationCalculation(String expression) {
        char[] charArr = expression.toCharArray();
        Stack<Integer> stack = new LinkedStack<>();
        for (Character c : charArr) {
            if (Character.isDigit(c)) {
                stack.push(Character.getNumericValue(c));
            } else {
                int y = stack.pop();
                int x = stack.pop();
                int result = 0;
                switch (c) {
                    case '*':
                        result = x * y;
                        break;
                    case '/':
                        result = x / y;
                        break;
                    case '+':
                        result = x + y;
                        break;
                    case '-':
                        result = x - y;
                        break;
                    default:
                        throw new UnsupportedOperationException();
                }
                stack.push(result);
            }
        }
        return stack.pop();
    }

    public static void hanoiTower(int n, Stack<Integer> start, Stack<Integer> aux, Stack<Integer> end) {
        if (n == 1) {
            end.push(start.pop());
        } else {
            hanoiTower(n - 1, start, end, aux);
            hanoiTower(1, start, aux, end);
            hanoiTower(n - 1, aux, start, end);
        }
    }

    // C-6.21
    public static void enumeratePermutation(ArrayList<Integer> list,  ArrayList<Integer> temp) {
        for (int i = 0; i < list.size(); i++) {
            Integer j = list.remove(i);
            temp.add(j);
            if (list.size() == 0) {
                System.out.println(Arrays.toString(temp.toArray()));
            } else {
                enumeratePermutation(list, temp);
            }
            list.add(i, temp.remove(temp.indexOf(j)));
        }
    }

    public static void enumeratePermutation(int n, ArrayList<Integer> list,  ArrayList<Integer> temp) {
        for (int i = 0; i < list.size(); i++) {
            Integer j = list.remove(i);
            temp.add(j);
            if (n == 1) {
                System.out.println(Arrays.toString(temp.toArray()));
            } else {
                enumeratePermutation(n - 1, list, temp);
            }
            list.add(i, temp.remove(temp.indexOf(j)));
        }
    }

    public static void enumerateCombination(int n, int startPos, int[] arr,  StringBuffer outStr) {
        if (n == 0) {
            System.out.println(outStr);
        } else {
            for (int i = startPos; i < arr.length; i++) {
                outStr.append(arr[i]);
                enumerateCombination(n - 1, i + 1, arr, outStr);
                outStr.deleteCharAt(outStr.length() - 1);
            }
        }
    }

    public static void combine(String instr, StringBuffer outstr, int index)
    {
        for (int i = index; i < instr.length(); i++)
        {
            outstr.append(instr.charAt(i));
            System.out.println(outstr);
            combine(instr, outstr, i + 1);
            outstr.deleteCharAt(outstr.length() - 1);
        }
    }

    public static void combinations2(String[] arr, int len, int startPosition, String[] result){
        if (len == 0){
            System.out.println(Arrays.toString(result));
            return;
        }
        for (int i = startPosition; i <= arr.length-len; i++){
            result[result.length - len] = arr[i];
            combinations2(arr, len-1, i+1, result);
        }
    }
    // C-6.21
    public static Queue<ArrayList<Integer>> enumeratePermutationWithStack(Stack<Integer> intStack) {
        Queue<ArrayList<Integer>> arrListQueue = new ArrayQueue<>();
        int intStackSize = intStack.size();
        for (int i = 0; i < intStackSize; i++) {
            Integer cur = intStack.pop();
            int arrListQueueSize = arrListQueue.size();
            if (arrListQueueSize == 0) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(cur);
                arrListQueue.enqueue(list);
            } else {
                for (int j = 0; j < arrListQueueSize; j++) {
                    ArrayList<Integer> origList = arrListQueue.dequeue();
                    for (int k = 0; k <= origList.size(); k++) {
                        ArrayList<Integer> tempList = new ArrayList<>(origList);
                        tempList.add(k, cur);
                        arrListQueue.enqueue(tempList);
                    }
                }
            }
        }
        return arrListQueue;
    }

    public static void main(String[] args) {
//        Stack<Integer> orig = new Exercise().new StackAdapter<>();
//        Stack<Integer> target = new ArrayStack<>();
//        Deque<Integer> deque = new ArrayDeque<>();
//        deque.addLast(1);
//        deque.addLast(2);
//        deque.addLast(3);
//        deque.addLast(4);
//        deque.addLast(5);
//        Stack<Integer> stack = new LinkedStack<>();
//        transfer3(deque, stack);
//        orig.push(5);
//        orig.push(3);
//        orig.push(7);
//        orig.push(8);
//        transfer(orig, target);
//        removeAllElemInStack(target);
//        //C-6.17
//        Stack<Integer> temp1 = new ArrayStack<>();
//        Stack<Integer> temp2 = new ArrayStack<>();
//        Stack<Integer> orig = new ArrayStack<>();
//        orig.push(5);
//        orig.push(3);
//        orig.push(7);
//        orig.push(8);
//        transfer(orig, temp1);
//        transfer(temp1, temp2);
//        transfer(temp2, orig);

//        System.out.println(postfixNotationCalculation("52+83-*4/"));
//        Stack<Integer> start = new ArrayStack<>(3);
//        Stack<Integer> end = new ArrayStack<>(3);
//        Stack<Integer> aux = new ArrayStack<>(3);
//        start.push(3);
//        start.push(2);
//        start.push(1);
//        hanoiTower(3, start, aux, end);
//        System.out.println(Integer.toString(98));
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        ArrayList<Integer> tempList = new ArrayList<>();
//        arrayList.add(1);
//        arrayList.add(2);
//        arrayList.add(3);
//        arrayList.add(4);
//        enumeratePermutation(arrayList, tempList);
//        Stack<Integer> intStack = new ArrayStack<>();
//        intStack.push(4);
//        intStack.push(3);
//        intStack.push(2);
//        intStack.push(1);
//        enumeratePermutationWithStack(intStack);
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        ArrayList<Integer> list2 = new ArrayList<>(list);

//        ArrayList<Integer> arrayList = new ArrayList<>();
//        ArrayList<Integer> tempList = new ArrayList<>();
//        arrayList.add(1);
//        arrayList.add(2);
//        arrayList.add(3);
//        arrayList.add(4);
////        enumeratePermutation(4, arrayList, tempList);
        int a[] = {1, 2, 3, 4, 5, 6};
        enumerateCombination(2, 1, a, new StringBuffer());
//        String[] arr = {"1","2","3","4"};
//        combinations2(arr, 2, 0, new String[2]);
//        StringBuffer a = new StringBuffer();
//        combine("1234", a, 0);
    }
}
