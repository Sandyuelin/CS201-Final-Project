class MyBST {
    /* 
     A binary search tree for managing people's records
     methods of insertion, getInfo, and search; specifically add deletion methods
    */

    class Node {
        PeopleRecord data;
        Node left, right;

        Node(PeopleRecord record) {
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

    private Node insertRec (Node root, PeopleRecord record){
        if (root ==null ){
            root = new Node(record);
            return root;
        }
        if (record.getFamilyName().compareTo(root.data.getFamilyName()) < 0){
            root.left = insertRec(root.left, record);
        } else if (record.getFamilyName().compareTo(root.data.getFamilyName()) > 0){
            root.right = insertRec(root.right, record);
        }

        else {
            if (record.getGivenName().compareTo(root.data.getGivenName()) < 0){
                root.left = insertRec(root.left, record);
            } else if (record.getGivenName().compareTo(root.data.getGivenName()) > 0){
                root.right = insertRec(root.right, record);

            }
        }
        return root;
    }
    
    // Search: search for a record by family name and given name

    public PeopleRecord search(String familyName, String givenName) {
        return searchRec(root, familyName, givenName);
    }

    private PeopleRecord searchRec(Node root, String familyName, String givenName) {
        if (root == null) {
            return null;
        }
        if (familyName.compareTo(root.data.getFamilyName()) < 0) {
            return searchRec(root.left, familyName, givenName);
        } else if (familyName.compareTo(root.data.getFamilyName()) > 0) {
            return searchRec(root.right, familyName, givenName);
        } else {
            if (givenName.compareTo(root.data.getGivenName()) < 0) {
                return searchRec(root.left, familyName, givenName);
            } else if (givenName.compareTo(root.data.getGivenName()) > 0) {
                return searchRec(root.right, familyName, givenName);
            } else {
                return root.data;
            }
        }
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

    // Deletion: delete a record by family name and given name

    public void delete(String familyName, String givenName) {
        root = deleteRec(root, familyName, givenName);
    }

    private Node deleteRec(Node root, String familyName, String givenName) {
        if (root == null) {
            return root;
        }
        if (familyName.compareTo(root.data.getFamilyName()) < 0) {
            root.left = deleteRec(root.left, familyName, givenName);
        } else if (familyName.compareTo(root.data.getFamilyName()) > 0) {
            root.right = deleteRec(root.right, familyName, givenName);
        } else {
            if (givenName.compareTo(root.data.getGivenName()) < 0) {
                root.left = deleteRec(root.left, familyName, givenName);
            } else if (givenName.compareTo(root.data.getGivenName()) > 0) {
                root.right = deleteRec(root.right, familyName, givenName);
            } else {
                // Node to be deleted is found
                // case 1: node has no left child return right child
                if (root.left == null) {
                    return root.right;
                // case 2: node has no right child return left child
                } else if (root.right == null) {
                    return root.left;
                }
                // case 3: node has two children, replace it with the inorder successor
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
}
