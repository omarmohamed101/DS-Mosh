package Queuee;

import java.util.Arrays;

public class priorityQueue {
    private int[] nums;
    private int maxSize;
    private int count;

    public priorityQueue(int size) {
        maxSize = size;
        nums = new int[size];
        count = 0;
    }

    public boolean isFull() {
        return count == maxSize;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int peek() {
        if (isEmpty())
            throw new IllegalStateException();
        return nums[count - 1];
    }

    public int remove() {
        if (isEmpty())
            throw new IllegalStateException();
        return nums[--count];
    }

    public void insert(int item) {
        if (isFull())
            throw new IllegalStateException();
        
        int j;
        if (count == 0)
            nums[count++] = item;
        else {
            for (j = count - 1; j >= 0; j--) {
                if (item > nums[j])
                    nums[j + 1] = nums[j];
                else
                    break;
            }
            nums[j+1] = item;
            count++;
        }
    }
    public void display(){
        System.out.println(Arrays.toString(nums));
    }
}
