import java.util.Iterator;

public class DataBuffer<T> implements Iterable<T> {
    private T[] buffer;
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    public DataBuffer(int bufferSize) {
        if(bufferSize < 1) {
            throw new IllegalArgumentException("Buffer size needs a value greater than 1");
        }
        buffer = (T[]) new Object[bufferSize];                              // creates a new buffer with int buffersize
    }

    public void enqueue (T t) {
        if (isFull()) {
            throw new IllegalArgumentException("Buffer is full");
        }
        buffer[tail] = t;                                                   // enqueues t in buffer
        tail = (tail + 1) % buffer.length;                                  // wraps the tail index around to 0 when it reaches buffer.length
        size++;                                                            // increases the counter/size of elements in buffer
    }

    public T dequeue () {
        if (isEmpty()) {
            throw new IllegalArgumentException("Buffer is empty");
        }
        T value = buffer[head];                                            // gets value of head in array
        buffer[head] = null;                                                // dequeues head
        head = (head + 1) % buffer.length;                                  // enables wrap-around
        size--;                                                             // decreases the size
        return value;
    }

    public void changeBufferSize (int newBufferSize) {
        if (newBufferSize < 1) {
            throw new IllegalArgumentException("Buffer size needs a value greater than 1");
        }
        T[] newBuffer = (T[]) new Object[newBufferSize];                   // allocates a fresh buffer of length newBufferSize
        int itemsToCopy = Math.min(size, newBufferSize);                   // controls how many elements in the old array to copy over to the new one
        for (int i = 0; i < itemsToCopy; i++) {
            newBuffer[i] = buffer[(head + i) % buffer.length];
        }
        buffer = newBuffer;                                                 // overwrites the old buffer
        head = 0;                                                           // resets the pointer
        tail = itemsToCopy % newBufferSize;
        size = itemsToCopy;
    }

    public boolean isFull() {
        return size == buffer.length;                                      // checks if full
    }

    public boolean isEmpty () {
        return size == 0;                                                  // checks if empty
    }

    public int size() {
        return size;                                                        // returns the amount of elements in queue
    }

    public int bufferSize() {
        return buffer.length;                                               // returns the amount of available positions in queue
    }

    public Iterator<T> iterator() {                                         // makes the databuffer iterable
        return new Iterator<T>() {
            private int idx = 0;
            public boolean hasNext() {
                return idx < size;
            }
            public T next() {
                if (!hasNext()) throw new IllegalArgumentException();
                T val = buffer[(head + idx) % buffer.length];
                idx++;
                return val;
            }
        };
    }
}