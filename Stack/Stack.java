package Stack;

import java.util.Arrays;

public class Stack {
    private int[] nums;
    private int currentSize;
    private int maxSize;
    public Stack(int size) {
        nums = new int[size];
        maxSize = size;
        currentSize = 0;
    }
    public boolean isFull() {
        return currentSize == maxSize;
    }
    public boolean isEmpty() {
        return currentSize == 0;
    }
    public void push(int item) {
        if (isFull())
            return;

        nums[currentSize++] = item;
    }
    public int peek() {
        if (isEmpty())
            return -1;

        return nums[currentSize - 1];
    }
    public int pop() {
        if (isEmpty())
            return -1;
        return nums[--currentSize];
    }
    @Override
    public String toString() {
        int[] content = Arrays.copyOfRange(nums, 0, currentSize);
        return Arrays.toString(content);
    }
}
