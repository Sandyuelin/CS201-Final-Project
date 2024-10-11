# CS201 Final Project - People Database Processing

## Overview

This project processes a database file (`people.txt`) that contains records of people's information, where each field is separated by semicolons (`;`). The file includes details such as names, addresses, contact info, and more. We developed several data structures in Java to handle operations like searching, sorting, and extracting frequently used words.

## File Structure
- [ ] **PeopleRecord**: A class representing individual records in the file `people.txt`.
- [ ] **MyBST**: A binary search tree (BST) for managing people’s records.
  - `getInfo()`: Returns the total number of nodes and the height of the tree.
  - `insert(PeopleRecord record)`: Inserts a new record into the BST.
  - `search(String firstName, String lastName)`: Searches for all records with matching given and family names.
- [ ] **MyHeap**: A heap structure for organizing people’s records.
  - `insert(PeopleRecord record)`: Adds a new record to the heap.
  - `remove()`: Removes the top element of the heap.
- [ ] **MyHashmap**: A hash map implemented with quadratic probing for storing and retrieving people’s records.
  - `put(String key, PeopleRecord record)`: Adds a record to the hash map.
  - `get(String key)`: Retrieves a record based on the key.
  - `delete(String key)`: Deletes a record from the hash map.
- [ ] **DatabaseProcessing**: A class that handles data loading, searching, sorting, and word frequency analysis.
  - `loadData(String fileName)`: Loads data from `people.txt` into an instance of `MyBST`.
  - `search(String firstName, String lastName)`: Searches for records in the BST.
  - `sort()`: Sorts records using heap sort by transferring them from the BST to the heap.
  - `getMostFrequentWords(int count, int len)`: Returns the top `count` most frequent words (of at least length `len`) in specific fields of `people.txt`.

## How to Run
1. **Clone the repository** and open it in your IDE.
2. **Compile and run** `DatabaseProcessing.java`.
3. The `main` method in `DatabaseProcessing` demonstrates the following operations:
    - Loading data from `people.txt`.
    - Searching for records by name.
    - Sorting records using heap sort.
    - Extracting the most frequent words from the dataset.
    
## Testing
1. **Search Testing**: We have tested the `search` method by querying names from `people.txt` to ensure it returns the correct records.
2. **Sort Testing**: The sorting functionality was verified by printing sorted records.
3. **Word Frequency Testing**: We confirmed the accuracy of the `getMostFrequentWords()` method by testing it with different word lengths and word counts.

## Task description
- Member 1: Implemented `PeopleRecord`, `MyBST`, and contributed to the `DatabaseProcessing` class.
- Member 2: Developed `MyHeap`, `MyHashmap`, and handled testing.

Overall contribution ratio: 50-50.

## Additional features
- [ ] support other typesof records apart from people.txt, BST/Heap or Hashmap are generic
- [ ] using email/phone/birthday to compare and order
- [ ] graph illustration of the BST/ Heap/Hashmap
- [ ] good testing stratey
