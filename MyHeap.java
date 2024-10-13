// Max-heap which stores records by name (z on top, a on bottom)
// Using ArrayList based implementation

import java.util.ArrayList;

public class MyHeap {
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

    // return the size of the heap
    public int size() {
        return heap.size();
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
