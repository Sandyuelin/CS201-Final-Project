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
    private MyHashMap<String, Integer> wordCountMap = new MyHashMap<>();

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

    public static void main(String[] args) {
        DatabaseProcessing db = new DatabaseProcessing();
        db.loadData("people.txt"); 

        // search fr a specific record
        PeopleRecord result = db.search("Smith" , "John");
        if (result != null){
            System.out.println("");
        }
        else {
            System.out.println("No matching record found.");
        }
    }
}
