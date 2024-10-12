import java.util.ArrayList;

public class MyHashMap {
    private ArrayList<PeopleRecord> table;
    private int capacity;
    private int size;

    // initialize hash map with a specified capacity
    public MyHashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.table = new ArrayList<>(); // create an empty arraylist

        // fill the arraylist with null values to initialize the capacity
        for (int i = 0; i < capacity; i++) {
            table.add(null); // add null placeholders
        }

        this.size = 0;
    }

    // insert a new record into the hash map
    public void put(String key, PeopleRecord record) {
        int hash = hash(key); // get hash index
        int i = 0;
        int index = quadraticProbe(hash, i); // quadratic probing to handle collisions

        // find an empty spot using quadratic probing
        while (table.get(index) != null) {
            i++;
            index = quadraticProbe(hash, i);
        }

        table.set(index, record); // insert the record at the calculated index
        size++;

        // resize the table if load factor exceeds 0.75
        if (size >= capacity * 0.75) {
            resize();
        }
    }

    // retrieve a record based on the key (person's name)
    public PeopleRecord get(String key) {
        int hash = hash(key); // get hash index
        int i = 0;
        int index = quadraticProbe(hash, i); // quadratic probing to find the key

        // loop until we find the matching key or encounter a null slot
        while (table.get(index) != null) {
            if (table.get(index).getFamilyName().equals(key)) {
                return table.get(index); // return the matching record
            }
            i++;
            index = quadraticProbe(hash, i);
        }

        return null; // key not found
    }

    // delete a record by key
    public void delete(String key) {
        int hash = hash(key); // get hash index
        int i = 0;
        int index = quadraticProbe(hash, i); // quadratic probing to find the key

        // loop until we find the matching key or encounter a null slot
        while (table.get(index) != null) {
            if (table.get(index).getFamilyName().equals(key)) {
                table.set(index, null); // set the index to null (delete)
                size--;
                break;
            }
            i++;
            index = quadraticProbe(hash, i);
        }
    }

    // hash function to calculate the index for the key
    private int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    // quadratic probing to handle collisions
    private int quadraticProbe(int hash, int i) {
        return (hash + i * i) % capacity;
    }

    // resize the hash map when load factor exceeds the threshold
    private void resize() {
        capacity *= 2; // double the capacity
        ArrayList<PeopleRecord> oldTable = table; // store old table
        table = new ArrayList<>(); // create a new empty arraylist

        // initialize the new table with null values
        for (int i = 0; i < capacity; i++) {
            table.add(null);
        }

        size = 0;

        // rehash all non-null records from the old table into the new table
        for (PeopleRecord record : oldTable) {
            if (record != null) {
                put(record.getFamilyName(), record); // insert the record into the new table
            }
        }
    }
}
