    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void quicksort(int[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }

    private static void quicksort(int[] arr, int start, int end) {
        if (start >= end) return;
        // partitioning
        int pivit = partiton(arr, start, end);
        // sort right half
        quicksort(arr, start, pivit - 1);
        // sort left half
        quicksort(arr, pivit + 1, end);
    }

    // 1 2 3 4 5
    // 5 -> pivit
    // ------ 5 -----
    private static int partiton(int [] arr, int start, int end) {
        int pivit = arr[end];
        int boundry = start - 1;
        for (int i = start; i <= end; i++) {
            if (arr[i] <= pivit) {
                boundry++;
                swap(arr, i, boundry);
            }
        }
        return boundry;

    }
