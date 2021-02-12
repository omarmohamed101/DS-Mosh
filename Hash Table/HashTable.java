package HashTaples;


import java.util.LinkedList;

public class HashTable {

    // put(k, v)
    // get(k): v
    // remove(k)
    // k: int
    // v: String
    // Collisions->chaning
    
    private class Entry {
        private String value;
        private int key;
        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    private LinkedList<Entry>[] array;

    public HashTable(int size) {
        array = new LinkedList[size];
    }

    private int hash(int key) {
        return key % array.length;
    }

    public void put(int key, String value) {

        Entry e = new Entry(key, value);
        int index = hash(key);

        if (array[index] == null) array[index] = new LinkedList<Entry>();

        // if There is an Existing entry with same key update it
        for (Entry en: array[index]) {
            if (en.key == key) {
                en.value = value;
                return;
            }
        }
        // adding the new entry
        array[index].addLast(e);
    }
    public String get(int key) {
        int index = hash(key);
        LinkedList<Entry> bucket = array[index];

        // To prevent iterating over null
        if (bucket == null) return null;
        for (Entry en: bucket) {
            if (en.key == key)
                return en.value;
        }
        return null;
    }

    public void remove(int key) {
        int index = hash(key);
        LinkedList<Entry> bucket = array[index];

        if (bucket == null) return;

        for (Entry en: bucket) {
            if (en.key == key) {
                bucket.remove(en);
                return;
            }
        }

    }
}
