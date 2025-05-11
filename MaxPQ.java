
import java.util.NoSuchElementException;

public class MaxPQ<T extends Comparable<T>> {
    private T[] pq;                             // heap array
    private int N;                              // number of items

    public MaxPQ(T[] a) {
        N = a.length;
        pq = (T[]) new Comparable[N+1];         // adds one extra slot for indexing

        // copy input array a[] into pq[something]
        for (int i = 0; i < N; i++) {        
            pq[i+1] = a[i];
        }

        // "Heapify" from O(N) by using the sink method. Start at N/2, since all nodes there are children already
        for (int k = N/2; k >= 1; k--) {        
            sink(k);
        }
    }

    // Moves element at index k down until heap order is achieved
    private void sink(int k) {
        while (2*k <= N) {                      // while k has at least a left child
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;     // if right child is larger than left, point j to right child
            if (!less(k, j)) break;             // if parent is larger than child, heap order is good, then break
            exch(k, j);                         // else swap parent with larger child
            k = j;                              // continue sinking at child's index
        }    
    }

    // compare pq[i] < pq[j]
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    // swap two entries in the heap
    private void exch(int i, int j) {
        T temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    // checks if heap is empty
    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(T x) {
        if (N == pq.length - 1) resize(2 * pq.length);          // if array is full, double the size
        pq[++N] = x;                                            // place the new element in the back
        swim(N);                                                // move up to restore heap order
    }


    private void resize(int capacity) {
        T[] temp = (T[]) new Comparable[capacity];              // creates a new temp array
        for (int i = 1; i <= N; i++) {                          // copies the elements over to the new array with changed size
            temp[i] = pq[i];            
        }
        pq = temp;                                              // updates array
    }

    private void swim(int k) {                      
        while (k > 1 && less(k/2, k)) {                         // while not at top and parent is smaller than child
            exch(k/2, k);                                       // switch child and parent
            k = k/2;                                            // move pointer up
        }
    }

    // removes and returns the maximum element in the heap
    public T delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        T max = pq[1];              // max root
        exch(1, N--);               // swap root with last element, also decrease size
        sink(1);                    // restore heap order by sinking the new root 
        if ((N > 0) && (N == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }
        return max;
    }

}