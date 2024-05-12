import java.util.Iterator;
import java.util.Stack;

public class BST <K extends Comparable<K>, V>{
    private Node root;
    private int size;
    private class Node{
        private K key;
        private V value;
        private Node left, right;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    public void put(K key, V value){
        size++;
        Node newNode = new Node(key, value);
        if(root == null){
            root = newNode;
            return;
        }
        Node current = root;
        while (true){
            int cmp = key.compareTo(current.key);
            if(cmp < 0){
                if(current.left == null){
                    current.left = newNode;
                    return;
                }
                current = current.left;
            }
            else if(cmp > 0){
                if(current.right == null){
                    current.right = newNode;
                    return;
                }
                current = current.right;
            }
            else{
                current.value = value;
                return;
            }
        }
    }
    public V get(K key){
        Node current = root;
        while(current != null){
            int cmp = key.compareTo(current.key);
            if(cmp < 0){
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            }
            else {
                return current.value;
            }
        }
        return null;
    }
    public void detele(K key){
        if(root == null){
            return;
        }
        Node parent = null;
        Node current = root;
        boolean isLeft = false;
        while(current != null && !current.key.equals(key)){
            parent = current;
            int cmp = key.compareTo(current.key);
            if(cmp < 0){
                current = current.left;
                isLeft = true;
            }
            else {
                current = current.right;
                isLeft = false;
            }
        }

        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeft) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeft) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        }
    }
    public class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public Iterable<Entry<K, V>> iterator() {
        Stack<Node> stack = new Stack<>();
        final Node[] current = new Node[]{root};

        return () -> new Iterator<Entry<K, V>>() {
            @Override
            public boolean hasNext() {
                return current[0] != null || !stack.isEmpty();
            }

            @Override
            public Entry<K, V> next() {
                while (current[0] != null) {
                    stack.push(current[0]);
                    current[0] = current[0].left;
                }

                Node node = stack.pop();
                Entry<K, V> entry = new Entry<>(node.key, node.value);

                current[0] = node.right;

                return entry;
            }
        };
    }






}
