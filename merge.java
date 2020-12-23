    public static void mergeSort(int[] arr) {
        // single item or no items
        if (arr.length < 2) return;
        int mid = arr.length / 2;

        int[] left = new int[mid];
        for (int i = 0; i < mid; i++)
            left[i] = arr[i];

        int[] right = new int[arr.length - mid];
        for (int i = mid; i < arr.length; i++)
            right[i - mid] = arr[i];

        mergeSort(left);
        mergeSort(right);

        merge(right, left, arr);
    }
    // O(N)
    public static void merge(int[] left, int[] right, int[] res) {
        int l = 0, r = 0, i = 0;
        while (l != left.length && r != right.length) {
            if(left[l] < right[r])
                res[i++] = left[l++];
            else
                res[i++] = right[r++];
        }
        while (l < left.length)
            res[i++] = left[l++];

        while (r < right.length)
            res[i++] = right[r++];
    }
