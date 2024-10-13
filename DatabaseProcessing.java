import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class PeopleRecord {
    private String givenName;
    private String familyName;
    private String companyName;
    private String address;
    private String city;
    private String county;
    private String state;
    private String zip;
    private String phone1;
    private String phone2;
    private String email;
    private String web;
    private String birthday;

    // Constructor
    public PeopleRecord(String givenName, String familyName, String companyName, String address, String city,
                        String county, String state, String zip, String phone1, String phone2, String email,
                        String web, String birthday) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.companyName = companyName;
        this.address = address;
        this.city = city;
        this.county = county;
        this.state = state;
        this.zip = zip;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.web = web;
        this.birthday = birthday;
    }
    public PeopleRecord(String givenName, String familyName) {
        this.givenName = givenName;
        this.familyName = familyName;
        // You can set other fields to default values, if needed.
        this.companyName = "";
        this.address = "";
        this.city = "";
        this.county = "";
        this.state = "";
        this.zip = "";
        this.phone1 = "";
        this.phone2 = "";
        this.email = "";
        this.web = "";
        this.birthday = "";
    }   

    // Getters for all attributes
    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getEmail() {
        return email;
    }

    public String getWeb() {
        return web;
    }

    public String getBirthday() {
        return birthday;
    }

    // Override toString for easy printing
    @Override
    public String toString() {
        return givenName + " " + familyName + ", " + companyName + ", " + address + ", " + city + ", " + county + ", "
                + state + " " + zip + ", Phone: " + phone1 + ", Email: " + email + ", Web: " + web + ", Birthday: " + birthday;
    }
}




class MyBST {
    // Node class definition
    public static class Node {
        PeopleRecord data;
        Node left, right;

        public Node(PeopleRecord record) {
            data = record;
            left = right = null;
        }
    }

    private Node root;

    public MyBST() {
        root = null;
    }

    // Insertion
    public void insert(PeopleRecord record) {
        root = insertRec(root, record);
    }

    private Node insertRec(Node root, PeopleRecord record) {
        if (root == null) {
            return new Node(record);
        }

        int familyComparison = record.getFamilyName().compareTo(root.data.getFamilyName());
        if (familyComparison < 0) {
            root.left = insertRec(root.left, record);
        } else if (familyComparison > 0) {
            root.right = insertRec(root.right, record);
        } else {
            // If family names are equal, compare given names
            int givenComparison = record.getGivenName().compareTo(root.data.getGivenName());
            if (givenComparison < 0) {
                root.left = insertRec(root.left, record);
            } else if (givenComparison > 0) {
                root.right = insertRec(root.right, record);
            }
            // Optionally handle duplicates here if needed
        }
        return root;
    }

    // Search: search for a record by family name and given name
    public List<PeopleRecord> search(String familyName, String givenName) {
        List<PeopleRecord> results = new ArrayList<>();
        searchHelper(root, familyName, givenName, results);
        return results;
    }

    private void searchHelper(Node node, String familyName, String givenName, List<PeopleRecord> results) {
        if (node == null) {
            return;
        }

        // Check if current node matches the search criteria
        if (node.data.getFamilyName().equalsIgnoreCase(familyName) &&
            node.data.getGivenName().equalsIgnoreCase(givenName)) {
            results.add(node.data);
        }

        // Continue searching in left and right subtrees
        searchHelper(node.left, familyName, givenName, results);
        searchHelper(node.right, familyName, givenName, results);
    }

    // Deletion: delete a record by family name and given name
    public void delete(String familyName, String givenName) {
        root = deleteRec(root, familyName, givenName);
    }

    private Node deleteRec(Node root, String familyName, String givenName) {
        if (root == null) {
            return root;
        }

        int familyComparison = familyName.compareTo(root.data.getFamilyName());
        if (familyComparison < 0) {
            root.left = deleteRec(root.left, familyName, givenName);
        } else if (familyComparison > 0) {
            root.right = deleteRec(root.right, familyName, givenName);
        } else {
            int givenComparison = givenName.compareTo(root.data.getGivenName());
            if (givenComparison < 0) {
                root.left = deleteRec(root.left, familyName, givenName);
            } else if (givenComparison > 0) {
                root.right = deleteRec(root.right, familyName, givenName);
            } else {
                // Node to be deleted is found
                // Case 1: Node has no left child
                if (root.left == null) {
                    return root.right;
                // Case 2: Node has no right child
                } else if (root.right == null) {
                    return root.left;
                }
                // Case 3: Node has two children, replace it with the inorder successor
                root.data = minValue(root.right);
                // Delete the inorder successor in the right subtree
                root.right = deleteRec(root.right, root.data.getFamilyName(), root.data.getGivenName());
            }
        }
        return root;
    }

