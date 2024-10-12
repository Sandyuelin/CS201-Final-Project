import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

class ShortLengthException extends Exception {
    public ShortLengthException(String message) {
        super(message);
    }
}

public class DatabaseProcessing {

    private MyBST bst = new MyBST();     
    // private MyHashMap<String, Integer> wordCountMap = new MyHashMap<>();

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
    /*
    public PeopleRecord search(String familyName, String givenName) {
        return bst.search(familyName, givenName);
    }
    public MyHeap sort(MyBST bst) {
        MyHeap heap = new MyHeap(bst.root);
        heap.heapify();
        return heap;
    }
    
    public List<WordFrequency> getMostFrequentWords(int count, int len) throws ShortLengthException{
        // handle the invalid input for len 
        if (len<3) {
            System.err.println("Error: The length of the word must be at least 3.");
            throw new ShortLengthException("The length of the word must be at least 3.");
        }
        wordCountMap.clear();
        // filter the valid words
        try(BufferedReader reader = new BufferedReader(new FileReader("people.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");

                String [] fields = {
                    data[0],// familyName
                    data[1],//givenName
                    data[2],//companyName
                    data[3],//address
                    data[4],//city
                    data[5],//county
                    data[6],//state
                };

                for (String field : fields) {
                    String[] words = field.split("\\s+");
                    for (String word : words) {
                        String cleanedWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

                        if(cleanedWord.length() >= len && cleanedWord.matches("[a-zA-Z]+")) {   
                            int currentCount = wordCountMap.getOrDefault(cleanedWord, 0);
                            wordCountMap.put(cleanedWord, wordCountMap.getOrDefault(cleanedWord, 0) + 1);
                        }
            }
        }
    
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
        
    // Helper class to store word and its frequency
    public static class WordFrequency {
        private String word;
        private int frequency;

        public WordFrequency(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }
        public String getWord(){
            return word;
        }
        public int getFrequency() {
            return frequency;
        }

        @Override
        public String toString() {
            return word + ": " + frequency;
        }
    }
        */


        // Testing method:

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
        public void testSearchFromDataset() {
            System.out.println("Searching records...");
            
            PeopleRecord result1 = bst.search("Smith", "John");
            assert result1 != null : "Record for John Smith not found";
            assert result1.getEmail().equals("john@abc.com");
            
            PeopleRecord result2 = bst.search("Doe", "Jane");
            assert result2 != null : "Record for Jane Doe not found";
            assert result2.getPhone1().equals("345-6789");
    
            // Try searching for a non-existent record
            PeopleRecord result3 = bst.search("Brown", "Michael");
            assert result3 == null : "Non-existent record found incorrectly";
            
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
            DatabaseProcessing db = new DatabaseProcessing();
    
            // Load data from file
            db.loadData("people.txt");
            /*
             * Total nodes: 498
             * Height: 17
             * theoretical minimum height is log2(498) = 8.96, 
             */

            db.testInsertFromDataset();
            db.testSearchFromDataset();
            db.testGetInfo();
        }
    
    


}
