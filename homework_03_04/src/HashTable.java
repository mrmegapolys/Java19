import java.util.Map.Entry;

public class HashTable<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 32; //is there a way to get rid of static?
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] array;
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

    public V get(K key) { //TODO: implement
        int idx = getStartIndex(key);
        Entry<K, V> entry;
        return null;
    }

    public V remove(K key) { //TODO: implement
        int idx = getStartIndex(key);
        Entry<K, V> entry;
        return null;
    }

    public boolean containsKey(K key) { //TODO: implement
        return false;
    }

    public boolean containsValue(V value) {
        for (Entry<K, V> entry : array) {
            if ((entry != null) && (entry.getValue() == value)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private Entry<K, V>[] createArray(int capacity) {
        return new Entry[capacity];
    }

    private void swapEntries(int idxFirst, int idxSecond) {
        Entry<K, V> tmp = array[idxSecond];
        array[idxSecond] = array[idxFirst];
        array[idxFirst] = tmp;
    }

    private void putValue(K key, V value) { //TODO: implement
        int idx = getStartIndex(key);
    }

    private int getStartIndex(K key) {
        return Math.abs(key.hashCode() % this.capacity);
    }

    private void resize() {
        Entry<K, V>[] oldArray = array;
        capacity *= 2;
        array = createArray(capacity);
        for (Entry<K, V> entry : oldArray) {
            if (entry != null) {
                putValue(entry.getKey(), entry.getValue());
            }
        }
    }

}
