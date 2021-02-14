package Sorting;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {0, 5, 6, 1, 4, 8, 0, 10, 2, 10};
        countSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void countSort(int[] array) {
        int range = maxValue(array);
        int[] freq = new int[range + 1];
        for (int num: array)
            freq[num]++;
        int current = 0;
        for (int i = 0; i < freq.length; i++) {
            for (int j = 0; j < freq[i]; j++)
                array[current++] = i;
        }

    }

    private static int maxValue(int[] array) {
        int res = Integer.MIN_VALUE;
        for (int num: array)
            if (num > res)
                res = num;
        return res;
    }
}
