
public class MultiStack<E> {
    private E[] arr = null;
    private int stackNum = 3;
    private int[] capacities = null;
    private int[] offsets = null;
    private int[] sizes = null;
    private int arrLength = 20;

    public MultiStack() {
        arr = (E[]) new Object[arrLength];
        capacities = new int[stackNum];
        offsets = new int[stackNum];
        int lastCapacities = arrLength;
        offsets[0] = 0;
        for (int i = 0; i < stackNum - 1; i++) {
            capacities[i] = arrLength / stackNum;
            lastCapacities -= capacities[i];
            offsets[i + 1] = offsets[i] + capacities[i];
        }
        capacities[stackNum - 1] = lastCapacities;
        sizes = new int[stackNum];
    }

    public int size(int stackIndex) {
        return sizes[stackIndex];
    }

    public boolean isEmpty(int stackIndex) {
        return sizes[stackIndex] == 0;
    }

    public E top(int stackIndex) {
        if (isEmpty(stackIndex)) {
            return null;
        }
        return arr[offsets[stackIndex] + sizes[stackIndex] - 1];
    }

    private boolean isFull(int stackIndex) {
        return size(stackIndex) == capacities[stackIndex];
    }

    public void push(E value, int stackIndex) {
        if (isFull(stackIndex)) {
            throw new IllegalStateException(String.format("Stack %d is full.", stackIndex));
        }
        arr[offsets[stackIndex] + size(stackIndex)] = value;
        sizes[stackIndex]++;
    }

    public E remove(int stackIndex) {
        if (isEmpty(stackIndex)) {
            return null;
        }
        E ret = arr[offsets[stackIndex] + sizes[stackIndex] - 1];
        sizes[stackIndex]--;
        return ret;
    }

    public static void main(String args[]) {
        MultiStack<Integer> multiStack = new MultiStack<>();
        System.out.println("multiStack 0 size = " + multiStack.size(0));
        System.out.println("multiStack 0 isEmpty = " + multiStack.isEmpty(0));
        System.out.println("multiStack 0 top = " + multiStack.top(0));
        multiStack.push(10, 0);
        System.out.println("multiStack.push(10, 0)");
        multiStack.push(9, 0);
        System.out.println("multiStack.push(9, 0)");
        multiStack.push(8, 0);
        System.out.println("multiStack.push(8, 0)");
        multiStack.push(7, 0);
        System.out.println("multiStack.push(7, 0)");
        multiStack.push(6, 0);
        System.out.println("multiStack.push(6, 0)");
        multiStack.push(5, 0);
        System.out.println("multiStack.push(5, 0)");
        System.out.println("multiStack 0 pop = " + multiStack.remove(0));
        System.out.println("multiStack 0 pop = " + multiStack.remove(0));
        System.out.println("multiStack 0 pop = " + multiStack.remove(0));
        System.out.println("multiStack 0 pop = " + multiStack.remove(0));
        System.out.println("multiStack 0 pop = " + multiStack.remove(0));
        //System.out.println("multiStack 0 pop = " + multiStack.remove(0));
        //System.out.println("multiStack 0 pop = " + multiStack.remove(0));
        //System.out.println("multiStack 0 pop = " + multiStack.remove(0));
        System.out.println("multiStack 0 size = " + multiStack.size(0));
        System.out.println("multiStack 0 top = " + multiStack.top(0));

        System.out.println("multiStack 1 size = " + multiStack.size(1));
        System.out.println("multiStack 1 isEmpty = " + multiStack.isEmpty(1));
        System.out.println("multiStack 1 top = " + multiStack.top(1));
        multiStack.push(20, 1);
        System.out.println("multiStack.push(20, 1)");
        multiStack.push(19, 1);
        System.out.println("multiStack.push(19, 1)");
        multiStack.push(18, 1);
        System.out.println("multiStack.push(18, 1)");
        multiStack.push(17, 1);
        System.out.println("multiStack.push(17, 1)");
        multiStack.push(16, 1);
        System.out.println("multiStack.push(16, 1)");
        multiStack.push(15, 1);
        System.out.println("multiStack.push(15, 1)");
        System.out.println("multiStack 1 size = " + multiStack.size(1));
        System.out.println("multiStack 1 isEmpty = " + multiStack.isEmpty(1));
        System.out.println("multiStack 1 top = " + multiStack.top(1));
        System.out.println("multiStack 2 size = " + multiStack.size(2));
        System.out.println("multiStack 2 isEmpty = " + multiStack.isEmpty(2));
        System.out.println("multiStack 2 top = " + multiStack.top(2));
        multiStack.push(30, 2);
        multiStack.push(29, 2);
        multiStack.push(28, 2);
        multiStack.push(27, 2);
        multiStack.push(26, 2);
        multiStack.push(25, 2);
        multiStack.push(24, 2);
        multiStack.push(23, 2);
        System.out.println("multiStack 2 size = " + multiStack.size(2));
        System.out.println("multiStack 2 isEmpty = " + multiStack.isEmpty(2));
        System.out.println("multiStack 2 top = " + multiStack.top(2));
        System.out.println("multiStack 1 pop = " + multiStack.remove(1));
        System.out.println("multiStack 1 pop = " + multiStack.remove(1));
        System.out.println("multiStack 1 pop = " + multiStack.remove(1));
        System.out.println("multiStack 1 pop = " + multiStack.remove(1));
        System.out.println("multiStack 0 pop = " + multiStack.remove(0));
        System.out.println("multiStack 2 pop = " + multiStack.remove(2));
        System.out.println("multiStack 2 pop = " + multiStack.remove(2));
        System.out.println("multiStack 2 size = " + multiStack.size(2));
        System.out.println("multiStack 1 size = " + multiStack.size(1));
    }
}

