import java.util.ArrayList;
import java.util.List;

class MyBST<T extends Comparable<T>> {
    // A binary search tree for managing people's records
    // Methods of insertion, getInfo, and search are included

    class Node {
        T data;
        Node left, right;

        Node(T record) {
            data = record;
            left = right = null;
        }
    }

    private Node root;
    private final Class<T> type;

    public MyBST(Class<T> type) {
        this.type = type;
        root = null;
    }

    // Insertion
    public void insert(T record) {
        root = insertRec(root, record);
    }

    private Node insertRec(Node root, T record) {
        if (root == null) {
            root = new Node(record);
            return root;
        }
        if (record.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, record);
        } else if (record.compareTo(root.data) > 0) {
            root.right = insertRec(root.right, record);
        }

        return root;
    }

    // Search by family name and given name
    public List<T> search(String familyName, String givenName) {
        List<T> matchingRecords = new ArrayList<>();
        searchRec(root, familyName, givenName, matchingRecords);
        return matchingRecords;
    }

    private void searchRec(Node root, String familyName, String givenName, List<T> matchingRecords) {
        if (root == null) {
            return;
        }

        if (matches(root.data, familyName, givenName)) {
            matchingRecords.add(root.data);
        }
        // Traverse left and right subtrees to continue searching
        searchRec(root.left, familyName, givenName, matchingRecords);
        searchRec(root.right, familyName, givenName, matchingRecords);
    }

    // Check if a record matches family name and given name
    private boolean matches(T record, String familyName, String givenName) {
        // Check if the object is an instance of PeopleRecord
        if (record instanceof PeopleRecord) {
            PeopleRecord pr = (PeopleRecord) record;
            return pr.getFamilyName().equals(familyName) && pr.getGivenName().equals(givenName);
        }
        // Additional handling if "T" is not PeopleRecord
        return false;
    }

    // Get information about the tree: total nodes and height
    public String getInfo() {
        return "Total nodes: " + getTotalNodes(root) + ", Height: " + getHeight(root);
    }

    // Get the total number of nodes in the tree
    private int getTotalNodes(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + getTotalNodes(root.left) + getTotalNodes(root.right);
    }

    // Get the height of the tree
    private int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
