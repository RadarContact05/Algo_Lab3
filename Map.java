import java.util.LinkedList;
import java.util.Queue;


public class Map<T1 extends Comparable<T1>, T2> {


    // Helpers

    private Node root;                                              // reference to the root node of the BST

    // initialize the variables that represent a node
    private class Node {
        T1 key;                 // keys by which nodes are ordered
        T2 val;                 // Values that are stored in nodes and refered by the key
        Node left, right;       // references to left and right child nodes
        int size;               // number of nodes under this node

    // Node constructor initializes key, value, and subtree size
        public Node(T1 key, T2 val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    // Put: recursive insertion into BST rooted at x
    private Node put(Node x, T1 key, T2 val) {
        if (x == null) {
            return new Node(key, val, 1);                       // if subtree is empty, create a new node of size 1
        }
        int cmp = key.compareTo(x.key);                         // Compare search key to node key
        if (cmp < 0) x.left  = put(x.left,  key, val);          // put left if key is smaller
        else if (cmp > 0) x.right = put(x.right, key, val);     // put right if key is larger
        else x.val = val;                                       // update value if the keys are equal

        x.size = 1 + size(x.left) + size(x.right);              // updates the size after insertion 
        return x;                                               // return node
    }

    // Get: recursive get from subtree rooted at x
    private T2 get(Node x, T1 key) {    
        if (x == null) return null;                     // if subtree empty, not found

        int cmp = key.compareTo(x.key);                 // compare search key to node key
        if (cmp < 0) return get(x.left, key);           // seatch left if key smaller
        else if (cmp > 0) return get(x.right, key);     // search right if key is larger
        else return x.val;                              // return the matching key if found
    }

    // Size: returns size of subtree rooted at x
    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.size;
        }
    }

    // Delete: delete a key from subtree rooted at x
    private Node delete(Node x, T1 key) {
        if (x == null) return null;                         // if tree empty, return null

        int cmp = key.compareTo(x.key);                     // compare search key to node key
        if (cmp < 0) x.left = delete(x.left, key);          // search left child
        else if (cmp > 0) x.right = delete(x.right, key);   // search right child

        else {
            if (x.right == null) return x.left;             // no right child, replace with left
            if (x.left == null) return x.right;             // no left child, replace with right
            Node t = x;                                     // save the reference to the node to delete
            x = min(t.right);                               // replace x with the inorder successor
            x.right = deleteMin(t.right);                   // remove successor from right subtre
            x.left = t.left;                                // link left subtree
        }

        // update size after deletion
        x.size = size(x.left) + size(x.right) + 1;
        return x;                                           // return updated subtree root
    }

    // deleteMin: delete the minimum node from subtree rooted at x
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;             // if no left child, return right subtree root
        x.left = deleteMin(x.left);                     // otherwise return left, since left is always the smaller, compared to root
        x.size = size(x.left) + size(x.right) + 1;      // update size
        return x;                                       // return the updated subtree root
    }

    // min: returns the node with the min value
    private Node min(Node x) {
        if (x.left == null) return x;                   // if, theres is no left child, return node x
        else return min(x.left);                        // return left child node, since that one is always the smaller one in BST
    } 

    // inorder: Using the inorder method to sort the keys
    private void inorder(Node x, Queue<T1> queue) {
        if (x == null) return;
        inorder(x.left, queue);
        queue.add(x.key);
        inorder(x.right, queue);
    }
    
    
    // Public API

    // Constructor, creates an empty tree
    public Map() {
        root = null;
    }

    // Insert key and value pair into the BST
    public void put(T1 key, T2 val) {
        if (key == null) throw new IllegalArgumentException("Argument is null");
        if (val == null) {
            throw new IllegalArgumentException("null values not supported");
        }
        root = put(root, key, val);                     // puts the new, node with it's values: root, key and val
    }

    // get: gets the value associated with given key
    public T2 get(T1 key) {
        if (key == null) throw new IllegalArgumentException("Argument is null");
        return get(root, key);
    }

    // contains: checks the presence of a key
    public boolean contains(T1 key) {
        if (key == null) throw new IllegalArgumentException("Argument is null");
        return get(key) != null;
    }

    // delete: remove key and its value from map
    public void delete(T1 key) {
        if (key == null) throw new IllegalArgumentException("Argument is null");
        root = delete(root, key);
    }

    // isEmpty: checks if map is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // size: returns number of key and value pairs in map
    public int size() {
        return size(root);
    }

    // Iterable: return all keys in sorted (ascending order)
    public Iterable<T1> keys() {
        Queue<T1> queue = new LinkedList<>();
        inorder(root, queue);
        return queue;
    }
}