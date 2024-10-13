import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseProcessing {

    private MyBST bst = new MyBST(); // 
    private MyHeap heap = new MyHeap(); // heap to sort records
    private MyHashMap wordFrequencyMap = new MyHashMap(100); // hash map to store word frequencies

    // Load data from file and insert into BST
    public void loadData(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
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

    // search for a PeopleRecord by family name and given name
    // calls the search method in MyBST class
    public PeopleRecord search(String familyName, String givenName) {
        return bst.search(familyName, givenName);  // call the search method from MyBST
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
    

    // Exception class for handling short length errors
    public class ShortLengthException extends Exception {
        public ShortLengthException(String message) {
            super(message);
        }
    }
    

    public static void main(String[] args) {
        DatabaseProcessing db = new DatabaseProcessing();

        // Load data from the file
        System.out.println("Loading data from 'people.txt'...");
        db.loadData("people.txt");

        // Test search()
        System.out.println("\n--- Searching for 'James Butt' ---\n");
        PeopleRecord searchResult = db.bst.search("Butt", "James");
        if (searchResult != null) {
            System.out.println("Record found: " + searchResult);
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

    // print athe first n records to make data more readable
    private static void displayRecords(ArrayList<PeopleRecord> records, int n) {
        for (int i = 0; i < Math.min(n, records.size()); i++) {
            System.out.println(records.get(i) + "\n");
        }
    }
}
