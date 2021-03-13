public static void shellSort(int[] arr) {
  int gab = 1;
  while (gab < arr.length / 3)
    gab = 3 * gab + 1;
  
  while (gab > 0) {
    for (int i = 0; i < arr.length; i++) {
      int temp = arr[i], j;
      for (j = i; j > gab - 1 && arr[j - gab] > temp; j -= gab)
        arr[j] = arr[j - h];

      arr[j] = temp;
    }
    
    gab = (gab - 1) / 3;
  }
}
