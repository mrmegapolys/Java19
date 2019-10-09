public class HashTable<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 32; //is there a way to get rid of static?
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<K, V>[] array;
    private int size;  //TODO: implement size
    private int capacity;
    private float loadFactor;


    public HashTable(int initialCapacity, float loadFactor) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.array = createArray(capacity);
    }

    public HashTable(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashTable() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }


    public void put(K key, V value) {
        if ((float) (size + 1) / capacity > loadFactor) {
            resize();
        }
        putValue(key, value);
    }

    public V get(K key) {
        int idx = getStartIndex(key);
        int firstTombstone = -1;
        Node<K, V> node;

        while ((node = array[idx]) != null) {
            if ((node.isTombstone()) && (firstTombstone == -1)) {
                firstTombstone = idx;
            }
            if (node.getKey() != key) {//TODO: check tombstone
                idx = (++idx) % capacity;
                continue;
            }
            if (firstTombstone != -1) {
                swapNodes(firstTombstone, idx);
            }
            return node.getValue();
        }

        return null;
    }

    public V remove(K key) {
        int idx = getStartIndex(key);
        Node<K, V> node;
        while ((node = array[idx]) != null) {
            if ((node.getKey() == key) && (!node.isTombstone())) {
                node.setTombstone(true);
                return node.getValue();
            }
            idx = (++idx) % capacity;
        }
        return null;
    }

    public boolean containsKey(K key) {
        return false;
    }

    public boolean containsValue(V value) {
        for (Node<K, V> node : array) {
            if ((node != null) && (node.getValue() == value)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private Node<K, V>[] createArray(int capacity) {
        return new Node[capacity];
    }

    private void swapNodes(int idxFirst, int idxSecond) {
        Node<K, V> tmp = array[idxSecond];
        array[idxSecond] = array[idxFirst];
        array[idxFirst] = tmp;
    }

    private void putValue(K key, V value) {
        int idx = getStartIndex(key);
        while (array[idx] != null) {
            if (array[idx].isTombstone()) {
                Node<K, V> node = new Node<>(key, value);
                array[idx] = node;
                return;
            }
            idx = (++idx) % capacity;
        }
        Node<K, V> node = new Node<>(key, value);
        array[idx] = node;
    }

    private int getStartIndex(K key) {
        return Math.abs(key.hashCode() % this.capacity);
    }

    private void resize() {
        Node<K, V>[] oldArray = array;
        capacity *= 2;
        array = createArray(capacity);
        for (Node<K, V> node : oldArray) {
            if ((node != null) && (!node.isTombstone())) {
                putValue(node.getKey(), node.getValue());
            }
        }
    }
}
