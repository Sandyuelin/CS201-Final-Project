// Hash map which stores records by family name
// Using ArrayList based implementation 
// With quadratic probing for collision handling

import java.util.ArrayList;

public class MyHashMap {
    private ArrayList<PeopleRecord> table;
    private int capacity;
    private int size;

    // initialize hash map with a specified capacity
    public MyHashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.table = new ArrayList<>();

        // fill the arraylist with null values to initialize the capacity
        for (int i = 0; i < capacity; i++) {
            table.add(null); 
        }

        this.size = 0;
    }

    // insert a new record into the hash map
    public void put(String key, PeopleRecord record) {
        int hash = hash(key); 
        int i = 0;
        int index = quadraticProbe(hash, i);

        // find an empty spot
        while (table.get(index) != null) {
            i++;
            index = quadraticProbe(hash, i);
        }

        table.set(index, record); 
        size++;

        // resize the table if load factor exceeds 0.75
        if (size >= capacity * 0.75) {
            resize();
        }
    }

    // retrieve a record based on the key (person's name)
    public PeopleRecord get(String key) {
        int hash = hash(key);
        int i = 0;
        int index = quadraticProbe(hash, i); // quadratic probing to find the key

        // loop until we find the matching key or a null slot
        while (table.get(index) != null) {
            if (table.get(index).getFamilyName().equals(key)) {
                return table.get(index); 
            }
            i++;
            index = quadraticProbe(hash, i);
        }

        return null; // key not found
    }

    // delete a record by key
    public void delete(String key) {
        int hash = hash(key);
        int i = 0;
        int index = quadraticProbe(hash, i);

        while (table.get(index) != null) {
            if (table.get(index).getFamilyName().equals(key)) {
                table.set(index, null); // set index to null
                size--;
                break;
            }
            i++;
            index = quadraticProbe(hash, i);
        }
    }

    // return current size of hash map
    public int size() {
        return size;  // Return the number of elements in the hash map
    }

    // hash function to calculate index for key
    private int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    // quadratic probing to handle collisions
    private int quadraticProbe(int hash, int i) {
        return (hash + i * i) % capacity;
    }

    // resize the hash map when load factor exceeds threshold
    private void resize() {
        capacity *= 2; 
        ArrayList<PeopleRecord> oldTable = table; 
        table = new ArrayList<>(); 

        // initialize the new table with null values
        for (int i = 0; i < capacity; i++) {
            table.add(null);
        }

        size = 0;

        // rehash all non-null records from old table into new table
        for (PeopleRecord record : oldTable) {
            if (record != null) {
                put(record.getFamilyName(), record); // insert the record into the new table
            }
        }
    }
}
