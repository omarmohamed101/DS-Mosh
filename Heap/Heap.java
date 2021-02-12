package Heap;

public class Heap {
    private int[] num;
    private int size;

    public Heap(int size) {
        num = new int[size];
        this.size = 0;
    }

    public boolean isFull() {
        return size == num.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(int item) {
        if (isFull())
            throw new IllegalStateException();

        num[size++] = item;
        bubbleup(size - 1);
    }
    private void bubbleup(int index) {
        int temp = num[index];
        int parent = (index - 1) / 2;
        while(index > 0 && num[parent] < temp) {
            num[index] = num[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        num[index] = temp;
    }

    public int remove() {
        if (isEmpty())
            throw new IllegalStateException();
        int ret = num[0];
        num[0] = num[--size];
        bubbledown();
        return ret;
    }
    public void bubbledown() {

        int largerChild;
        int index = 0;
        int top = num[index];

        // There is at least one child
        while(index < size / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;

            // if there is a right child then there is a left one
            if (rightChild < size && num[rightChild] > num[leftChild])
                largerChild = rightChild;
            else
                largerChild = leftChild;

            if (num[largerChild] <= top)
                break;

            num[index] = num[largerChild];
            index = largerChild;
        }
        num[index] = top;



    }

    public static void heapSort(int[] nums) {
        Heap heap = new Heap(nums.length);
        for (int item: nums)
            heap.insert(item);
        for (int i = nums.length - 1; i >= 0; i--)
            nums[i] = heap.remove();
    }

    public static void heapify(int[] array) {
        for(int i = array.length / 2 - 1; i >= 0; i--) {
            heapify(array, i);
        }
    }
    private static void heapify(int[] array, int index) {
        int largerIndex = index;

        int leftIndex = index * 2 + 1;
        if (leftIndex < array.length && array[leftIndex] > array[largerIndex])
            largerIndex = leftIndex;

        int rightIndex = leftIndex + 1;
        if (rightIndex < array.length && array[rightIndex] > array[largerIndex])
            largerIndex = rightIndex;

        if (index == largerIndex)
            return;

        swap(array, index, largerIndex);
        heapify(array, largerIndex);

    }

    private static void swap(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
}
