// Don't worry brother
// https://www.youtube.com/watch?v=HOPwlxCl64o&list=PLWPirh4EWFpG49yASGCmvOwXwVvgnm6Jt&index=125
// https://www.youtube.com/watch?v=GWGr5XQFXPc&list=PLWPirh4EWFpG49yASGCmvOwXwVvgnm6Jt&index=126


// The main function to that sorts arr[] of size n using
// Radix Sort
public static void radixsort(int arr[]) {
    int m = maxValue(arr);

    for (int exp = 1; m / exp > 0; exp *= 10)
        countSort(arr, exp);                         // exp is 1, 10, 100, ...
}

// A function to do counting sort of arr[] according to
// the digit represented by exp.
public static void countSort(int arr[], int exp) {
    int n = arr.length;
    int output[] = new int[n];
    int count[] = new int[10];
    Arrays.fill(count, 0);

    // calculate the frequency of 0~9 at that location indicated by exp
    for (int i = 0; i < n; i++)
        count[(arr[i] / exp) % 10]++;

    // Change count[i] so that count[i] now contains
    // actual position of this digit in output[]
    for (int i = 1; i < 10; i++)
        count[i] += count[i - 1];
    // Build the output array
    for (int i = n - 1; i >= 0; i--) {
        output[count[(arr[i] / exp) % 10] - 1] = arr[i];
        count[(arr[i] / exp) % 10]--;
    }
    for (int i = 0; i < n; i++)
        arr[i] = output[i];
}
