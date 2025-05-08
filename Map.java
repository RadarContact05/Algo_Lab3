import java.util.Queue;
import java.util.LinkedList;


public class Map<T1 extends Comparable<T1>, T2> {


    // Helpers
    private Node root;
    private class Node {
        T1 key;
        T2 val;
        Node left, right;
        int size; 

        Node(T1 key, T2 val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    private Node put(Node x, T1 key, T2 val) {
        if (x == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    private T2 get(Node x, T1 key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    private int size(Node x) {
        return (x == null) ? 0 : x.size;
    }

    private Node delete(Node x, T1 key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);

        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    private void inorder(Node x, Queue<T1> queue) {
        if (x == null) return;
        inorder(x.left, queue);
        queue.add(x.key);
        inorder(x.right, queue);
    }
    
    
    // Public API
    public Map() {
        root = null;
    }

    public void put(T1 key, T2 val) {
        if (key == null) throw new IllegalArgumentException("Argument is null");
        if (val == null) {
            throw new IllegalArgumentException("null values not supported");
        }
        root = put(root, key, val);
    }

    public T2 get(T1 key) {
        if (key == null) throw new IllegalArgumentException("Argument is null");
        return get(root, key);
    }

    public boolean contains(T1 key) {
        if (key == null) throw new IllegalArgumentException("Argument is null");
        return get(key) != null;
    }

    public void delete(T1 key) {
        if (key == null) throw new IllegalArgumentException("Argument is null");
        root = delete(root, key);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    public Iterable<T1> keys() {
        Queue<T1> queue = new LinkedList<>();
        inorder(root, queue);
        return queue;
    }
}