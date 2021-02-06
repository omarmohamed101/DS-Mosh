package Queuee;

import java.util.Stack;

public class StackQueue {

    private Stack<Integer> s1;      // used in enqueue
    private Stack<Integer> s2;      // used in dequeue

    public StackQueue() {
        s1 = new Stack<Integer>();
        s2 = new Stack<Integer>();
    }

    // O(1)
    public void enqueue(int item) {
        s1.push(item);
    }
    // O(n)
    public int dequeue() {
        if (isEmpty())
            throw new IllegalStateException();

        if (s2.empty())
            while (!s1.empty())
                s2.push(s1.pop());

        return s2.pop();
    }

    // O(n)
    public int peek() {
        if (isEmpty())
            throw new IllegalStateException();

        if (s2.empty())
            while (!s1.empty())
                s2.push(s1.pop());

        return s2.peek();
    }

    public boolean isEmpty() {
        return s1.empty() && s2.empty();
    }
}
