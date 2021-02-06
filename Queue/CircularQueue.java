package Queuee;

import java.util.Arrays;

public class ArrayQueue {
    private int[] numbers;
    private int front;
    private int rear;
    private int count;

    public ArrayQueue(int size) {
        numbers = new int[size];
    }

    public boolean isEmpty() {
        return count == 0;
    }
    public boolean isFull() {
        return count == numbers.length;
    }
    public int peek() {
        if (isEmpty())
            throw new IllegalStateException();
        return numbers[front];
    }
    public void enqueue(int item) {
        if (isFull())
            throw new IllegalStateException();

        numbers[rear] = item;
        rear = (rear + 1) % numbers.length;
        count++;
    }
    public int dequeue() {
        if (isEmpty())
            throw new IllegalStateException();
        count--;
        int x = numbers[front];
        front = (front + 1) % numbers.length;
        return x;

    }
    @Override
    public String toString() {
        return Arrays.toString(numbers);
    }
}
  