    // minValue: a helper function to find the minimum value node in a subtree
    private PeopleRecord minValue(Node root) {
        PeopleRecord minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    // Get the root of the BST
    public Node getRoot() {
        return root;
    }
    public String getInfo() {
        return "Total nodes: " + getTotalNodes(root) + ", Height: " + getHeight(root);
    }

    private int getTotalNodes(Node root){
        if (root == null) {
            return 0;
        }
        return 1 + getTotalNodes(root.left) + getTotalNodes(root.right);
    }

    private int getHeight (Node root){
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    // Method to visualize the BST
    public void visualize() {
        visualize(root, "", true);
    }

    private void visualize(Node node, String prefix, boolean isTail) {
        if (node != null) {
            System.out.print(prefix + (isTail ? "└── " : "├── ") + node.data.getFamilyName() + "\n");
            visualize(node.left, prefix + (isTail ? "    " : "│   "), false);
            visualize(node.right, prefix + (isTail ? "    " : "│   "), true);
        }
    }

    // Method to get all records as an ArrayList
    public ArrayList<PeopleRecord> getAllRecords() {
        ArrayList<PeopleRecord> records = new ArrayList<>();
        getAllRecordsHelper(root, records);
        return records;
    }

    // Recursively traverse through tree nodes/children
    private void getAllRecordsHelper(Node node, ArrayList<PeopleRecord> records) {
        if (node != null) {
            records.add(node.data); // add current node's data
            getAllRecordsHelper(node.left, records);  // repeat for left subtree
            getAllRecordsHelper(node.right, records); // repeat for right subtree
        }
    }
}

// Max-heap which stores records by name (z on top, a on bottom)
// Using ArrayList based implementation


class MyHeap {
    private ArrayList<PeopleRecord> heap;

    public MyHeap() {
        heap = new ArrayList<>();
    }

    // add a node to end of list, then trickle up to restore heap condition
    public void insert(PeopleRecord newNode) {
        heap.add(newNode);
        trickleUp(heap.size() - 1);
    }

    // move last node to root then trickle down and return deleted root
    public PeopleRecord remove() {
        if (heap.isEmpty()) {
            return null;
        }
        
        PeopleRecord root = heap.get(0);
        
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        trickleDown(0);

        return root;
    }

    // recursively swap node n with its parent node p while n is bigger than p
    private void trickleUp(int n) {
        if (n == 0) {
            return;
        }

        int p = parentIndex(n);
    
        // if current node is greater than parent node, swap and continue trickling up
        if (heap.get(n).getFamilyName().compareTo(heap.get(p).getFamilyName()) > 0) {
            swap(n, p);
            trickleUp(p);
        }
    }
    // recursively swap parent node p with bigger child
    private void trickleDown(int p) {
        int l = leftIndex(p); // left child index
        int r = rightIndex(p); // right child index
        int biggest = p; // initialize biggest as current parent index

        // update biggest if left child is greater
        if (l < heap.size() && heap.get(l).getFamilyName().compareTo(heap.get(biggest).getFamilyName()) > 0) {
            biggest = l;
        }

        // update biggest if right child is greater
        if (r < heap.size() && heap.get(r).getFamilyName().compareTo(heap.get(biggest).getFamilyName()) > 0) {
            biggest = r;
        }

        // if current parent is not the biggest, swap and trickle down again 
        if (p != biggest) {
            swap(p, biggest);
            trickleDown(biggest);
        }
    }

    // swap two node indices
    private void swap(int i, int j) {
        PeopleRecord temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public int size(){
        return heap.size();
    }

    // get index of parent node
    private int parentIndex(int i) {
        return (i - 1) / 2;
    }

    // get index of left child node
    private int leftIndex(int i) {
        return 2 * i + 1;
    }

    // get index of right child node
    private int rightIndex(int i) {
        return 2 * i + 2;
    }

}

// Hash map which stores records by family name
// Using ArrayList based implementation 
// With quadratic probing for collision handling

class MyHashMap {
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




class ShortLengthException extends Exception {
    public ShortLengthException(String message) {
        super(message);
    }
}

public class DatabaseProcessing {

    private MyBST bst = new MyBST(); // 
    private MyHeap heap = new MyHeap(); // heap to sort records
    private MyHashMap wordFrequencyMap = new MyHashMap(100); // hash map to store word frequencies
    
    public DatabaseProcessing() {
        // Initialize the BST, heap, and wordFrequencyMap
        bst = new MyBST();
        heap = new MyHeap();
        wordFrequencyMap = new MyHashMap(100);
    }
    // Load data from file and insert into BST
    public void loadData(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming the delimiter is ';' based on your example
                String[] data = line.split(";");

                // Extracting fields from the data array
                String familyName = data[0];
                String givenName = data[1];
                String companyName = data[2];
                String address = data[3];
                String city = data[4];
                String county = data[5];
                String state = data[6];
                String zip = data[7];
                String phone1 = data[8];
                String phone2 = data[9];
                String email = data[10];
                String web = data[11];
                String[] date = data[12].split("/");
                String birthday = date[2] + "-" + date[0] + "-" + date[1];

                PeopleRecord record = new PeopleRecord(givenName, familyName, companyName, address, city, county, state, zip, phone1, phone2, email, web, birthday);

                bst.insert(record);
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    
    }
    public MyBST getBST(){
        return bst;
    }
     // search for a PeopleRecord by family name and given name
    // calls the search method in MyBST class
    public List<PeopleRecord> search(String familyName, String givenName) {
    return bst.search(familyName, givenName);
}


    // sort all records using MyHeap (ascending by family name)
    public ArrayList<PeopleRecord> sort() {
        ArrayList<PeopleRecord> sortedRecords = new ArrayList<>();

        // get all records from BST and put them into a heap
        ArrayList<PeopleRecord> recordsFromBST = bst.getAllRecords();

        for (PeopleRecord record : recordsFromBST) {
            heap.insert(record);
        }

        // extract records from the heap and add to sorted list
        while (heap.size() > 0) {
            sortedRecords.add(heap.remove()); // remove the largest element from the heap
        }

        return sortedRecords; 
    }
    

    // 
    public String[][] getMostFrequentWords(int count, int len) throws ShortLengthException {
        if (len < 3) {
            throw new ShortLengthException("The length must be at least 3.");
        }
    
        wordFrequencyMap = new MyHashMap(100); // reset the frequency map
        ArrayList<PeopleRecord> records = bst.getAllRecords();
        ArrayList<String> wordsList = new ArrayList<>(); // to store words added to the map
    
        // process each record
        for (PeopleRecord record : records) {
            String[] fields = {
                record.getGivenName(),
                record.getFamilyName(),
                record.getCompanyName(),
                record.getAddress(),
                record.getCity(),
                record.getCounty(),
                record.getState()
            };
    
            // extract andcount words
            for (String field : fields) {
                for (String word : field.split("\\s+")) {
                    String cleanedWord = word.replaceAll("[^a-zA-Z]", "");
                    if (cleanedWord.length() >= len) {
                        // increment frequency in MyHashMap
                        int frequency = wordFrequencyMap.get(cleanedWord) != null ? 
                            Integer.parseInt(wordFrequencyMap.get(cleanedWord).getGivenName()) : 0;
                        frequency++;
                        wordFrequencyMap.put(cleanedWord, new PeopleRecord(String.valueOf(frequency), cleanedWord));
                        if (!wordsList.contains(cleanedWord)) {
                            wordsList.add(cleanedWord); // keep track of the words
                        }
                    }
                }
            }
        }
    
        // prepare result array
        String[][] result = new String[count][2];
        int index = 0;
    
        // find top 'count' words
        while (index < count) {
            String topWord = null;
            int topFrequency = 0;
    
            // iterate through wordsList to find the highest frequency word
            for (String word : wordsList) {
                PeopleRecord record = wordFrequencyMap.get(word); // get record using the word key
                if (record != null) {
                    int frequency = Integer.parseInt(record.getGivenName());
                    if (frequency > topFrequency) {
                        topFrequency = frequency;
                        topWord = word;
                    }
                }
            }
    
            if (topWord != null) {
                result[index][0] = topWord;
                result[index][1] = String.valueOf(topFrequency);
                index++;
                wordFrequencyMap.delete(topWord); // remove to avoid recounting
                wordsList.remove(topWord); // remove from the words list as well
            } else {
                break; // no more words found
            }
        }
    
        return result; // return the top 'count' most frequent words
    }
    

    // print athe first n records to make data more readable
    private static void displayRecords(ArrayList<PeopleRecord> records, int n) {
        for (int i = 0; i < Math.min(n, records.size()); i++) {
            System.out.println(records.get(i) + "\n");
        }
    }

    
    // Test insertion functionality

        public void testInsertFromDataset() {
            System.out.println("Inserting records from dataset...");
            bst.insert(new PeopleRecord("John", "Smith", "ABC Corp", "123 Main St", "CityA", "CountyA", "StateA", "12345", "123-4567", "234-5678", "john@abc.com", "www.johnsmith.com", "1990-01-01"));
            bst.insert(new PeopleRecord("Jane", "Doe", "XYZ Inc", "456 High St", "CityB", "CountyB", "StateB", "67890", "345-6789", "456-7890", "jane@xyz.com", "www.janedoe.com", "1992-02-02"));
            bst.insert(new PeopleRecord("Emily", "Brown", "DEF LLC", "789 Oak St", "CityC", "CountyC", "StateC", "54321", "789-0123", "890-1234", "emily@def.com", "www.emilybrown.com", "1994-03-03"));
            bst.insert(new PeopleRecord("Michael", "Johnson", "GHI Co", "321 Pine St", "CityD", "CountyD", "StateD", "98765", "654-3210", "765-4321", "michael@ghi.com", "www.michaeljohnson.com", "1988-04-04"));
            bst.insert(new PeopleRecord("Sarah", "Williams", "JKL Inc", "654 Maple St", "CityE", "CountyE", "StateE", "87654", "432-1098", "543-2109", "sarah@jkl.com", "www.sarahwilliams.com", "1991-05-05"));
    
            assert bst.getInfo().contains("Total nodes: 5"); // Adjust based on number of records inserted
            System.out.println("Insertion from dataset test passed!");
        }
    
        // Test search functionality
        // Test search functionality
public void testSearchFromDataset() {
    System.out.println("Searching records...");

    // Test case 1: Search for an existing record
    List<PeopleRecord> results1 = bst.search("Smith", "John");
    if (!results1.isEmpty()) {
        assert results1.get(0).getEmail().equals("john@abc.com") : "Expected email for John Smith does not match.";
        System.out.println("Found record for John Smith: " + results1.get(0));
    } else {
        System.out.println("Record for John Smith not found.");
    }

    // Test case 2: Search for another existing record
    List<PeopleRecord> results2 = bst.search("Doe", "Jane");
    if (!results2.isEmpty()) {
        assert results2.get(0).getPhone1().equals("345-6789") : "Expected phone number for Jane Doe does not match.";
        System.out.println("Found record for Jane Doe: " + results2.get(0));
    } else {
        System.out.println("Record for Jane Doe not found.");
    }

    // Test case 3: Try searching for a non-existent record
    List<PeopleRecord> results3 = bst.search("Brown", "Michael");
    assert results3.isEmpty() : "Non-existent record found incorrectly for Michael Brown.";
    if (results3.isEmpty()) {
        System.out.println("Correctly identified that no record exists for Michael Brown.");
    }

    System.out.println("Search from dataset test passed!");
}

    
        // Test the getInfo() method
    public void testGetInfo() {
            System.out.println("Testing getInfo()...");
            String info = bst.getInfo();
            System.out.println(info); // This will print something like: "Total nodes: 5, Height: 3"
            
            // You can also assert the values if you know the expected number of nodes and height
            assert info.contains("Total nodes: 5");
            assert info.contains("Height: "); // Adjust based on the actual tree height
            
            System.out.println("getInfo test passed!");
        }
    
        // Main method to load data and test
    public static void main(String[] args) {
            // Set headless mode
            System.setProperty("java.awt.headless", "true");
        
            DatabaseProcessing db = new DatabaseProcessing();
            System.out.println("Loading data from 'people.txt'...");
            db.loadData("people.txt");
        
            db.testInsertFromDataset();
            db.testSearchFromDataset();
            db.testGetInfo();
            System.out.println("Visualizing the BST:");
            db.getBST().visualize(); // 

            
            
            // Visualize the BST
           //  DataStructureVisualizer.visualize(db.getBST());
            // Load data from the file
        // Test search()
        System.out.println("\n--- Searching for 'James Butt' ---\n");
        List<PeopleRecord> searchResult = db.bst.search("Butt", "James");
        if (searchResult != null) {
            System.out.println("Record found ");
        } else {
            System.out.println("No records found with the name 'James Butt'.");
        }

        // Test sort()
        System.out.println("\n--- Sorting Records by Family Name ---\n");
        ArrayList<PeopleRecord> sortedList = db.sort();

        System.out.println("Displaying first 10 sorted records: \n");
        displayRecords(sortedList, 10);  // print only the first 10 sorted records

        // Test getMostFrequentWords()
        System.out.println("\n--- Getting Most Frequent Words ---");
        try {
            String[][] frequentWords = db.getMostFrequentWords(5, 3);
            System.out.println("Top 5 most frequent words:");
            for (String[] entry : frequentWords) {
                if (entry[0] != null) { // Ensure entry is not null
                    System.out.println(entry[0] + ": " + entry[1]);
                }
            }
        } catch (ShortLengthException e) {
            System.out.println(e.getMessage());
        }
        }
           
    
    


}
