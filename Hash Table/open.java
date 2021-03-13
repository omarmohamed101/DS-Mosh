package HashTaples;

public class OpenAdressing {
    private class Entry {
        private int key;
        private int data;

        public Entry(int key) {
            this.key = key;
        }
        public int getKey(){
            return this.key;
        }
        public void setData(int data) {
            this.data = data;
        }
    }

    private Entry[] arr;
    private double currentElements;

    public OpenAdressing(int size) {
        arr = new Entry[size];
        currentElements = 0;
    }
    public int hash(int key) {
        return key % arr.length;
    }

    public int hash2(int key) {
        return 5 - key % 5;
    }
    // open addressing -> linear probing
    public void insertL(Entry item) {

        // first check alpha
        if (currentElements / arr.length >= 0.5)
            return;

        int key = item.getKey();
        int index = hash(key);
        // searching for del item or an empty place
        while (arr[index] != null && arr[index].data != -1) {
            index++;
            index %= arr.length;
        }
        arr[index] = item;

    }

    public void insertQ(Entry item) {

        // first check alpha
        if (currentElements / arr.length >= 0.5)
            return;

        int key = item.getKey();
        int index = hash(key);
        int i = 1;
        // searching for del item or an empty place
        while (arr[index] != null && arr[index].data != -1) {
            index += Math.pow(i++, 2);
            index %= arr.length;
        }
        arr[index] = item;
    }

    public Entry find(int key) {
        // to prevent infinite loop
        if (currentElements / arr.length >= 0.5)
            return null;

        int index = hash(key);
        while (arr[index] != null) {
            if (arr[index].getKey() == key)
                return arr[index];
            index++;
            index %= index;
        }
        return null;
    }

    public Entry delete(int key) {
        int index = hash(key);

        while (arr[index] != null) {
            if (arr[index].getKey() == key) {
                Entry temp = arr[index];
                arr[index].setData(-1);
                return temp;
            }
            index++;
            index %= arr.length;
        }
        return null;
    }

    //--------------------------------------
    // Double hashing
    public void insertD(Entry item) {

        // first check alpha
        if (currentElements / arr.length >= 0.5)
            return;

        int key = item.getKey();
        int index = hash(key);
        int step = hash2(key);
        // searching for del item or an empty place
        while (arr[index] != null && arr[index].data != -1) {
            index += step;
            index %= arr.length;
        }
        arr[index] = item;
    }

    public Entry deleteD(int key) {
        int index = hash(key);
        int step = hash2(key);

        while (arr[index] != null) {
            if (arr[index].getKey() == key) {
                Entry temp = arr[index];
                arr[index].setData(-1);
                return temp;
            }
            index += step;
            index %= arr.length;
        }
        return null;
    }

    public Entry findD(int key) {
        // to prevent infinite loop
        if (currentElements / arr.length >= 0.5)
            return null;

        int index = hash(key);
        int step = hash2(key);

        while (arr[index] != null) {
            if (arr[index].getKey() == key)
                return arr[index];
            index += step;
            index %= index;
        }
        return null;
    }
}
