
    // original shell sort
    // O(N * (log(N))^2)
    public static void shellSort(int[] array) {

        int n = array.length;
        for (int gab = n / 2; gab > 0; gab /= 2) {
            for (int i = gab; i < n; i++) {
                int temp = array[i], j;

                for(j = i; j >= gab && array[j - gab] > temp; j -= gab)
                   array[j] = array[j - gab];

                array[j] = temp;
            }
        }
    }
    
    // Knuth algorithm doesn't required in the exam
