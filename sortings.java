    public static void swap(int i, int j, int [] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void bubbleSort(int [] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            boolean isSorted = true;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(j + 1, j, arr);
                    isSorted = false;
                }
            }
            if (isSorted) return;
            display(arr);
            System.out.println();
        }
    }
    
    public static void selectionSort(int [] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minn = i;
            for (int j = i; j < arr.length; j++)
                if (arr[j] < arr[minn])
                    minn = j;
            swap(minn, i, arr);
        }
    }

    public static void insertionSort(int [] arr) {
        // NO need to begin with the first item science the sorted array will contain one element
        // and obviously this element is in place so no need to start with it
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i], j = i - 1;
            /*for (j = i - 1; j >= 0; j--) {
                if (arr[j] > temp)
                    arr[j + 1] = arr[j];
                else break;
            }*/
            // using while loop for cleaner visualisation instead of break and ugly for with variable outside
            while (j >= 0 && arr[j] > temp) {
                arr[j +  1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }
