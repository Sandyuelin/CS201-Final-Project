import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DatabaseProcessing {

    private MyBST bst = new MyBST();     

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

    public static void main(String[] args) {
        DatabaseProcessing db = new DatabaseProcessing();
        db.loadData("people.txt"); // Example filename
    }
}
