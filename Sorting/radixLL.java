package Sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import static java.util.Arrays.*;
import static java.util.Collections.min;

public class Main {
    public static void main(String[] args) {
        int[] arr = {421, 240, 35, 532, 305, 430, 124};
        radixLL(arr);
        System.out.println(Arrays.toString(arr));

    }

    private static int maxValue(int[] array) {
        int res = Integer.MIN_VALUE;
        for (int num: array)
            if (num > res)
                res = num;
        return res;
    }



    public static void radixLL(int[] array) {
        LinkedList<Integer>[] sub = new LinkedList[10];
        int maxval = maxValue(array);
        // iterate number of digit times-> thats why we calculated the max
        for (int i = 1; maxval / i > 0; i *= 10) {

            for (int j = 0; j < array.length; j++) {
                if (sub[(array[j] / i) % 10] == null)
                    sub[(array[j] / i) % 10] = new LinkedList<Integer>();

                sub[(array[j] / i) % 10].addLast(array[j]);
            }
            int location = 0;
            for (int j = 0; j < 10; j++) {
                if (sub[j] != null)
                    while (!sub[j].isEmpty())
                        array[location++] = sub[j].removeFirst();
            }
        }
    }

}
