    public static void countSort(int[] array) {
        int range = maxValue(array);
        int[] freq = new int[range + 1];
        for(int num: array)
            freq[num]++;

        int[] prefix = new int[array.length];
        int current = 0;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != 0) {
                prefix[current] += i;
                if (current + freq[i] >= prefix.length)
                    break;
                prefix[current + freq[i]] -= i;
                current++;
            }
        }
        for (int i = 1; i < prefix.length; i++)
            prefix[i] += prefix[i - 1];

        for (int i = 0; i < prefix.length; i++)
            array[i] = prefix[i];
    }


    private static int maxValue(int[] array) {
        int res = Integer.MIN_VALUE;
        for (int num: array)
            if (num > res)
                res = num;
        return res;
    }
