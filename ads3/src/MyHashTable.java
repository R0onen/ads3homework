public class MyHashTable <K, V>{
    private class HashNode<K, V>{
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }
    }
    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;
    public MyHashTable(){
        chainArray = new HashNode[M];
    }
    public MyHashTable(int M){
        this.M = M;
        chainArray = new HashNode[M];
    }
    private int hash(K key){
        return Math.abs(key.hashCode()) % M;
    }
    public void put(K key, V value){
        int index = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);

        if(chainArray[index] == null){
            chainArray[index] = newNode;
            size++;
            return;
        }

        HashNode<K, V> current = chainArray[index];
        HashNode<K, V> lastNode = null;
        while (current != null){
            if(current.key.equals(key)){
                current.value = value;
                return;
            }
            lastNode = current;
            current = current.next;
        }

        if(lastNode != null){
            lastNode.next = newNode;
            size++;
        }
    }
    public V get(K key){
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        while(current != null){
            if(current.key.equals(key)){
                return current.value;
            }
            current = current.next;
        }
        return null;

    }
    public V remove(K key){
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        HashNode<K, V> lastNode = null;
        while (current != null){
            if(current.key.equals(key)){
                if(lastNode != null){
                    lastNode.next = current.next;
                }
                else{
                    chainArray[index] = current.next;
                }
                size--;
                return current.value;
            }
            lastNode = current;
            current = current.next;
        }
        return null;
    }
    public boolean contains(V value){
        for(int i = 0; i < chainArray.length; i++){
            HashNode<K, V> current = chainArray[i];
            while (current != null){
                if(current.value.equals(value)){
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }
    public K getkey(V value){
        for(int i = 0; i < chainArray.length; i++){
            HashNode<K, V> current = chainArray[i];
            while(current != null){
                if(current.value.equals(value)){
                    return current.key;
                }
                current = current.next;
            }
        }
        return null;
    }
}
