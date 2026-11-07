import java.util.LinkedList;

class KeyValuePair {
    String key;
    int value;

    public KeyValuePair(String key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class SeparateChainingHashTable {
    private static final int TABLE_SIZE = 10;
    private LinkedList<KeyValuePair>[] table;

    public SeparateChainingHashTable() {
        table = new LinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % TABLE_SIZE;
    }

    public void put(String key, int value) {
        int index = hash(key);
        LinkedList<KeyValuePair> bucket = table[index];

        // Check if the key already exists in the bucket
        for (KeyValuePair entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value; // Update the existing value
                return;
            }
        }

        // Key doesn't exist in the bucket, add a new entry
        bucket.add(new KeyValuePair(key, value));
    }

    public Integer get(String key) {
        int index = hash(key);
        LinkedList<KeyValuePair> bucket = table[index];

        // Search for the key in the bucket
        for (KeyValuePair entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        return null; // Key not found
    }

    public void remove(String key) {
        int index = hash(key);
        LinkedList<KeyValuePair> bucket = table[index];

        // Search for the key in the bucket and remove it if found
        for (KeyValuePair entry : bucket) {
            if (entry.key.equals(key)) {
                bucket.remove(entry);
                return;
            }
        }
    }

    public static void main(String[] args) {
        SeparateChainingHashTable hashTable = new SeparateChainingHashTable();

        hashTable.put("Mary", 85);
        hashTable.put("Steve", 92);
        hashTable.put("Onyx", 78);
        hashTable.put("Karen", 95);

        System.out.println("Steve's grade: " + hashTable.get("Steve")); //
        System.out.println("Onyx's grade: " + hashTable.get("Onyx")); //

        hashTable.remove("Bob");
        System.out.println("Bob's grade: " + hashTable.get("Bob")); // Output: Bob's grade: null (not found)
    }
}