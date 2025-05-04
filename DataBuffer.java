import java.util.Iterator;

public class DataBuffer<T> implements Iterable<T> {
    private T[] buffer;
    private int head = 0;
    private int tail = 0;
    private int count = 0;

    public DataBuffer(int bufferSize) {
        if(bufferSize < 1) {
            throw new IllegalArgumentException("Buffer size needs a value greater than 1");
        }
        buffer = (T[]) new Object[bufferSize];
    }

    public void enqueue (T t) {
        if (isFull()) {
            throw new IllegalArgumentException("Buffer is full");
        }
        buffer[tail] = t;
        tail = (tail + 1) % buffer.length;
        count++;
    }

    public T dequeue () {
        if (isEmpty()) {
            throw new IllegalArgumentException("Buffer is empty");
        }
        T value = buffer[head];
        buffer[head] = null;
        head = (head + 1) % buffer.length;
        count--;
        return value;
    }

    public void changeBufferSize (int newBufferSize) {
        if (newBufferSize < 1) {
            throw new IllegalArgumentException("Buffer size needs a value greater than 1");
        }
        T[] newBuffer = (T[]) new Object[newBufferSize];
        int itemsToCopy = Math.min(count, newBufferSize);
        for (int i = 0; i < itemsToCopy; i++) {
            newBuffer[i] = buffer[(head + 1) % buffer.length];
        }
        buffer = newBuffer;
        head = 0;
        tail = itemsToCopy % newBufferSize;
        count = itemsToCopy;
    }

    public boolean isFull() {
        return count == buffer.length;
    }

    public boolean isEmpty () {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public int bufferSize() {
        return buffer.length;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int idx = 0;
            public boolean hasNext() {
                return idx < count;
            }
            public T next() {
                if (!hasNext()) throw new IllegalArgumentException();
                T val = buffer[(head + idx) % buffer.length];
                idx++;
                return val;
            }
        };
    }

    public int [] MaxPQ (T[] a) {

    }
}